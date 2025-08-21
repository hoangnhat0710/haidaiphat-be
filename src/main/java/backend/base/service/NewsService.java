package backend.base.service;

import backend.base.domain.entity.News;
import backend.base.domain.entity.NewsCategory;
import backend.base.domain.repository.NewsCategoryRepository;
import backend.base.domain.repository.NewsRepository;
import backend.base.domain.request.NewsRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    public Page<News> getAllNews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return newsRepository.findAll(pageable);
    }

    public Optional<News> getNewsById(Integer id) {
        return newsRepository.findById(id);
    }

    public News createNews(NewsRequestDTO dto) {
        News news = new News();
        news.setTitle(dto.getTitle());
        news.setImageUrl(dto.getImageUrl());
        news.setMetaTitle(dto.getMetaTitle());
        news.setMetaDescription(dto.getMetaDescription());
        news.setDescription(dto.getDescription());
        news.setContentHtml(dto.getContentHtml());
        news.setTags(dto.getTags());
        news.setKeywords(dto.getKeywords());
        news.setCreatedAt(LocalDateTime.now());
        news.setModifiedAt(LocalDateTime.now());
        news.setViewCount(0);
        if (dto.getNewsCategoryId() != null) {
            NewsCategory newsCategory = newsCategoryRepository.findById(dto.getNewsCategoryId())
                .orElseThrow(() -> new RuntimeException("NewsCategory not found"));
            news.setNewsCategory(newsCategory);
        }
        return newsRepository.save(news);
    }

    public Optional<News> updateNews(Integer id, NewsRequestDTO dto) {
        return newsRepository.findById(id).map(news -> {
            news.setTitle(dto.getTitle());
            news.setImageUrl(dto.getImageUrl());
            news.setMetaTitle(dto.getMetaTitle());
            news.setMetaDescription(dto.getMetaDescription());
            news.setDescription(dto.getDescription());
            news.setContentHtml(dto.getContentHtml());
            news.setTags(dto.getTags());
            news.setKeywords(dto.getKeywords());
            news.setModifiedAt(LocalDateTime.now());
            if (dto.getNewsCategoryId() != null) {
                NewsCategory newsCategory = newsCategoryRepository.findById(dto.getNewsCategoryId())
                    .orElseThrow(() -> new RuntimeException("NewsCategory not found"));
                news.setNewsCategory(newsCategory);
            }
            return newsRepository.save(news);
        });
    }

    public boolean deleteNews(Integer id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void increaseViewCount(Integer id) {
        newsRepository.findById(id).ifPresent(news -> {
            if (news.getViewCount() == null) {
                news.setViewCount(1);
            } else {
                news.setViewCount(news.getViewCount() + 1);
            }
            newsRepository.save(news);
        });
    }
}

