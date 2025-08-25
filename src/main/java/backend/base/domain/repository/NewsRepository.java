package backend.base.domain.repository;

import backend.base.domain.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    
    Page<News> findByType(News.NewsType type, Pageable pageable);
    
    Optional<News> findByNewsIdAndType(Integer newsId, News.NewsType type);
}