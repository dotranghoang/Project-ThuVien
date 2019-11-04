package com.codegym.repository;

import com.codegym.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobRepository extends PagingAndSortingRepository<Job,Long> {
}
