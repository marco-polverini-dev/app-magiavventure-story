package com.magiavventure.story.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorySearch {

    private String title;
    private String subtitle;
    private String text;
    private String author;
    private String categories;
    private Boolean active;

}
