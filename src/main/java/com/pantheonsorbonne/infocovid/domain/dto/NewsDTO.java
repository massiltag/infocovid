package com.pantheonsorbonne.infocovid.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class NewsDTO {

    @JsonProperty("author")
    private String author;
    
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("url")
    private String url;

    @JsonProperty("source")
    private String source;

    @JsonProperty("image")
    private String image;

    @JsonProperty("published_at")
    private String published_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(title, newsDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
