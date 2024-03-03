package com.blinov.consumer.service;

import com.blinov.Data;
import com.blinov.consumer.repo.DataRepository;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    private DataRepository repository;

    public DataServiceImpl(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Data saveMessage(Data data) {
        return repository.save(data);
    }
}
