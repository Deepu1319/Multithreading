package com.company;

class PrintNumber {
    public synchronized void printPrime() throws InterruptedException {
        for (int i = 3; i <= 20; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {

                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println("Prime " + i);
                Thread.sleep(1000);
            }
            notify();
            wait();
        }
    }


    public synchronized void printNonPrime() throws InterruptedException {
        for (int i = 3; i <= 20; i++) {
            boolean isNonPrime = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isNonPrime = true;
                }
            }
            if (isNonPrime) {
                System.out.println("Non Prime " + i);
                Thread.sleep(1000);
            }
            notify();
            wait();
        }
    }

}

class PrimeNonPrime implements Runnable {
    private PrintNumber printNumber;
    private boolean flag;

    public PrimeNonPrime(PrintNumber printNumber, boolean flag) {
        this.printNumber = printNumber;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag)
                printNumber.printPrime();
            else
                printNumber.printNonPrime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

public class PrimeAndNonPrime {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        PrimeNonPrime primeNonPrime = new PrimeNonPrime(printNumber, true);
        PrimeNonPrime primeNonPrime1 = new PrimeNonPrime(printNumber, false);
        new Thread(primeNonPrime).start();
        new Thread(primeNonPrime1).start();
    }
}
