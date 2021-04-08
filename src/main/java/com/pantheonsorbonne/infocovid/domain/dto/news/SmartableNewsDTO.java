package com.pantheonsorbonne.infocovid.domain.dto.news;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Actualités récupérées depuis l'API Smartable, doit être converti en {@link NewsDTO} par @{@link com.pantheonsorbonne.infocovid.domain.mappers.NewsMapper}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class SmartableNewsDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("excerpt")
    private String excerpt;

    @JsonProperty("webUrl")
    private String webUrl;

    @JsonProperty("provider")
    private Provider provider;

    @JsonProperty("images")
    private Image[] images;

    @JsonProperty("publishedDateTime")
    private String publishedDateTime;

    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    public static class Image {
        @JsonProperty("url")
        String url;
    }

    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    public static class Provider {
        @JsonProperty("name")
        String name;
    }
}
