package backend.base.domain.request;

import lombok.Data;

@Data
public class NewsRequestDTO {
    private String title;
    private String imageUrl;
    private String metaTitle;
    private String metaDescription;
    private String description;
    private String contentHtml;
    private Integer newsCategoryId;
    private String tags;
    private String keywords;
} 