package com.magiavventure.story.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "story")
public class EStory {

    @Id()
    private UUID id;

    private String title;
    private String subtitle;
    private String text;
    private String author;
    private LocalDateTime creationDate;
    private LocalDateTime approvationDate;
    private Boolean active;
    private List<String> categories;
    private String age;

    public EStory generateId() {
        this.setId(UUID.randomUUID());
        this.setActive(Boolean.TRUE);
        return this;
    }

}
