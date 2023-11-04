package it.app.magiavventura.operation;

import com.magiavventure.story.model.Story;
import com.magiavventure.story.operation.StoryOperation;
import com.magiavventure.story.service.StoryService;
import it.app.magiavventura.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StoryOperationTest {
    @InjectMocks
    private StoryOperation storyOperation;

    @Mock
    private StoryService storyService;

    @Test
    void searchStoriesTest_ok() {
        Mockito.when(storyService.findAll(Mockito.anyInt(), Mockito.any()))
                .thenReturn(TestUtils.createPageStories());

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
                .thenReturn(TestUtils.createStory(id));

        var story = storyOperation.findStoryById(id);

        Mockito.verify(storyService).findById(Mockito.any());

        assertStoryValues(id, story);

    }

    @Test
    void addStoryTest_ok() {
        var storyPost = TestUtils.createStoryPost();
        var id = UUID.randomUUID();
        Mockito.when(storyService.saveStory(Mockito.any()))
                .thenReturn(TestUtils.createStory(id));

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


}
