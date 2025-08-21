package backend.base.domain.response;

import backend.base.domain.entity.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingListResponseDTO {
    private List<JobPosting> content;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;

    public JobPostingListResponseDTO(List<JobPosting> content, int currentPage, int totalPages, long totalItems, int pageSize) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.pageSize = pageSize;
    }
} 