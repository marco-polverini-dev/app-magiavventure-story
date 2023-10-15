package it.app.magiavventura.story.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Story {
    private UUID id;
    private String title;
    private String subtitle;
    private String text;
    private String author;
    private Date creationDate;
    private Date approvationDate;
    private List<String> categories;
    private String age;

}
