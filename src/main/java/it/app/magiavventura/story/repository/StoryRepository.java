package it.app.magiavventura.story.repository;

import it.app.magiavventura.story.repository.entity.EStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoryRepository extends MongoRepository<EStory, UUID>, PagingAndSortingRepository<EStory, UUID> {
}
