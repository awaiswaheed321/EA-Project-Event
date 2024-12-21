package edu.miu.cs544.awais.EventManagementService.domain.customer;

import edu.miu.cs544.awais.EventManagementService.domain.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.domain.customer.dto.CreateCustomerDTO;
import edu.miu.cs544.awais.EventManagementService.domain.customer.dto.UpdateCustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<Customer> createCustomer(CreateCustomerDTO customerDTO);

    ResponseEntity<Customer> getCustomerById(Long id);

    ResponseEntity<Customer> updateCustomer(Long id, UpdateCustomerDTO customerDTO);

    ResponseEntity<Void> deleteCustomer(Long id);

    ResponseEntity<List<Customer>> getAllCustomers();
}
