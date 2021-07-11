package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;

import com.epam.util.PerformanceTest;
import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    class MostFrequentWordComparator implements Comparator<Pair<String, Long>> {

        @Override
        public int compare(Pair<String, Long> pair1, Pair<String, Long> pair2) {
            int freqComparator = pair2.getValue().compareTo(pair1.getValue());
            if(freqComparator == 0){
                return pair1.getKey().compareTo(pair2.getKey());
            }
            return freqComparator;
        }
    }

    class DuplicatedComparator implements Comparator<String> {

        @Override
        public int compare(String word1, String word2) {
            int lengthsComparison = Integer.compare(word1.length(),word2.length());
            if(lengthsComparison == 0){
                return word1.compareTo(word2);
            }
            return lengthsComparison;
        }
    }

    @Override
    public int sum(List<Integer> numbers) {
        int result = 0;
        for(int number:numbers) {
            result += number;
        }
        return result;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> mWordsFrequency = new HashMap<>();
        for(String word: words) {
            if(mWordsFrequency.containsKey(word)) {
                mWordsFrequency.put(word, mWordsFrequency.get(word)+1);
            } else{
                mWordsFrequency.put(word, 1L);
            }
        }
        List<Pair<String, Long>> lstFrequency = new ArrayList<>();
        for(Map.Entry<String, Long> entry :mWordsFrequency.entrySet()) {
            lstFrequency.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        Collections.sort(lstFrequency,new MostFrequentWordComparator());
        return lstFrequency.size()<limit?lstFrequency:lstFrequency.subList(0,(int)limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Map<String, Boolean> mDuplicatedWords = new HashMap<>();
        for(String word: words) {
            String upperWord = word.toUpperCase();
            if(mDuplicatedWords.containsKey(upperWord)) {
                mDuplicatedWords.put(upperWord, Boolean.TRUE);
            } else{
                mDuplicatedWords.put(upperWord, Boolean.FALSE);
            }
        }
        List<String> lstDuplicatedWords = new ArrayList<>();
        for(Map.Entry<String, Boolean> entry:mDuplicatedWords.entrySet()){
            if(entry.getValue()){
                lstDuplicatedWords.add(entry.getKey());
            }
        }
        Collections.sort(lstDuplicatedWords, new DuplicatedComparator());
        return lstDuplicatedWords.size()<limit?lstDuplicatedWords:lstDuplicatedWords.subList(0,(int)limit);
    }
}
