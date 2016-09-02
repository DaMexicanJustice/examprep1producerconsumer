/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprep1producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author xboxm
 */
public class ExamPrep1ProducerConsumer2 {

    private static long sum;
    private static boolean flag;

    public static void main(String[] args) {
        long runTime = 0;
        try {
            /*
            
            This code is with task 5 from exam set.
            
             */

            runTime = alternative(1);

            while (runTime == 0) {
                // Busy Waiting
            }
            
            System.out.println("Run time: " + runTime + " ms");

            
        } catch (InterruptedException ex) {
            System.out.println("Method call error: " + ex.getMessage());
        }

    }

    public static long alternative(int toUse) throws InterruptedException {
        long timestamp = System.currentTimeMillis();

        int size = 11;

        ArrayBlockingQueue s1 = new ArrayBlockingQueue(size);
        ArrayBlockingQueue s2 = new ArrayBlockingQueue(size);

        s1.add(4);
        s1.add(5);
        s1.add(8);
        s1.add(12);
        s1.add(21);
        s1.add(22);
        s1.add(34);
        s1.add(35);
        s1.add(36);
        s1.add(37);
        s1.add(42);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    while (!s1.isEmpty()) {
                        Long input = Long.parseLong(String.valueOf(s1.take()));
                        Long result = fib(input);
                        s2.put(result);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Producer error: " + ex.getMessage());
                }
            }

            private long fib(long n) {
                if ((n == 0) || (n == 1)) {
                    return n;
                } else {
                    return fib(n - 1) + fib(n - 2);
                }
            }
        };
        Thread[] thrs = new Thread[toUse];
        for (int i = 0; i < toUse; i++) {
            thrs[i] = new Thread(r);
            thrs[i].start();
        }

        Runnable r2 = () -> {
            sum = 0l;
            while (!flag) {
                try {
                    Long fibValue = (Long) s2.take();
                    System.out.println(fibValue);
                    sum += fibValue;
                } catch (InterruptedException ex) {
                    System.out.println("Consumer error: " + ex.getMessage());
                }
            }
        };

        Thread c1 = new Thread(r2);
        c1.start();

        for (Thread thr : thrs) {
            try {
                thr.join();
            } catch (InterruptedException ex) {
                System.out.println("Thread array error: " + ex.getMessage());
            }
        }
        flag = true;
        return System.currentTimeMillis() - timestamp;
    }

}
