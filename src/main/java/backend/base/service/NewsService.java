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

    // Methods for filtering by type
    public Page<News> getNewsByType(News.NewsType type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return newsRepository.findByType(type, pageable);
    }

    public Optional<News> getNewsByIdAndType(Integer id, News.NewsType type) {
        return newsRepository.findByNewsIdAndType(id, type);
    }

    public News createNewsWithType(NewsRequestDTO dto, News.NewsType type) {
        News news = new News();
        news.setTitle(dto.getTitle());
        news.setImageUrl(dto.getImageUrl());
        news.setMetaTitle(dto.getMetaTitle());
        news.setMetaDescription(dto.getMetaDescription());
        news.setDescription(dto.getDescription());
        news.setContentHtml(dto.getContentHtml());
        news.setTags(dto.getTags());
        news.setKeywords(dto.getKeywords());
        news.setType(type);
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

    public Optional<News> updateNewsWithType(Integer id, NewsRequestDTO dto, News.NewsType type) {
        return newsRepository.findByNewsIdAndType(id, type).map(news -> {
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

    public boolean deleteNewsWithType(Integer id, News.NewsType type) {
        Optional<News> news = newsRepository.findByNewsIdAndType(id, type);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void increaseViewCountWithType(Integer id, News.NewsType type) {
        newsRepository.findByNewsIdAndType(id, type).ifPresent(news -> {
            if (news.getViewCount() == null) {
                news.setViewCount(1);
            } else {
                news.setViewCount(news.getViewCount() + 1);
            }
            newsRepository.save(news);
        });
    }

    // Search news by title
    public Page<News> searchNewsByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return newsRepository.searchByTitle(title, pageable);
    }

    // Search news by title with type filter
    public Page<News> searchNewsByTitleAndType(String title, News.NewsType type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return newsRepository.searchByTitleAndType(title, type, pageable);
    }
}

