package edu.miu.cs544.awais.EventManagementService.domain.customer;

import edu.miu.cs544.awais.EventManagementService.domain.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.domain.customer.dto.CreateCustomerDTO;
import edu.miu.cs544.awais.EventManagementService.domain.customer.dto.UpdateCustomerDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    private final PasswordEncoder passwordEncoder;

    public CustomerFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getUsername(),
                customerDTO.getEmail(),
                passwordEncoder.encode(customerDTO.getPassword()),
                customerDTO.getPhoneNumber(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getState(),
                customerDTO.getZip()
        );
    }

    public void updateCustomerData(Customer customer, UpdateCustomerDTO customerDTO) {
        if (customerDTO.getUsername() != null) {
            customer.setUsername(customerDTO.getUsername());
        }
        if (customerDTO.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        }
        if (customerDTO.getPhoneNumber() != null) {
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
        }
        if (customerDTO.getStreet() != null) {
            customer.getAddress().setStreet(customerDTO.getStreet());
        }
        if (customerDTO.getCity() != null) {
            customer.getAddress().setCity(customerDTO.getCity());
        }
        if (customerDTO.getState() != null) {
            customer.getAddress().setState(customerDTO.getState());
        }
        if (customerDTO.getZip() != null) {
            customer.getAddress().setZip(customerDTO.getZip());
        }
    }
}
