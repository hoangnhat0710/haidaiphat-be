package backend.base.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_departments")
@Data
public class JobDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}