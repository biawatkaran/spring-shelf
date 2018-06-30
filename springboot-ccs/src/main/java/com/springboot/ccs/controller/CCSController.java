package com.springboot.ccs.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.ccs.model.CurrencyConversion;
import com.springboot.ccs.service.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CCSController {

    @Value(value = "${forex.url}")
    private String forexUrl;

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(forexUrl, CurrencyConversion.class, uriVariables);

        CurrencyConversion response = responseEntity.getBody();

        return new CurrencyConversion(response.getId(), from, to, response.getConversionRate(), quantity,
                quantity.multiply(response.getConversionRate()), response.getPort());

    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable("from") String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(response.getId(), from, to, response.getConversionRate(), quantity,
                quantity.multiply(response.getConversionRate()), response.getPort());

    }

    public CurrencyConversion fallback(String from, String to, BigDecimal quantity){

        return new CurrencyConversion();
    }
}
