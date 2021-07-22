package algo;

import algo.utils.tree.definition.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

    @Test
    public void test() {
        ExecutorService es = Executors.newCachedThreadPool();
        Sample s = new Sample();
        es.execute(s::t2);
        es.execute(s::t1);
        es.execute(s::wake);
    }

}

class Sample {
    private Lock lock = new ReentrantLock();
    private Condition con1 = lock.newCondition();
    private Condition con2 = lock.newCondition();

    public void wake() {
        lock.lock();
        try {
            System.out.println("Now wake t1");
            con1.signal();
            System.out.println("Now wake t2");
            con2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void t1() {
        lock.lock();
        try {
            System.out.println("t1 await");
            con1.await();
            System.out.println("t1 woken");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void t2() {
        lock.lock();
        try {
            System.out.println("t2 await");
            con2.await();
            System.out.println("t2 woken");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}