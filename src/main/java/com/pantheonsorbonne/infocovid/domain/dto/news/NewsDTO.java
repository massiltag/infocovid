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

	/**
	 * Titre de l'article
	 */
    @Id
    @JsonProperty("title")
    private String title;
    
    /**
     * Description
     */
    @JsonProperty("description")
    private String description;

    /**
     * Lien URL
     */
    @JsonProperty("url")
    private String url;

    /**
     * Nom officiel du journal auteur
     */
    @JsonProperty("source")
    private String source;

    /**
     * Image à la une de l'article
     */
    @JsonProperty("image")
    private String image;

    /**
     * Date de publication
     */
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
