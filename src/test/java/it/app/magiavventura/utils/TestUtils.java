package it.app.magiavventura.utils;

import com.magiavventure.story.configuration.StoryProperties;
import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.model.StorySearch;
import com.magiavventure.story.repository.entity.EStory;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    private TestUtils() {}

    @NotNull
    public static EStory createEStory(UUID id) {
        return EStory
                .builder()
                .id(id)
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
    public static StorySearch createStorySearch() {
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
    public static StoryPost createStoryPost() {
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

    @NotNull
    public static Story createStory(UUID id) {
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
    public static List<Story> createStories() {
        return List.of(createStory(UUID.randomUUID()));
    }

    @NotNull
    public static List<EStory> createEStories() {
        return List.of(createEStory(UUID.randomUUID()));
    }

    @NotNull
    public static Page<Story> createPageStories() {
        return new PageImpl<>(TestUtils.createStories());
    }

    @NotNull
    public static Page<EStory> createPageEStories() {
        return new PageImpl<>(TestUtils.createEStories());
    }

    @NotNull
    public static StoryProperties createStoryProperties() {
        var storyProperties = new StoryProperties();
        storyProperties.setSearch(createStorySearchProperties());
        return storyProperties;
    }
    @NotNull
    public static StoryProperties.SearchProperties createStorySearchProperties() {
        var searchProperties = new StoryProperties.SearchProperties();
        var pageableProperties = new StoryProperties.SearchProperties.PageableProperties();
        var sortProperties = new StoryProperties.SearchProperties.PageableProperties.SortProperties();
        sortProperties.setProperties(new String[]{"creationDate", "id"});
        sortProperties.setDirection("DESC");
        pageableProperties.setSort(sortProperties);
        pageableProperties.setPageSize(3);
        searchProperties.setPageable(pageableProperties);
        return searchProperties;
    }

    public static void assertStoryValues(EStory expected, Story actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCreationDate(), actual.getCreationDate());
        Assertions.assertEquals(expected.getApprovationDate(), actual.getApprovationDate());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }

    public static void assertEStoryValues(StorySearch expected, EStory actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }

    public static void assertEStoryValues(StoryPost expected, EStory actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getSubtitle(), actual.getSubtitle());
        Assertions.assertEquals(expected.getText(), actual.getText());
        Assertions.assertEquals(expected.getAuthor(), actual.getAuthor());
        Assertions.assertEquals(expected.getCategories(), actual.getCategories());
    }
}
