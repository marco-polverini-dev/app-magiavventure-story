package it.app.magiavventura.operation;

import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.operation.StoryOperation;
import com.magiavventure.story.service.StoryService;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StoryOperationTest {
    @InjectMocks
    private StoryOperation storyOperation;

    @Mock
    private StoryService storyService;

    @Test
    void searchStoriesTest_ok() {
        var page = new PageImpl<>(createStories());
        Mockito.when(storyService.findAll(Mockito.anyInt(), Mockito.any()))
                .thenReturn(page);

        var stories = storyOperation.searchStories(0, "title", "subtitle",
                "text", "author", "category");

        Mockito.verify(storyService).findAll(Mockito.anyInt(), Mockito.any());

        Assertions.assertNotNull(stories);
        Assertions.assertFalse(stories.isEmpty());
        Assertions.assertNotNull(stories.getContent());
        Assertions.assertEquals(1, stories.getContent().size());

    }

    @Test
    void findStoryByIdTest_ok() {
        var id = UUID.randomUUID();
        Mockito.when(storyService.findById(Mockito.any()))
                .thenReturn(createStory(id));

        var story = storyOperation.findStoryById(id);

        Mockito.verify(storyService).findById(Mockito.any());

        assertStoryValues(id, story);

    }

    @Test
    void addStoryTest_ok() {
        var storyPost = createStoryPost();
        var id = UUID.randomUUID();
        Mockito.when(storyService.saveStory(Mockito.any()))
                .thenReturn(createStory(id));

        var story = storyOperation.addStory(storyPost);

        Mockito.verify(storyService).saveStory(Mockito.any());

        assertStoryValues(id, story);
    }

    private void assertStoryValues(UUID id, Story story) {
        Assertions.assertNotNull(story);
        Assertions.assertEquals(id, story.getId());
        Assertions.assertEquals("title", story.getTitle());
        Assertions.assertEquals("subtitle", story.getSubtitle());
        Assertions.assertEquals("text", story.getText());
        Assertions.assertEquals("author", story.getAuthor());
        Assertions.assertEquals("category", story.getCategories());
    }

    @NotNull
    private StoryPost createStoryPost() {
        return StoryPost
                .builder()
                .title("title")
                .text("text")
                .author("author")
                .creationDate(LocalDateTime.now())
                .categories("category")
                .build();
    }

    @NotNull
    private Story createStory(UUID id) {
        return Story
                .builder()
                .id(id)
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .categories("category")
                .build();
    }

    @NotNull
    private List<Story> createStories() {
        return List.of(createStory(UUID.randomUUID()));
    }
}
