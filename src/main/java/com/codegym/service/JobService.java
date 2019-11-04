package com.codegym.service;

import com.codegym.model.Job;

public interface JobService {
    Iterable<Job> findAll();

    Job findById(Long id);

    void remove(Long id);

    void save(Job job);
}
