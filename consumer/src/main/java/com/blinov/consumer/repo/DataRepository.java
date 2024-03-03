package com.blinov.consumer.repo;

import com.blinov.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Data, String> {
}
