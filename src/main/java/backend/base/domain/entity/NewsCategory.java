package backend.base.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "news_categories")
@Data
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
} 