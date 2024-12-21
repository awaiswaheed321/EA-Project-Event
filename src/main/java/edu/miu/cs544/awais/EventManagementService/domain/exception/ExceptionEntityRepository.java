package edu.miu.cs544.awais.EventManagementService.domain.exception;

import edu.miu.cs544.awais.EventManagementService.domain.exception.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionEntityRepository extends JpaRepository<ExceptionEntity, Long> {
}
