package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.epam.util.PerformanceTest;
import javafx.util.Pair;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        BinaryOperator<Integer> boAddition = (num1, num2) -> num1 + num2;
        Optional<Integer> opResult =  numbers.parallelStream().reduce(boAddition);
        return opResult.orElse(0);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Function<String, Long> funcFrequency = (word) -> 1L;
        return words.parallelStream()
                .map(word -> new Pair<String, Long>(word, (long)Collections.frequency(words,word)))
                .distinct()
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
        List<String> lstWordsUppercase = words.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        return words.parallelStream()
                .map(String::toUpperCase)
                .sorted((word1, word2) -> {
                    int lengthsComparison = Integer.compare(word1.length(),word2.length());
                    if(lengthsComparison == 0){
                        return word1.compareTo(word2);
                    }
                    return lengthsComparison;
                })
                .filter(word -> Collections.frequency(lstWordsUppercase,word) > 1)
                .distinct()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
