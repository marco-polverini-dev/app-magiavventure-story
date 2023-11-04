package it.app.magiavventura.service;

import com.magiavventure.story.mapper.StoryMapper;
import com.magiavventure.story.repository.StoryRepository;
import com.magiavventure.story.service.StoryService;
import it.app.magiavventura.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StoryServiceTest {

    private StoryService storyService;
    @Mock
    private StoryRepository storyRepository;

    @BeforeEach
    void beforeEach() {
        var storyMapper = Mappers.getMapper(StoryMapper.class);
        storyService = new StoryService(storyRepository, storyMapper, TestUtils.createStoryProperties());
    }

    @Test
    void saveStoryTest_ok() {
        var storyPost = TestUtils.createStoryPost();
        var eStory = TestUtils.createEStory(UUID.randomUUID());
        Mockito.when(storyRepository.save(Mockito.any()))
                .thenReturn(eStory);

        var story = storyService.saveStory(storyPost);

        Mockito.verify(storyRepository).save(Mockito.any());

        TestUtils.assertStoryValues(eStory, story);
    }

    @Test
    void saveStoryTest_throwInternalServerError() {
        var storyPost = TestUtils.createStoryPost();
        Mockito.when(storyRepository.save(Mockito.any()))
                .thenReturn(null);

        var exception = Assertions.assertThrows(HttpServerErrorException.class,
                () -> storyService.saveStory(storyPost));

        Mockito.verify(storyRepository).save(Mockito.any());

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(500, exception.getStatusCode().value());
    }

    @Test
    void findStoryByIdTest_ok() {
        var id = UUID.randomUUID();
        var eStory = TestUtils.createEStory(id);
        Mockito.when(storyRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(eStory));

        var story = storyService.findById(id);

        Mockito.verify(storyRepository).findById(Mockito.any());

        TestUtils.assertStoryValues(eStory, story);
    }

    @Test
    void findStoryByIdTest_throwNotFoundError() {
        var id = UUID.randomUUID();
        Mockito.when(storyRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(HttpClientErrorException.class,
                () -> storyService.findById(id));

        Mockito.verify(storyRepository).findById(Mockito.any());

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(404, exception.getStatusCode().value());
    }

    @Test
    @SuppressWarnings("unchecked")
    void findAllTest_ok() {
        Mockito.when(storyRepository.findAll(Mockito.any(Example.class), Mockito.any(Pageable.class)))
                        .thenReturn(TestUtils.createPageEStories());

        var storiesPage = storyService.findAll(0, TestUtils.createStorySearch());

        Mockito.verify(storyRepository).findAll(Mockito.any(Example.class), Mockito.any(Pageable.class));

        Assertions.assertNotNull(storiesPage);
        Assertions.assertEquals(1, storiesPage.getNumberOfElements());
    }
}
