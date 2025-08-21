package backend.base.domain.repository;

import backend.base.domain.entity.JobDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDepartmentRepository extends JpaRepository<JobDepartment, Integer> {
}
