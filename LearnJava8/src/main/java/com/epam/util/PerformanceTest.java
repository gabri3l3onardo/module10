package com.epam.util;

public class PerformanceTest {

    private String methodTestedName;

    private Long startTime;
    private Long endTime;
    private Long avg;

    public PerformanceTest(String methodTestedName){
        this.methodTestedName = methodTestedName;
        this.avg = 0L;
    }

    public void beginTest() {
        startTime = System.nanoTime();
    }

    public Long finishTest() {
        endTime = System.nanoTime() - startTime;
        //System.out.println(this.methodTestedName+"; Time in nano: "+endTime);
        avg += endTime;
        return endTime;
    }

    public void getAvg(int numTests) {
        System.out.println(
                String.format("%s; For %d tests, Average time in nano is: %d",this.methodTestedName,numTests,(avg/numTests)));
    }

}
