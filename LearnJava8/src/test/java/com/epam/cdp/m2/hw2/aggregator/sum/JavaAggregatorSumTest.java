package com.epam.cdp.m2.hw2.aggregator.sum;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.epam.util.PerformanceTest;
import org.junit.Test;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;

public abstract class JavaAggregatorSumTest {

    @Parameterized.Parameter(0)
    public List<Integer> numbers;

    @Parameterized.Parameter(1)
    public int expected;

    private Aggregator aggregator;

    public JavaAggregatorSumTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 5, 6, 7, 8, 4, 5, 6, 7, 8), 180});
   /*   data.add(new Object[]{asList(1, 2, 3, 4, 5, 6, 7, 8), 36});
        data.add(new Object[]{asList(10, -10, 3), 3});
        data.add(new Object[]{emptyList(), 0});  */
        return data;
    }

    @Test
    public void test() {
        /*
        int actual = aggregator.sum(numbers);
        assertEquals(expected, actual);
         */
        PerformanceTest ptest = new PerformanceTest(aggregator.getClass().getCanonicalName()+" - sum");
        for(int contTest = 0; contTest<30; contTest++) {
            ptest.beginTest();
            int actual = aggregator.sum(numbers);
            ptest.finishTest();
            assertEquals(expected, actual);
        }
        ptest.getAvg(30);
    }
}