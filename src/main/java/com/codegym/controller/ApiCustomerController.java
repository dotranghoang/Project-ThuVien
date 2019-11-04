package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Job;
import com.codegym.service.CustomerService;
import com.codegym.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiCustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private JobService jobService;

    @GetMapping("/api/customer")
    public ResponseEntity<List<Customer>> getCustomerList(){
        List<Customer> customers = (List<Customer>) customerService.findAll();

        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PostMapping("/api/customer")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        Job job = jobService.findById(customer.getJobId());

        customer.setJob(job);
        customerService.save(customer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/customer/{id}")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer, @PathVariable Long id){
        Customer customer1 = customerService.findById(id);
        if(customer1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Job job = jobService.findById(customer.getJobId());

        customer1.setName(customer.getName());
        customer1.setBirthday(customer.getBirthday());
        customer1.setAddress(customer.getAddress());
        customer1.setJob(job);
        customer1.setJobId(customer.getJobId());
        customerService.save(customer1);

        return new ResponseEntity<>(customer1,HttpStatus.OK);
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
