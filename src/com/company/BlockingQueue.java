package com.company;

import java.util.LinkedList;
public class BlockingQueue {
    public static void main(String[] args) throws InterruptedException
    {
        final PubSubUsingBlockingQueue pc = new PubSubUsingBlockingQueue();
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pc.publisher();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pc.subscriber();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    public static class PubSubUsingBlockingQueue
    {
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 10;
        public void publisher() throws InterruptedException
        {
            int value = 0;
            while (true)
            {
                synchronized (this)
                {
                    if (list.size() == capacity){
                        wait();
                    }
                    System.out.println("Publisher publish-" + value);
                    list.add(value++);
                    notifyAll();
                    Thread.sleep(1000);
                }
            }
        }
        public void subscriber() throws InterruptedException
        {
            while (true)
            {
                synchronized (this)
                {
                    if (list.size()==0){
                        wait();
                    }
                    int val = list.removeFirst();
                    System.out.println("Consumer consumed-" + val);
                    notifyAll();
                    Thread.sleep(1000);
                }
            }
        }
    }
}
