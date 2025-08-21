package backend.base.domain.response;

import backend.base.domain.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsListResponseDTO {
    private List<News> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
} 