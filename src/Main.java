import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sankaja on 2/7/2018.
 */

public class Main
{
    public static void main(String[] args)
    {
        SemaphoreHolder.getInstance();                  // make sure semaphores are created
        Scanner sc=new Scanner(System.in);
        System.out.println("Input rider count");
        int riders = sc.nextInt();
        System.out.println("Input Bus count");
        int buses = sc.nextInt();

        ExecutorService riderService =  Executors.newFixedThreadPool(riders);
        for (int i = 0; i < riders; i++) {
            riderService.execute(new Rider());
        }

        riderService.shutdown();

        ExecutorService busService =  Executors.newFixedThreadPool(buses);
        for (int i = 0; i < buses; i++) {
            busService.execute(new Bus());
        }

        busService.shutdown();

    }



}
