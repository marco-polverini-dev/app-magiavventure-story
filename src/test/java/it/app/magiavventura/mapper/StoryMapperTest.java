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
import java.util.List;
import java.util.UUID;

class StoryMapperTest {

    private final StoryMapper storyMapper = Mappers.getMapper(StoryMapper.class);

    @Test
    void mapTest_Ok() {
        var eStoryFull = createEStory(true);
        var eStoryWithoutCategories = createEStory(false);
        EStory eStoryNull = null;

        var storyFull = storyMapper.map(eStoryFull);
        var storyWithoutCategories = storyMapper.map(eStoryWithoutCategories);
        var storyNull = storyMapper.map(eStoryNull);

        Assertions.assertNotNull(storyFull);
        assertStoryValues(eStoryFull, storyFull, true);
        Assertions.assertNotNull(storyWithoutCategories);
        assertStoryValues(eStoryWithoutCategories, storyWithoutCategories, false);
        Assertions.assertNull(storyNull);
    }

    @Test
    void mapSearchTest_Ok() {
        var storySearchFull = createStorySearch(true);
        var storySearchWithoutCategories = createStorySearch(false);
        StorySearch storySearchNull = null;

        var eStoryFull = storyMapper.mapSearch(storySearchFull);
        var eStoryWithoutCategories = storyMapper.mapSearch(storySearchWithoutCategories);
        var eStoryNull = storyMapper.mapSearch(storySearchNull);

        Assertions.assertNotNull(eStoryFull);
        assertEStoryValues(storySearchFull, eStoryFull, true);
        Assertions.assertNotNull(eStoryWithoutCategories);
        assertEStoryValues(storySearchWithoutCategories, eStoryWithoutCategories, false);
        Assertions.assertNull(eStoryNull);
    }

    @Test
    void mapPostTest_Ok() {
        var storyPostFull = createStoryPost(true);
        var storyPostWithoutCategories = createStoryPost(false);
        StoryPost storyPostNull = null;

        var eStoryFull = storyMapper.mapPost(storyPostFull);
        var eStoryWithoutCategories = storyMapper.mapPost(storyPostWithoutCategories);
        var eStoryNull = storyMapper.mapPost(storyPostNull);

        Assertions.assertNotNull(eStoryFull);
        assertEStoryValues(storyPostFull, eStoryFull, true);
        Assertions.assertNotNull(eStoryWithoutCategories);
        assertEStoryValues(storyPostWithoutCategories, eStoryWithoutCategories, false);
        Assertions.assertNull(eStoryNull);
    }

    private void assertStoryValues(EStory expected, Story actual, boolean hasCategories) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCreationDate(), actual.getCreationDate());
        Assertions.assertEquals(expected.getApprovationDate(), actual.getApprovationDate());
        if(hasCategories)
            Assertions.assertEquals(expected.getCategories().size(), actual.getCategories().size());
        else
            Assertions.assertNull(actual.getCategories());
        Assertions.assertEquals(expected.getAge(), actual.getAge());
    }

    private void assertEStoryValues(StorySearch expected, EStory actual, boolean hasCategories) {
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        if(hasCategories)
            Assertions.assertEquals(expected.getCategories().size(), actual.getCategories().size());
        else
            Assertions.assertNull(actual.getCategories());
        Assertions.assertEquals(expected.getAge(), actual.getAge());
    }

    private void assertEStoryValues(StoryPost expected, EStory actual, boolean hasCategories) {
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        if(hasCategories)
            Assertions.assertEquals(expected.getCategories().size(), actual.getCategories().size());
        else
            Assertions.assertNull(actual.getCategories());
        Assertions.assertEquals(expected.getAge(), actual.getAge());
    }

    @NotNull
    private EStory createEStory(boolean addCategories) {
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
                .categories(addCategories ? List.of("category") : null)
                .age("age")
                .build();
    }

    @NotNull
    private StorySearch createStorySearch(boolean addCategories) {
        return StorySearch
                .builder()
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .categories(addCategories ? List.of("category") : null)
                .age("age")
                .build();
    }

    @NotNull
    private StoryPost createStoryPost(boolean addCategories) {
        return StoryPost
                .builder()
                .title("title")
                .subtitle("subtitle")
                .text("text")
                .author("author")
                .creationDate(LocalDateTime.now())
                .approvationDate(LocalDateTime.now())
                .categories(addCategories ? List.of("category") : null)
                .age("age")
                .build();
    }
}
