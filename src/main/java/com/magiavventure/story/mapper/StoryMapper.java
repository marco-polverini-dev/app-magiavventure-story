package com.magiavventure.story.mapper;

import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.repository.entity.EStory;
import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StorySearch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    EStory mapPost(StoryPost storyPost);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "approvationDate", ignore = true)
    EStory mapSearch(StorySearch story);
    Story map(EStory eStory);
}
