package it.app.magiavventura.story.service;

import com.mongodb.BasicDBList;
import it.app.magiavventura.story.configuration.StoryProperties;
import it.app.magiavventura.story.mapper.StoryMapper;
import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.StoryPost;
import it.app.magiavventura.story.model.StorySearch;
import it.app.magiavventura.story.repository.StoryRepository;
import it.app.magiavventura.story.repository.entity.EStory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StoryService {
    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;
    private final StoryProperties storyProperties;

    public Story saveStory(StoryPost storyPost) {
        return Optional.ofNullable(storyMapper.mapPost(storyPost))
                .map(this::generateId)
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
        var example = Example.of(probe, ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("subtitle", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("text", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("categories", matcher -> matcher
                        .transform(source -> {
                            if(source.isPresent()) {
                                var categories = (String[]) source.get();
                                return Optional.of(String.join(",", categories));
                            }
                            return Optional.empty();
                        })
                        .contains())
        );
        return storyRepository.findAll(example, pageable).map(storyMapper::map);
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

    private EStory generateId(EStory eStory) {
        eStory.setId(UUID.randomUUID());
        eStory.setActive(Boolean.TRUE);
        return eStory;
    }
}
