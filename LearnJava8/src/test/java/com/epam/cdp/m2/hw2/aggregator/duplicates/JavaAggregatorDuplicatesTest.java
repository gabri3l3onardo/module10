package com.epam.cdp.m2.hw2.aggregator.duplicates;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.util.PerformanceTest;
import org.junit.Test;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;

public abstract class JavaAggregatorDuplicatesTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<String> expected;

    private Aggregator aggregator;

    public JavaAggregatorDuplicatesTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{3, asList("wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo","345","zzz", "wwwoooo", "BA", "wwwoOoo", "ba","abcde","wwwoooo", "a","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe","wwwoooo", "BA", "wwwoOoo", "ba", "BA", "wwwoOoo", "ba","AbcDe","AbcDe","AbcDe"), asList("BA", "ABCDE", "WWWOOOO")});
        /*
        data.add(new Object[]{3, asList("wwwoooo", "a", "BA", "wwwoOoo", "ba"), asList("BA", "WWWOOOO")});
        data.add(new Object[]{3, asList("all", "arguments", "a", "map", "wwwwwww", "map", "arguments", "all"), asList("ALL", "MAP", "ARGUMENTS")});
        data.add(new Object[]{2, asList("dog", "word", "intellij", "a", "b", "dog", "word", "intellij"), asList("DOG", "WORD")});
        data.add(new Object[]{3, Collections.emptyList(), Collections.emptyList()});
        data.add(new Object[]{3, asList("www"), Collections.emptyList()});
         */
        return data;
    }

    @Test
    public void test() {
        /*
        List<String> actual = aggregator.getDuplicates(words, limit);
        assertEquals(expected, actual);
         */
        PerformanceTest ptest = new PerformanceTest(aggregator.getClass().getCanonicalName()+" - getDuplicates");
        for(int contTest = 0; contTest<30; contTest++) {
            ptest.beginTest();
            List<String> actual = aggregator.getDuplicates(words, limit);
            ptest.finishTest();
            assertEquals(expected, actual);
        }
        ptest.getAvg(30);
    }
}