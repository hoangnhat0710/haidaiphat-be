package backend.base.controller;

import backend.base.domain.entity.News;
import backend.base.domain.request.NewsRequestDTO;
import backend.base.domain.response.ApiResponse;
import backend.base.domain.response.NewsListResponseDTO;
import backend.base.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<ApiResponse<NewsListResponseDTO>> getAllNews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortBy
    ) {
        Page<News> newsPage = newsService.getNewsByType(News.NewsType.GENERAL, page, size, sortBy);
        NewsListResponseDTO response = new NewsListResponseDTO(
                newsPage.getContent(),
                newsPage.getNumber(),
                newsPage.getTotalPages(),
                newsPage.getTotalElements(),
                newsPage.getSize()
        );
        return ResponseEntity.ok(new ApiResponse<>(null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<News>> getNewsById(@PathVariable Integer id) {
        return newsService.getNewsByIdAndType(id, News.NewsType.GENERAL)
                .map(news -> ResponseEntity.ok(new ApiResponse<>(null, news)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<News>> createNews(@RequestBody NewsRequestDTO newsRequestDTO) {
        return ResponseEntity.ok(new ApiResponse<>("Tạo tin tức thành công", 
                newsService.createNewsWithType(newsRequestDTO, News.NewsType.GENERAL)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<News>> updateNews(@PathVariable Integer id, @RequestBody NewsRequestDTO newsRequestDTO) {
        return newsService.updateNewsWithType(id, newsRequestDTO, News.NewsType.GENERAL)
                .map(updatedNews -> ResponseEntity.ok(new ApiResponse<>("Cập nhật tin tức thành công", updatedNews)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNews(@PathVariable Integer id) {
        if (newsService.deleteNewsWithType(id, News.NewsType.GENERAL)) {
            return ResponseEntity.ok(new ApiResponse<>("Xóa tin tức thành công", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null));
    }

    @PatchMapping("/{id}/view-count")
    public ResponseEntity<ApiResponse<Void>> increaseViewCount(@PathVariable Integer id) {
        newsService.increaseViewCountWithType(id, News.NewsType.GENERAL);
        return ResponseEntity.ok(new ApiResponse<>("Tăng view thành công", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<NewsListResponseDTO>> searchNewsByKeyword(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortBy
    ) {
        Page<News> newsPage = newsService.searchNewsByTitle(keyword, page, size, sortBy);
        NewsListResponseDTO response = new NewsListResponseDTO(
                newsPage.getContent(),
                newsPage.getNumber(),
                newsPage.getTotalPages(),
                newsPage.getTotalElements(),
                newsPage.getSize()
        );
        return ResponseEntity.ok(new ApiResponse<>(null, response));
    }
}
