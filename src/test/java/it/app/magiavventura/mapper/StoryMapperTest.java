package it.app.magiavventura.mapper;

import com.magiavventure.story.mapper.StoryMapper;
import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.model.StorySearch;
import com.magiavventure.story.repository.entity.EStory;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

class StoryMapperTest {

    private final StoryMapper storyMapper = Mappers.getMapper(StoryMapper.class);

    @Test
    void mapTest_Ok() {
        var eStory = createEStory();
        EStory eStoryNull = null;

        var story = storyMapper.map(eStory);
        var storyNull = storyMapper.map(eStoryNull);

        Assertions.assertNotNull(story);
        assertStoryValues(eStory, story);
        Assertions.assertNull(storyNull);
    }

    @Test
    void mapSearchTest_Ok() {
        var storySearch = createStorySearch();
        StorySearch storySearchNull = null;

        var eStory = storyMapper.mapSearch(storySearch);
        var eStoryNull = storyMapper.mapSearch(storySearchNull);

        Assertions.assertNotNull(eStory);
        assertEStoryValues(storySearch, eStory);
        Assertions.assertNull(eStoryNull);
    }

    @Test
    void mapPostTest_Ok() {
        var storyPost = createStoryPost();
        StoryPost storyPostNull = null;

        var eStory = storyMapper.mapPost(storyPost);
        var eStoryNull = storyMapper.mapPost(storyPostNull);

        Assertions.assertNotNull(eStory);
        assertEStoryValues(storyPost, eStory);
        Assertions.assertNull(eStoryNull);
    }

    private void assertStoryValues(EStory expected, Story actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCreationDate(), actual.getCreationDate());
        Assertions.assertEquals(expected.getApprovationDate(), actual.getApprovationDate());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }

    private void assertEStoryValues(StorySearch expected, EStory actual) {
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }

    private void assertEStoryValues(StoryPost expected, EStory actual) {
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }

    @NotNull
    private EStory createEStory() {
        return EStory
                .builder()
                .id(UUID.randomUUID())
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .creationDate(LocalDateTime.now())
                .approvationDate(LocalDateTime.now())
                .active(true)
                .categories("category")
                .build();
    }

    @NotNull
    private StorySearch createStorySearch() {
        return StorySearch
                .builder()
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .categories("category")
                .build();
    }

    @NotNull
    private StoryPost createStoryPost() {
        return StoryPost
                .builder()
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .creationDate(LocalDateTime.now())
                .approvationDate(LocalDateTime.now())
                .categories("category")
                .build();
    }
}
