package edu.miu.cs544.awais.EventManagementService.domain.operation;

import edu.miu.cs544.awais.EventManagementService.domain.operation.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
