package it.app.magiavventura.mapper;

import com.magiavventure.story.mapper.StoryMapper;
import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.model.StorySearch;
import com.magiavventure.story.repository.entity.EStory;
import it.app.magiavventura.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;


class StoryMapperTest {

    private final StoryMapper storyMapper = Mappers.getMapper(StoryMapper.class);

    @Test
    void mapTest_Ok() {
        var eStory = TestUtils.createEStory(UUID.randomUUID());
        EStory eStoryNull = null;

        var story = storyMapper.map(eStory);
        var storyNull = storyMapper.map(eStoryNull);

        TestUtils.assertStoryValues(eStory, story);
        Assertions.assertNull(storyNull);
    }

    @Test
    void mapSearchTest_Ok() {
        var storySearch = TestUtils.createStorySearch();
        StorySearch storySearchNull = null;

        var eStory = storyMapper.mapSearch(storySearch);
        var eStoryNull = storyMapper.mapSearch(storySearchNull);

        TestUtils.assertEStoryValues(storySearch, eStory);
        Assertions.assertNull(eStoryNull);
    }

    @Test
    void mapPostTest_Ok() {
        var storyPost = TestUtils.createStoryPost();
        StoryPost storyPostNull = null;

        var eStory = storyMapper.mapPost(storyPost);
        var eStoryNull = storyMapper.mapPost(storyPostNull);

        TestUtils.assertEStoryValues(storyPost, eStory);
        Assertions.assertNull(eStoryNull);
    }
}
