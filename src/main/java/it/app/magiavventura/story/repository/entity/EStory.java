package it.app.magiavventura.story.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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
    private Date creationDate;
    private Date approvationDate;
    private List<String> categories;
    private String age;

}
