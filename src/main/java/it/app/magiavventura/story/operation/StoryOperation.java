package it.app.magiavventura.story.operation;

import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.post.StoryPost;
import it.app.magiavventura.story.repository.entity.EStory;
import it.app.magiavventura.story.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController()
@AllArgsConstructor
@RequestMapping("/v1/story")
public class StoryOperation {

    private final StoryService storyService;

    @GetMapping(path = "")
    public Page<EStory> searchStories() {
        return storyService.findAll();
    }

    @PostMapping
    public Story addStory(@RequestBody StoryPost storyPost) {
        return storyService.saveStory(storyPost);
    }
}
