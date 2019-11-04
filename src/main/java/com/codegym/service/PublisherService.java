package com.codegym.service;

import com.codegym.model.Publisher;

public interface PublisherService {
    Iterable<Publisher> findAll();

    Publisher findById(Long id);

    void remove(Long id);

    void save(Publisher publisher);
}
