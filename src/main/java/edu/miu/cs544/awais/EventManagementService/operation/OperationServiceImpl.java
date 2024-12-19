package edu.miu.cs544.awais.EventManagementService.operation;

import edu.miu.cs544.awais.EventManagementService.operation.domain.Operation;
import edu.miu.cs544.awais.EventManagementService.security.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationLoggerRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationLoggerRepository) {
        this.operationLoggerRepository = operationLoggerRepository;
    }

    @Override
    public void log(JoinPoint joinPoint) {
        Operation operation = new Operation();
        operation.setPrinciple(SecurityUtils.getUsername());
        operation.setOperation(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        operationLoggerRepository.save(operation);
    }
}
