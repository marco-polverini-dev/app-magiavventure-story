package com.magiavventure.story.mapper;

import com.magiavventure.story.model.StoryPost;
import com.magiavventure.story.repository.entity.EStory;
import com.magiavventure.story.model.Story;
import com.magiavventure.story.model.StorySearch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryMapper {

    EStory mapPost(StoryPost storyPost);
    EStory mapSearch(StorySearch story);
    Story map(EStory eStory);
}
