package backend.base.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsId;

    private String title; // Tiêu đề bài viết
    private String imageUrl; // Ảnh thumb

    private String metaTitle; // SEO meta title
    private String metaDescription; // SEO meta description

    private String description; // Mô tả ngắn

    @Column(columnDefinition = "TEXT")
    private String contentHtml; // Nội dung HTML

    @ManyToOne
    @JoinColumn(name = "news_category_id")
    private NewsCategory newsCategory;

    private String tags; // Dạng chuỗi: "bds,dat nen"
    private String keywords; // Dạng chuỗi: "mua bán nhà, đầu tư"

    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}