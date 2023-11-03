package com.magiavventure.story.service;

import com.magiavventure.story.repository.StoryRepository;
import com.magiavventure.story.repository.entity.EStory;
import com.magiavventure.story.configuration.StoryProperties;
import com.magiavventure.story.mapper.StoryMapper;
import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.model.StorySearch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class StoryService {
    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;
    private final StoryProperties storyProperties;

    public Story saveStory(StoryPost storyPost) {
        return Optional.ofNullable(storyMapper.mapPost(storyPost))
                .map(EStory::generateId)
                .map(storyRepository::save)
                .map(storyMapper::map)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatusCode.valueOf(500)));
    }

    public Page<Story> findAll(Integer pageNumber, StorySearch storySearch) {
        var pageableProperties = storyProperties.getSearch().getPageable();
        var pageable = PageRequest.of(pageNumber, pageableProperties.getPageSize(),
                Sort.by(Sort.Direction.valueOf(pageableProperties.getSort().getDirection()),
                        pageableProperties.getSort().getProperties()));
        var probe = storyMapper.mapSearch(storySearch);
        return storyRepository.findAll(getExample(probe), pageable).map(storyMapper::map);
    }

    public Story findById(UUID id) {
        return storyRepository
                .findById(id)
                .map(storyMapper::map)
                .map(story -> {
                    log.error(String.join(",", story.getCategories()));
                    return story;
                })
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404)));
    }

    private Example<EStory> getExample(EStory probe) {
        return Example.of(probe, ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("subtitle", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("text", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("categories", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("author", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("active", ExampleMatcher.GenericPropertyMatcher::exact)
        );
    }
}
