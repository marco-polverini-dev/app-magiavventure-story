package it.app.magiavventura.story.service;

import it.app.magiavventura.story.repository.StoryRepository;
import it.app.magiavventura.story.repository.entity.Story;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StoryService {
    private final StoryRepository storyRepository;

    public Story saveStory() {
        return storyRepository.save(Story
                .builder()
                        .id(UUID.randomUUID())
                        .title("title")
                        .text("<h1>text</h1>")
                .build());
    }

    public List<Story> findAll() {
        return storyRepository.findAll();
    }
}
