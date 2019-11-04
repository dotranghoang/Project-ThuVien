package com.codegym.repository;

import com.codegym.model.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends PagingAndSortingRepository<Status,Long> {
}
