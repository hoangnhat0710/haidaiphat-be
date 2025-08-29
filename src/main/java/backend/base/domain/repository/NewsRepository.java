package backend.base.domain.repository;

import backend.base.domain.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    
    Page<News> findByType(News.NewsType type, Pageable pageable);
    
    Optional<News> findByNewsIdAndType(Integer newsId, News.NewsType type);
    
    // Search by title with case-insensitive LIKE
    @Query("SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<News> searchByTitle(@Param("title") String title, Pageable pageable);
    
    // Search by title with type filter
    @Query("SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%')) AND n.type = :type")
    Page<News> searchByTitleAndType(@Param("title") String title, @Param("type") News.NewsType type, Pageable pageable);
}