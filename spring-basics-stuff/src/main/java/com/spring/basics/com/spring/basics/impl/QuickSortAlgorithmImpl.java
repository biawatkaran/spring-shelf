package com.spring.basics.com.spring.basics.impl;

import org.springframework.stereotype.Component;

@Component
public class QuickSortAlgorithmImpl implements SortAlgorithm{

    @Override
    public int sort(int[] numbers) {

        System.out.println("Quick Sort Algorithm Started");
        return 2;
    }
}
