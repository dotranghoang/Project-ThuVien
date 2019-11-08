package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String job;

    @OneToMany(targetEntity = Customer.class,fetch = FetchType.EAGER)
    private List<Customer> customers;

    public Job() {
    }

    public Job(String job, List<Customer> customers) {
        this.job = job;
        this.customers = customers;
    }
public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
