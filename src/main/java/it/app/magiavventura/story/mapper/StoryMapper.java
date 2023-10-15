package it.app.magiavventura.story.mapper;

import it.app.magiavventura.story.model.Story;
import it.app.magiavventura.story.model.StoryPost;
import it.app.magiavventura.story.repository.entity.EStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoryMapper {

    @Mapping(target = "id", ignore = true)
    EStory mapPost(StoryPost storyPost);
    Story map(EStory eStory);
}
