package backend.base.controller;

import backend.base.domain.entity.JobDepartment;
import backend.base.domain.entity.JobPosting;
import backend.base.domain.repository.JobDepartmentRepository;
import backend.base.domain.request.JobPostingRequestDTO;
import backend.base.domain.response.ApiResponse;
import backend.base.domain.response.JobPostingListResponseDTO;
import backend.base.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-postings")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

    @Autowired
    private JobDepartmentRepository jobDepartmentRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<JobPosting>> createJobPosting(@RequestBody JobPostingRequestDTO dto) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setTitle(dto.getTitle());
        jobPosting.setCity(dto.getCity());
        jobPosting.setWorkingType(dto.getWorkingType());
        jobPosting.setQuantity(dto.getQuantity());
        jobPosting.setSalary(dto.getSalary());
        jobPosting.setGenderRequirement(dto.getGenderRequirement());
        jobPosting.setExperienceRequirement(dto.getExperienceRequirement());
        jobPosting.setJobDetailHtml(dto.getJobDetailHtml());
        jobPosting.setCandidateRequirementHtml(dto.getCandidateRequirementHtml());
        jobPosting.setBenefitHtml(dto.getBenefitHtml());
        jobPosting.setWorkingTime(dto.getWorkingTime());
        jobPosting.setDeadline(dto.getDeadline());
        jobPosting.setReceiverEmail(dto.getReceiverEmail());
        jobPosting.setReceiverPhone(dto.getReceiverPhone());
        jobPosting.setReceiverName(dto.getReceiverName());
        jobPosting.setApplicationMethod(dto.getApplicationMethod());
        jobPosting.setContactPhone(dto.getContactPhone());
        jobPosting.setContactEmail(dto.getContactEmail());
        jobPosting.setInsuranceBenefit(dto.getInsuranceBenefit());
        jobPosting.setHealthBenefit(dto.getHealthBenefit());
        jobPosting.setBonusBenefit(dto.getBonusBenefit());
        jobPosting.setCreatedAt(dto.getCreatedAt());
        jobPosting.setModifiedAt(dto.getModifiedAt());
        JobDepartment department = jobDepartmentRepository.findById(dto.getDepartment_id())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng ban"));
        jobPosting.setDepartment(department);
        return ResponseEntity.ok(new ApiResponse<>("Tạo tin tuyển dụng thành công", jobPostingService.save(jobPosting)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPosting>> getJobPostingById(@PathVariable Integer id) {
        return ResponseEntity.ok(new ApiResponse<>(null, jobPostingService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<JobPostingListResponseDTO>> getAllJobPostings(Pageable pageable, @RequestParam(required = false) Integer department_id) {
        Page<JobPosting> jobPostingPage = jobPostingService.getAllSortedByNewest(pageable, department_id);
        JobPostingListResponseDTO response = new JobPostingListResponseDTO(
            jobPostingPage.getContent(),
            jobPostingPage.getNumber(),
            jobPostingPage.getTotalPages(),
            jobPostingPage.getTotalElements(),
            jobPostingPage.getSize()
        );
        return ResponseEntity.ok(new ApiResponse<>(null, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPosting>> updateJobPosting(@PathVariable Integer id, @RequestBody JobPostingRequestDTO dto) {
        JobPosting updatedJob = new JobPosting();
        updatedJob.setTitle(dto.getTitle());
        updatedJob.setCity(dto.getCity());
        updatedJob.setWorkingType(dto.getWorkingType());
        updatedJob.setQuantity(dto.getQuantity());
        updatedJob.setSalary(dto.getSalary());
        updatedJob.setGenderRequirement(dto.getGenderRequirement());
        updatedJob.setExperienceRequirement(dto.getExperienceRequirement());
        updatedJob.setJobDetailHtml(dto.getJobDetailHtml());
        updatedJob.setCandidateRequirementHtml(dto.getCandidateRequirementHtml());
        updatedJob.setBenefitHtml(dto.getBenefitHtml());
        updatedJob.setWorkingTime(dto.getWorkingTime());
        updatedJob.setDeadline(dto.getDeadline());
        updatedJob.setReceiverEmail(dto.getReceiverEmail());
        updatedJob.setReceiverPhone(dto.getReceiverPhone());
        updatedJob.setReceiverName(dto.getReceiverName());
        updatedJob.setApplicationMethod(dto.getApplicationMethod());
        updatedJob.setContactPhone(dto.getContactPhone());
        updatedJob.setContactEmail(dto.getContactEmail());
        updatedJob.setInsuranceBenefit(dto.getInsuranceBenefit());
        updatedJob.setHealthBenefit(dto.getHealthBenefit());
        updatedJob.setBonusBenefit(dto.getBonusBenefit());
        updatedJob.setCreatedAt(dto.getCreatedAt());
        updatedJob.setModifiedAt(dto.getModifiedAt());
        JobDepartment department = jobDepartmentRepository.findById(dto.getDepartment_id())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng ban"));
        updatedJob.setDepartment(department);
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật tin tuyển dụng thành công", jobPostingService.update(id, updatedJob)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJobPosting(@PathVariable Integer id) {
        jobPostingService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>("Xóa tin tuyển dụng thành công", null));
    }
}
