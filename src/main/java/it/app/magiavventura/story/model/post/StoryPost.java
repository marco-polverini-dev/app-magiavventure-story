package it.app.magiavventura.story.model.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoryPost {
    @NonNull
    private String title;
    private String subtitle;
    @NonNull
    private String text;
    @NonNull
    private String author;
    @NonNull
    private Date creationDate;
    private Date approvationDate;
    @NonNull
    private List<String> categories;
    private String age;
}
