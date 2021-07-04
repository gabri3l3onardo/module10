package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Function<String, Long> funcFrequency = (word) -> 1L;
        Map<String,Long> mWordsFrequency = words.stream()
                .collect(
                        Collectors.toMap(Function.identity(), funcFrequency, (freq1, freq2) -> freq1+freq2));
        return mWordsFrequency.entrySet().stream()
                .map(entry -> new Pair<String, Long>(entry.getKey(), entry.getValue()))
                .sorted((pair1, pair2) -> {
                    int freqComparator = pair2.getValue().compareTo(pair1.getValue());
                    if(freqComparator == 0) {
                        return pair1.getKey().compareTo(pair2.getKey());
                    }
                    return freqComparator;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}