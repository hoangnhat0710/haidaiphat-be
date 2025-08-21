package backend.base.domain.repository;


import backend.base.domain.entity.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Page<JobPosting> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<JobPosting> findAllByDepartment_DepartmentIdOrderByCreatedAtDesc(Integer departmentId, Pageable pageable);
}