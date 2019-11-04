package com.codegym.service.Impl;

import com.codegym.model.Publisher;
import com.codegym.repository.PublisherRepository;
import com.codegym.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;

public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Iterable<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        publisherRepository.delete(id);
    }

    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
