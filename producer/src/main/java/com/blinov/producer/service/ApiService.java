package com.blinov.producer.service;

import com.blinov.Data;

import java.util.List;

public interface ApiService {
    List<Data> getData(Integer limit);
}
