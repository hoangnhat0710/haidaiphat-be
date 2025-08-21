package backend.base.controller;

import backend.base.domain.entity.JobDepartment;
import backend.base.domain.repository.JobDepartmentRepository;
import backend.base.domain.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job-departments")
public class JobDepartmentController {
    @Autowired
    private JobDepartmentRepository jobDepartmentRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobDepartment>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(null, jobDepartmentRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JobDepartment>> create(@RequestBody JobDepartment jobDepartment) {
        JobDepartment saved = jobDepartmentRepository.save(jobDepartment);
        return ResponseEntity.ok(new ApiResponse<>("Tạo phòng ban thành công", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobDepartment>> update(@PathVariable Integer id, @RequestBody JobDepartment jobDepartment) {
        Optional<JobDepartment> opt = jobDepartmentRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Không tìm thấy phòng ban", null));
        }
        JobDepartment existing = opt.get();
        existing.setName(jobDepartment.getName());
        existing.setModifiedAt(jobDepartment.getModifiedAt());
        JobDepartment saved = jobDepartmentRepository.save(existing);
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật phòng ban thành công", saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        if (!jobDepartmentRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Không tìm thấy phòng ban", null));
        }
        jobDepartmentRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("Xoá phòng ban thành công", null));
    }
} 