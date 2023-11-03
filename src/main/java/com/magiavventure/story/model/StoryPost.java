package com.magiavventure.story.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoryPost {
    @NotNull
    private String title;
    private String subtitle;
    @NotNull
    private String text;
    @NotNull
    private String author;
    @NotNull
    private LocalDateTime creationDate;
    private LocalDateTime approvationDate;
    @NotNull
    @NotEmpty
    private List<String> categories;
    private String age;
}
