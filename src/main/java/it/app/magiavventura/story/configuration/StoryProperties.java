package it.app.magiavventura.story.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "story")
public class StoryProperties {

    private SearchProperties search;
    @Data
    public static class SearchProperties {

        private PageableProperties pageable;

        @Data
        public static class PageableProperties {
            private Integer pageSize;
            private SortProperties sort;
            @Data
            public static class SortProperties {
                private String direction;
                private String[] properties;
            }
        }

    }


}
