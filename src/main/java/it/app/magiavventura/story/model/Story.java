package it.app.magiavventura.story.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Story {
    @NotNull
    private UUID id;
    @NotNull
    private String title;
    private String subtitle;
    @NotNull
    private String text;
    @NotNull
    private String author;
    @NotNull
    private Date creationDate;
    private Date approvationDate;
    @NotNull
    @NotEmpty
    private List<String> categories;
    private String age;

}
