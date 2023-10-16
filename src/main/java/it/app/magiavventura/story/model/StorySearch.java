package it.app.magiavventura.story.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorySearch {

    private String title;
    private String subtitle;
    private String text;
    private String author;
    private List<String> categories;
    private String age;

}
