package com.epam.cdp.m2.hw2.aggregator;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        BinaryOperator<Integer> boAddition = (num1, num2) -> num1 + num2;
        Optional<Integer> opResult =  numbers.stream().reduce(boAddition);
        return opResult.orElse(0);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}