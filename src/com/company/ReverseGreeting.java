package com.company;

public class ReverseGreeting extends Thread
{
    private int counter;
    public ReverseGreeting(int counter)
    {
        super("Thread" + counter);
        this.counter = counter;
    }

    public void run()
    {
        counter++ ;
        if(counter <= 11)
        {
            ReverseGreeting reverseGreeting = new ReverseGreeting(counter);
            reverseGreeting.start();
            try {
                reverseGreeting.join();
                System.out.println("Hello from "+ getName());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main (String args[])
    {
        ReverseGreeting thread1 = new ReverseGreeting(1);
        thread1.start();
    }

}
