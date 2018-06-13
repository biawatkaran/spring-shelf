package com.spring.basics.com.spring.basics.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BubbleSortAlgorithmImpl implements SortAlgorithm {

    @Override
    public int sort(int[] numbers) {

        System.out.println("Bubble Sort Algorithm Started");
        return 1;
    }
}
