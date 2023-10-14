package it.app.magiavventura.story.operation;

import it.app.magiavventura.story.repository.entity.Story;
import it.app.magiavventura.story.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/v1/story")
public class StoryOperation {

    private final StoryService storyService;

    @GetMapping
    public Story getHello() {
        return storyService.saveStory();
    }

    @GetMapping(path = "/all")
    public List<Story> getAll() {
        return storyService.findAll();
    }
}
