package com.pantheonsorbonne.infocovid.domain.mappers;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.domain.dto.news.SmartableNewsDTO;
import org.mapstruct.Mapper;

/**
 * <p>
 *     Mapper pour convertir les actualités reçues de l'API Smartable vers notre modèle de données.
 * </p>
 */
@Mapper
public abstract class NewsMapper {

    public static NewsDTO smartableNewsToNewsDTO(SmartableNewsDTO smartableNewsDTO) {
        return NewsDTO.builder()
                .title(smartableNewsDTO.getTitle())
                .description(smartableNewsDTO.getExcerpt())
                .url(smartableNewsDTO.getWebUrl())
                .image(smartableNewsDTO.getImages() == null ? null : smartableNewsDTO.getImages()[0].getUrl())
                .source(smartableNewsDTO.getProvider().getName())
                .published_at(smartableNewsDTO.getPublishedDateTime())
                .build();
    }

}
