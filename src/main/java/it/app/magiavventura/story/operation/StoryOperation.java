package it.app.magiavventura.story.operation;

import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.StoryPost;
import it.app.magiavventura.story.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@AllArgsConstructor
@RequestMapping("/v1/story")
public class StoryOperation {

    private final StoryService storyService;

    @GetMapping
    public Page<Story> searchStories(@RequestHeader(defaultValue = "0") Integer pageNumber) {
        return storyService.findAll(pageNumber);
    }

    @GetMapping("/{id}")
    public Story findStoryById(@RequestParam UUID id) {
        return storyService.findById(id);
    }

    @PostMapping
    public Story addStory(@RequestBody StoryPost storyPost) {
        return storyService.saveStory(storyPost);
    }
}
