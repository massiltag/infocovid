package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.remote.NewsClient;
import com.pantheonsorbonne.infocovid.repositories.NewsRepository;
import com.pantheonsorbonne.infocovid.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsClient newsClient;

    private final NewsRepository newsRepository;

    /**
     * <p>
     *     S'exécute tous les jours à 20h, permet de récupérer les nouveaux articles parus
     *     et les enregistrer en base.
     * </p>
     */
    @Override
    @Scheduled(cron = "0 0 20 * * *")
    public void getAndSaveArticles() {
        List<NewsDTO> news = newsClient.getNews();
        news.forEach( n -> { if (newsRepository.findById(n.getTitle()).isEmpty()) { newsRepository.save(n); } } );
    }

    /**
     * <p>
     *     Permet de récupérer les articles en base, fait une requête API si base vide.
     * </p>
     * @return liste des articles
     */
    @Override
    public List<NewsDTO> getNews() {
        if (newsRepository.findAll().isEmpty()) { getAndSaveArticles(); }
        return newsRepository.findAll().stream()
                .sorted((n1, n2) -> compareDates(n1.getPublished_at(), n2.getPublished_at()))
                .collect(Collectors.toList());
    }

    /**
     * Permet de vider la base de données, s'exécute le 1er de chaque mois à minuit
     */
    @Override
    @Scheduled(cron = "0 0 0 1 * *")
    public void clear() {
        newsRepository.deleteAll();
    }

    private int compareDates(String d1, String d2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[XXX][X]");
        LocalDateTime localDateTime1 = LocalDateTime.parse(d1, formatter);
        LocalDateTime localDateTime2 = LocalDateTime.parse(d2, formatter);

        if (localDateTime1.isAfter(localDateTime2)) return -1;
        else if (localDateTime1.isEqual(localDateTime2)) return 0;
        else return 1;
    }
}
