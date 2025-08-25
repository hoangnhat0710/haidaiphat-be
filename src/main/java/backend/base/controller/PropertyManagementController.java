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
@RequestMapping("/property-management")
public class PropertyManagementController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<ApiResponse<NewsListResponseDTO>> getAllPropertyManagement(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<News> newsPage = newsService.getNewsByType(News.NewsType.PROPERTY_MANAGEMENT, page, size);
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
    public ResponseEntity<ApiResponse<News>> getPropertyManagementById(@PathVariable Integer id) {
        return newsService.getNewsByIdAndType(id, News.NewsType.PROPERTY_MANAGEMENT)
                .map(news -> ResponseEntity.ok(new ApiResponse<>(null, news)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<News>> createPropertyManagement(@RequestBody NewsRequestDTO newsRequestDTO) {
        return ResponseEntity.ok(new ApiResponse<>("Tạo tin tức thành công", 
                newsService.createNewsWithType(newsRequestDTO, News.NewsType.PROPERTY_MANAGEMENT)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<News>> updatePropertyManagement(@PathVariable Integer id, @RequestBody NewsRequestDTO newsRequestDTO) {
        return newsService.updateNewsWithType(id, newsRequestDTO, News.NewsType.PROPERTY_MANAGEMENT)
                .map(updatedNews -> ResponseEntity.ok(new ApiResponse<>("Cập nhật tin tức thành công", updatedNews)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePropertyManagement(@PathVariable Integer id) {
        if (newsService.deleteNewsWithType(id, News.NewsType.PROPERTY_MANAGEMENT)) {
            return ResponseEntity.ok(new ApiResponse<>("Xóa tin tức thành công", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Không tìm thấy tin tức", null));
    }

    @PatchMapping("/{id}/view-count")
    public ResponseEntity<ApiResponse<Void>> increaseViewCount(@PathVariable Integer id) {
        newsService.increaseViewCountWithType(id, News.NewsType.PROPERTY_MANAGEMENT);
        return ResponseEntity.ok(new ApiResponse<>("Tăng view thành công", null));
    }
} 