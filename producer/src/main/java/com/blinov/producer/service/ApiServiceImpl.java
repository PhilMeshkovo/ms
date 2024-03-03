package com.blinov.producer.service;

import com.blinov.Data;
import com.blinov.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private RestTemplate restTemplate;
//    private String apiUrl;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
//        this.apiUrl = apiUrl;
    }

    public List<Data> getData(Integer limit) {
//        UriComponentsBuilder builder = UriComponentsBuilder
//                .fromUriString(apiUrl)
//                .queryParam("limit", limit);
//
//        UserData data = restTemplate.getForObject(builder.toUriString(), UserData.class);
        Data data1 = new Data("Phil-11", "111@mail.ru", "123");
        UserData data = new UserData();
        data.setData(List.of(data1));
        return data.getData();
    }

}
