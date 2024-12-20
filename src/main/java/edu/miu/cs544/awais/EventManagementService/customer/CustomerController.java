package edu.miu.cs544.awais.EventManagementService.customer;

import edu.miu.cs544.awais.EventManagementService.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.customer.dto.CreateCustomerDTO;
import edu.miu.cs544.awais.EventManagementService.customer.dto.UpdateCustomerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}