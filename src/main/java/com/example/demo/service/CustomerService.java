package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.User;
import com.example.demo.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //IMPLEMENTER PBKDF2


    public Customer getCustomer(Customer customer){
        Optional<Customer> getUser = customerRepository.findById(customer.getId());
        if(getUser.isEmpty()){
            new User();
        }
        return getUser.get();
    }

    public List<Customer> getAllCustomers(){
        List<Customer> allUsers = customerRepository.findAll();
        return allUsers;
    }


    public boolean createCustomer(Customer customer){
        customerRepository.save(customer);
        return !customerRepository.findById(customer.getId()).isEmpty();
    }

    public boolean editCustomer(Customer customer){
        Optional<Customer> existingUser = customerRepository.findById(customer.getId());
        if(existingUser.isEmpty()){
            return false;
        }
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setUserId(existingUser.get().getUserId());
        newCustomer.setFirstName(existingUser.get().getFirstName());
        newCustomer.setLastName(existingUser.get().getLastName());
        newCustomer.setReservation(existingUser.get().getReservation());
        customerRepository.save(customer);
        return true;
    }

    public boolean deleteCustomer(Customer customer){
        Long customerId = customer.getId();
        customerRepository.delete(customer);
        return !customerRepository.findById(customerId).isEmpty();
    }
}




