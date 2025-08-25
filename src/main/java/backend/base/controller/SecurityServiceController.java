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
@RequestMapping("/security-service")
public class SecurityServiceController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<ApiResponse<NewsListResponseDTO>> getAllSecurityService(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<News> newsPage = newsService.getNewsByType(News.NewsType.SECURITY_SERVICE, page, size);
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
    public ResponseEntity<ApiResponse<News>> getSecurityServiceById(@PathVariable Integer id) {
        return newsService.getNewsByIdAndType(id, News.NewsType.SECURITY_SERVICE)
                .map(news -> ResponseEntity.ok(new ApiResponse<>(null, news)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<News>> createSecurityService(@RequestBody NewsRequestDTO newsRequestDTO) {
        return ResponseEntity.ok(new ApiResponse<>("Tạo tin tức thành công", 
                newsService.createNewsWithType(newsRequestDTO, News.NewsType.SECURITY_SERVICE)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<News>> updateSecurityService(@PathVariable Integer id, @RequestBody NewsRequestDTO newsRequestDTO) {
        return newsService.updateNewsWithType(id, newsRequestDTO, News.NewsType.SECURITY_SERVICE)
                .map(updatedNews -> ResponseEntity.ok(new ApiResponse<>("Cập nhật tin tức thành công", updatedNews)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSecurityService(@PathVariable Integer id) {
        if (newsService.deleteNewsWithType(id, News.NewsType.SECURITY_SERVICE)) {
            return ResponseEntity.ok(new ApiResponse<>("Xóa tin tức thành công", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null));
    }

    @PatchMapping("/{id}/view-count")
    public ResponseEntity<ApiResponse<Void>> increaseViewCount(@PathVariable Integer id) {
        newsService.increaseViewCountWithType(id, News.NewsType.SECURITY_SERVICE);
        return ResponseEntity.ok(new ApiResponse<>("Tăng view thành công", null));
    }
} 