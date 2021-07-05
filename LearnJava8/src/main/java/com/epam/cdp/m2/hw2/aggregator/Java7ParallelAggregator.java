package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import javafx.util.Pair;

public class Java7ParallelAggregator implements Aggregator {

    public static ForkJoinPool forkJoinPool = new ForkJoinPool(2);

    class RecursiveAdditionTask extends RecursiveTask<Integer> {

        private List<Integer> lstNumbers;

        public RecursiveAdditionTask(List<Integer> lstNumbers) {
            this.lstNumbers = lstNumbers;
        }

        @Override
        protected Integer compute() {
            if(lstNumbers.size() > 2) {
                int result = 0;
                List<RecursiveAdditionTask> lstSublists = (List<RecursiveAdditionTask>) divideAdditionTasks();
                for(RecursiveAdditionTask recursiveTask:lstSublists){
                    result += forkJoinPool.invoke(recursiveTask);
                }
                return result;
            }
            return sum();
        }

        private Collection<RecursiveAdditionTask> divideAdditionTasks() {
            List<RecursiveAdditionTask> lstSublists = new ArrayList<>();
            lstSublists.add(new RecursiveAdditionTask(lstNumbers.subList(0,lstNumbers.size()/2)));
            lstSublists.add(new RecursiveAdditionTask(lstNumbers.subList(lstNumbers.size()/2, lstNumbers.size())));
            return lstSublists;
        }

        private Integer sum() {
            int result = 0;
            for(Integer number: lstNumbers){
                result += number;
            }
            return result;
        }
    }

    class RecursiveWordFrequencyTask extends RecursiveTask<Map<String, Long>> {

        private List<String> lstWords;

        public RecursiveWordFrequencyTask(List<String> lstWords) {
            this.lstWords = lstWords;
        }

        @Override
        protected Map<String, Long> compute() {
            if(lstWords.size() > 2) {
                Map<String, Long> mWordsFrequency = new HashMap<>();
                List<RecursiveWordFrequencyTask> lstSublists = (List<RecursiveWordFrequencyTask>) divideWordFrequencyTasks();
                for(RecursiveWordFrequencyTask recursiveTask:lstSublists){
                    Map<String, Long> mWordsFrequencyTemp = forkJoinPool.invoke(recursiveTask);
                    for(Map.Entry<String, Long> entry: mWordsFrequencyTemp.entrySet()) {
                        mWordsFrequency.put(entry.getKey(), entry.getValue() + (mWordsFrequency.containsKey(entry.getKey())?mWordsFrequency.get(entry.getKey()):0));
                    }
                }
                return mWordsFrequency;
            }
            return getFrequency();
        }

        private Collection<RecursiveWordFrequencyTask> divideWordFrequencyTasks() {
            List<RecursiveWordFrequencyTask> lstSublists = new ArrayList<>();
            lstSublists.add(new RecursiveWordFrequencyTask(lstWords.subList(0,lstWords.size()/2)));
            lstSublists.add(new RecursiveWordFrequencyTask(lstWords.subList(lstWords.size()/2, lstWords.size())));
            return lstSublists;
        }

        private Map<String, Long> getFrequency() {
            Map<String, Long> mWordsFrequency = new HashMap<>();
            for(String word: lstWords) {
                if(mWordsFrequency.containsKey(word)) {
                    mWordsFrequency.put(word, mWordsFrequency.get(word)+1);
                } else{
                    mWordsFrequency.put(word, 1L);
                }
            }
            return mWordsFrequency;
        }
    }

    class RecursiveDuplicatedWordTask extends RecursiveTask<Map<String, Boolean>> {

        private List<String> lstWords;

        public RecursiveDuplicatedWordTask(List<String> lstWords) {
            this.lstWords = lstWords;
        }

        @Override
        protected Map<String, Boolean> compute() {
            if(lstWords.size() > 2) {
                Map<String, Boolean> mDuplicatedWords = new HashMap<>();
                List<RecursiveDuplicatedWordTask> lstSublists = (List<RecursiveDuplicatedWordTask>) divideDuplicatedWordTasks();
                for(RecursiveDuplicatedWordTask recursiveTask:lstSublists){
                    Map<String, Boolean> mDuplicatedWordsTemp = forkJoinPool.invoke(recursiveTask);
                    for(Map.Entry<String, Boolean> entry: mDuplicatedWordsTemp.entrySet()) {
                        mDuplicatedWords.put(entry.getKey(), entry.getValue() || mDuplicatedWords.containsKey(entry.getKey()));
                    }
                }
                return mDuplicatedWords;
            }
            return getDuplicated();
        }

        private Collection<RecursiveDuplicatedWordTask> divideDuplicatedWordTasks() {
            List<RecursiveDuplicatedWordTask> lstSublists = new ArrayList<>();
            lstSublists.add(new RecursiveDuplicatedWordTask(lstWords.subList(0,lstWords.size()/2)));
            lstSublists.add(new RecursiveDuplicatedWordTask(lstWords.subList(lstWords.size()/2, lstWords.size())));
            return lstSublists;
        }

        private Map<String, Boolean> getDuplicated() {
            Map<String, Boolean> mDuplicatedWords = new HashMap<>();
            for(String word: lstWords) {
                String upperWord = word.toUpperCase();
                if(mDuplicatedWords.containsKey(upperWord)) {
                    mDuplicatedWords.put(upperWord, Boolean.TRUE);
                } else{
                    mDuplicatedWords.put(upperWord, Boolean.FALSE);
                }
            }
            return mDuplicatedWords;
        }
    }

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
        RecursiveAdditionTask recursiveAdditionTask = new RecursiveAdditionTask(numbers);
        return forkJoinPool.invoke(recursiveAdditionTask);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        RecursiveWordFrequencyTask recursiveWordFrequencyTask = new RecursiveWordFrequencyTask(words);
        Map<String, Long> mWordsFrequency = forkJoinPool.invoke(recursiveWordFrequencyTask);
        List<Pair<String, Long>> lstFrequency = new ArrayList<>();
        for(Map.Entry<String, Long> entry :mWordsFrequency.entrySet()) {
            lstFrequency.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        Collections.sort(lstFrequency,new MostFrequentWordComparator());
        return lstFrequency.size()<limit?lstFrequency:lstFrequency.subList(0,(int)limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        RecursiveDuplicatedWordTask recursiveDuplicatedWordTask = new RecursiveDuplicatedWordTask(words);
        Map<String, Boolean> mDuplicatedWords = forkJoinPool.invoke(recursiveDuplicatedWordTask);
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
