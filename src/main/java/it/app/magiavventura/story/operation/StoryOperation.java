package it.app.magiavventura.story.operation;

import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.StoryPost;
import it.app.magiavventura.story.model.StorySearch;
import it.app.magiavventura.story.service.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@AllArgsConstructor
@RequestMapping("/v1/story")
public class StoryOperation {

    private final StoryService storyService;

    @GetMapping
    public Page<Story> searchStories(@RequestHeader(defaultValue = "0") Integer pageNumber,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) String subtitle,
                                     @RequestParam(required = false) String text,
                                     @RequestParam(required = false) String author,
                                     @RequestParam(required = false) List<String> categories,
                                     @RequestParam(required = false) String age) {
        return storyService.findAll(pageNumber, StorySearch
                .builder()
                .title(title)
                .subtitle(subtitle)
                .text(text)
                .author(author)
                .categories(categories)
                .age(age)
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
