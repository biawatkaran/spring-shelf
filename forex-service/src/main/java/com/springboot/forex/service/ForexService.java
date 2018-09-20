package com.springboot.forex.service;

import com.springboot.forex.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ForexService {

    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    private Environment environment;

    public ExchangeValue retrieveExchangeValue(String from, String to){

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;

    }
}
