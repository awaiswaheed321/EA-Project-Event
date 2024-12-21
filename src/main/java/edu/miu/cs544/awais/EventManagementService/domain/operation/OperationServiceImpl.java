package edu.miu.cs544.awais.EventManagementService.domain.operation;

import edu.miu.cs544.awais.EventManagementService.domain.operation.domain.Operation;
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
        operation.setPrinciple(SecurityUtils.getPrincipalUsername());
        operation.setOperation(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        operationLoggerRepository.save(operation);
    }
}
