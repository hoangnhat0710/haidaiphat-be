package backend.base.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
@Data
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String title; // Tên vị trí cần tuyển dụng

    private String city;

    private String workingType; // Hình thức làm việc (Full / Part / Intern / CTV)

    private Integer quantity; // Số lượng tuyển

    private String salary; // Thu nhập (tiền hoặc thỏa thuận)
    private String genderRequirement; // Giới tính
    @Column(columnDefinition = "TEXT")
    private String experienceRequirement; // Kinh nghiệm
    @Column(columnDefinition = "TEXT")
    private String jobDetailHtml; // Chi tiết công việc
    @Column(columnDefinition = "TEXT")
    private String candidateRequirementHtml; // Yêu cầu ứng viên
    @Column(columnDefinition = "TEXT")
    private String benefitHtml; // Quyền lợi
    private String workingTime; // Thời gian làm việc
    private LocalDateTime deadline; // Hạn nộp hồ sơ

    @Column(columnDefinition = "TEXT")
    private String receiverEmail; // Email người nhận hồ sơ
    @Column(columnDefinition = "TEXT")
    private String receiverPhone; // SDT người nhận hồ sơ
    @Column(columnDefinition = "TEXT")
    private String receiverName; // Người nhận hồ sơ
    private String applicationMethod; // Cách thức ứng tuyển -> Email / gọi trực tiếp / Nhập form

    @Column(columnDefinition = "TEXT")
    private String contactPhone; // Liên hệ
    @Column(columnDefinition = "TEXT")
    private String contactEmail;

    @Column(columnDefinition = "TEXT")
    private String insuranceBenefit;     // Chế độ bảo hiểm
    @Column(columnDefinition = "TEXT")
    private String healthBenefit;        // Chăm sóc sức khỏe
    @Column(columnDefinition = "TEXT")
    private String bonusBenefit;         // Chế độ thưởng

    @ManyToOne
    @JoinColumn(name = "department_id")
    private JobDepartment department;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
