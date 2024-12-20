package edu.miu.cs544.awais.EventManagementService.customer;

import edu.miu.cs544.awais.EventManagementService.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.customer.dto.CreateCustomerDTO;
import edu.miu.cs544.awais.EventManagementService.customer.dto.UpdateCustomerDTO;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.exception.custom.UnauthorizedAccessException;
import edu.miu.cs544.awais.EventManagementService.security.SecurityUtils;
import edu.miu.cs544.awais.EventManagementService.shared.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(CreateCustomerDTO customerDTO) {
        checkEmailExists(customerDTO.getEmail());
        Customer customer = customerFactory.createCustomer(customerDTO);
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
    }

    private void checkEmailExists(String email) {
        if(customerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        SecurityUtils.verifyCustomer(id);
        return ResponseEntity.ok(findCustomerById(id));
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Long id, UpdateCustomerDTO customerDTO) {
        SecurityUtils.verifyCustomer(id);
        Customer customer = findCustomerById(id);
        customerFactory.updateCustomerData(customer, customerDTO);
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    private Customer findCustomerById(Long emId) {
        return customerRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Customer not found: " + emId));
    }
}
