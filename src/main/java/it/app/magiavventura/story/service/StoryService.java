package it.app.magiavventura.story.service;

import it.app.magiavventura.story.configuration.StoryProperties;
import it.app.magiavventura.story.mapper.StoryMapper;
import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.StoryPost;
import it.app.magiavventura.story.repository.StoryRepository;
import it.app.magiavventura.story.repository.entity.EStory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
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
                .map(this::generateId)
                .map(storyRepository::save)
                .map(storyMapper::map)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatusCode.valueOf(500)));
    }

    public Page<Story> findAll(Integer pageNumber) {
        var pageableProperties = storyProperties.getSearch().getPageable();
        var pageable = PageRequest.of(pageNumber, pageableProperties.getPageSize(),
                Sort.by(Sort.Direction.valueOf(pageableProperties.getSort().getDirection()),
                        pageableProperties.getSort().getProperties()));
        return storyRepository.findAll(pageable).map(storyMapper::map);
    }

    private EStory generateId(EStory eStory) {
        eStory.setId(UUID.randomUUID());
        return eStory;
    }
}
