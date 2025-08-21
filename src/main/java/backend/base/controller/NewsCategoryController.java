package backend.base.controller;

import backend.base.domain.entity.NewsCategory;
import backend.base.domain.repository.NewsCategoryRepository;
import backend.base.domain.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news-categories")
public class NewsCategoryController {
    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NewsCategory>>> getAllNewsCategories() {
        return ResponseEntity.ok(new ApiResponse<>(null, newsCategoryRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsCategory>> getNewsCategoryById(@PathVariable Integer id) {
        Optional<NewsCategory> newsCategory = newsCategoryRepository.findById(id);
        return newsCategory.map(value -> ResponseEntity.ok(new ApiResponse<>(null, value)))
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy news category", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NewsCategory>> createNewsCategory(@RequestBody NewsCategory newsCategory) {
        return ResponseEntity.ok(new ApiResponse<>("Tạo news category thành công", newsCategoryRepository.save(newsCategory)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NewsCategory>> updateNewsCategory(@PathVariable Integer id, @RequestBody NewsCategory newsCategory) {
        return newsCategoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(newsCategory.getName());
                    existing.setDescription(newsCategory.getDescription());
                    return ResponseEntity.ok(new ApiResponse<>("Cập nhật news category thành công", newsCategoryRepository.save(existing)));
                })
                .orElse(ResponseEntity.ok(new ApiResponse<>("Không tìm thấy news category", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNewsCategory(@PathVariable Integer id) {
        if (newsCategoryRepository.existsById(id)) {
            newsCategoryRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("Xóa news category thành công", null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Không tìm thấy news category", null));
    }
} 