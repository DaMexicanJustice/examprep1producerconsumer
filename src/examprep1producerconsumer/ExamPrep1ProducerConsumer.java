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
public class ExamPrep1ProducerConsumer {
    
    private static Long sum;
    private static boolean flag;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        
            This code is without task 5 implemented from exam set.
        
        */
        sum = 0l;
        flag = false;
        
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

        Thread p1 = new Thread(r);
        Thread p2 = new Thread(r);
        Thread p3 = new Thread(r);
        Thread p4 = new Thread(r);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        
        Runnable r2 = () -> {
            while (!flag) {
                try {
                    Long fibValue = (Long) s2.take();
                    System.out.println("Fibonacci number: " + fibValue);
                    sum += fibValue;
                } catch (InterruptedException ex) {
                    System.out.println("Consumer error: " + ex.getMessage());
                }
            }
        };
        
        Thread c1 = new Thread(r2);
        c1.start();
        
        Thread[] threads = {p1, p2, p3, p4};
        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException ex) {
                System.out.println("Thread array error: " + ex.getMessage());
            }
        }
        flag = true;
    }

}
