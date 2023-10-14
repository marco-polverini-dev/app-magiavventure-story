package it.app.magiavventura.story.repository;

import it.app.magiavventura.story.repository.entity.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoryRepository extends MongoRepository<Story, UUID> {
}
