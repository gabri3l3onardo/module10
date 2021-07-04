package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Override
    public int sum(List<Integer> numbers) {
        RecursiveAdditionTask recursiveAdditionTask = new RecursiveAdditionTask(numbers);
        return forkJoinPool.invoke(recursiveAdditionTask);
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
