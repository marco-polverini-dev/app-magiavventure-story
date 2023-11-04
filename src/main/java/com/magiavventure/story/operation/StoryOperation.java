package com.magiavventure.story.operation;

import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.model.StorySearch;
import com.magiavventure.story.service.StoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@AllArgsConstructor
@RequestMapping("/v1/story")
@Slf4j
public class StoryOperation {

    private final StoryService storyService;

    @GetMapping
    public Page<Story> searchStories(@RequestHeader(defaultValue = "0") Integer pageNumber,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) String subtitle,
                                     @RequestParam(required = false) String text,
                                     @RequestParam(required = false) String author,
                                     @RequestParam(required = false) String category) {
        return storyService.findAll(pageNumber, StorySearch
                .builder()
                .title(title)
                .subtitle(subtitle)
                .text(text)
                .author(author)
                .categories(category)
                .active(Boolean.TRUE)
                .build());
    }

    @GetMapping("/{id}")
    public Story findStoryById(@PathVariable UUID id) {
        return storyService.findById(id);
    }

    @PostMapping
    public Story addStory(@RequestBody StoryPost storyPost) {
        return storyService.saveStory(storyPost);
    }
}
