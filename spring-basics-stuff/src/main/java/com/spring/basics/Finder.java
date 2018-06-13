package com.spring.basics;

import com.spring.basics.com.spring.basics.impl.SortAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Finder {

    @Autowired
    public SortAlgorithm algorithm;

    /*
        1. By constructor

        public Finder(SortAlgorithm algorithm) {

        this.algorithm = algorithm;

        2. By setters
        @Autowired
        public void setAlgorithm(SortAlgorithm algorithm) {
        this.algorithm = algorithm;

        3. With no setter or constructor works too ( take by setter as default from log it seems )
    }
    }*/

    public int find(int[] numbers, int key) {

        int result = this.algorithm.sort(numbers);
        return result;
    }
}
