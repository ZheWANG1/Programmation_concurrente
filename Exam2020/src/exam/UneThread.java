package exam;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UneThread implements Runnable {
    private int monId;
    private Object monL;
    private Random monG = new Random();

    UneThread(int i, Object l) {
        monId = i;
        monL = l;
    }

    public void run() {
        try {
            synchronized (monL) {
                monL.wait();
            }
            System.out.println(monId + ".A");
            Thread.sleep(monG.nextInt(1000));
            System.out.println(monId + ".B");

        } catch (InterruptedException i) {
            System.out.println(monId + ".AAAAARG!");
        }
    }
}

class Execution2 {
    public static void main(String[] args) {
        Object sharedL = new Object();
        int i;
        System.out.println("main se lance");
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (i = 1; i <= 4; i++) {
            exec.execute(new UneThread(i, sharedL));
        }
        synchronized (sharedL) {
            sharedL.notifyAll();
        }
        System.out.println("main termine");
    }
}
