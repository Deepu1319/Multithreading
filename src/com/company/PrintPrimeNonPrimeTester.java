package com.company;

public class PrintPrimeNonPrimeTester {
    public static void main(String[] args) {
        Printer printer = new Printer();
        OddEven oddEven1 = new OddEven(printer,true);
        OddEven oddEven2 = new OddEven(printer,false);
        new Thread(oddEven1).start();
        new Thread(oddEven2).start();

    }
}
class OddEven implements Runnable{
    private Printer printer;
    private boolean flag;
    public OddEven(Printer printer, boolean flag){
        this.printer = printer;
        this.flag = flag;
    }
    @Override
    public void run() {
        try{
            if(flag){
                printer.printOdd();
            }else{
                printer.printEven();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class Printer{
    int i = 1;
    public synchronized void printOdd() throws InterruptedException{
        while(i<=19){
            if(i%2==0){
                wait();
            }
            System.out.println("Odd ="+i++);
            notify();
        }
    }

    public synchronized void printEven() throws InterruptedException{
        while(i<=20){
            if(i%2!=0){
                wait();
            }
            System.out.println("Even ="+i++);
            notify();
        }
    }
}

