package backend.base.service;

import backend.base.domain.entity.JobPosting;
import backend.base.domain.exception.ServiceException;
import backend.base.domain.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    public JobPosting save(JobPosting jobPosting) {
        jobPosting.setCreatedAt(LocalDateTime.now());
        jobPosting.setModifiedAt(LocalDateTime.now());
        return jobPostingRepository.save(jobPosting);
    }

    public JobPosting getById(Integer id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Job posting not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    public Page<JobPosting> getAllSortedByNewest(Pageable pageable, Integer departmentId) {
        if (departmentId != null) {
            return jobPostingRepository.findAllByDepartment_DepartmentIdOrderByCreatedAtDesc(departmentId, pageable);
        }
        return jobPostingRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public JobPosting update(Integer id, JobPosting updatedJob) {
        JobPosting existing = getById(id);
        updatedJob.setId(id);
        updatedJob.setCreatedAt(existing.getCreatedAt());
        updatedJob.setModifiedAt(LocalDateTime.now());
        return jobPostingRepository.save(updatedJob);
    }

    public void delete(Integer id) {
        if (!jobPostingRepository.existsById(id)) {
            throw new ServiceException("Job posting not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        jobPostingRepository.deleteById(id);
    }
}
