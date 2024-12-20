package edu.miu.cs544.awais.EventManagementService.customer;

import edu.miu.cs544.awais.EventManagementService.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
}
