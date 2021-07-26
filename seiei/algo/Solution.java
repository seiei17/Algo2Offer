package algo;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Solution {
    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SynTest(countDownLatch), "Thread + " + i);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("Count down");

        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.deleteCharAt()
    }
}

class SynTest implements Runnable {

    private CountDownLatch countDownLatch;

    SynTest(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + ": Count Down.");
        countDownLatch.countDown();
        System.out.println(Thread.currentThread() + ": Count Over");
    }
}