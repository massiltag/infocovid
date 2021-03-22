package com.pantheonsorbonne.infocovid.domain.dto.news;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * <p>
 *     DTO qui sert de structure "par défaut" des actualités dans notre application.
 *     Tous les autres DTO d'actualités devront être mappés vers ce type.
 * </p>
 */

@Document(collection = "news")
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

    @Id
    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("url")
    public String url;

    @JsonProperty("source")
    public String source;

    @JsonProperty("image")
    public String image;

    @JsonProperty("published_at")
    public String published_at;

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
