package edu.miu.cs544.awais.EventManagementService.domain.customer;

import edu.miu.cs544.awais.EventManagementService.domain.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
}
