package com.codegym.service.Impl;

import com.codegym.model.Job;
import com.codegym.repository.JobRepository;
import com.codegym.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;


    @Override
    public Iterable<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        jobRepository.delete(id);
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }
}
