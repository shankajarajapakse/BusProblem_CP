import java.util.concurrent.Semaphore;

/**
 * Created by Sankaja on 2/7/2018.
 */
public class SemaphoreHolder
{
    private static SemaphoreHolder instance = null;
    //Semaphores
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore multiplex = new Semaphore(50);
    private static Semaphore bus = new Semaphore(0);
    private static Semaphore allAboard = new Semaphore(0);
    private static Semaphore haltForBus = new Semaphore(1);

    private static int riders = 0;

    private SemaphoreHolder()
    {

    }

    public static synchronized SemaphoreHolder getInstance()
    {
        if (instance == null)
            instance = new SemaphoreHolder();
        return instance;
    }

    public static Semaphore getHaltForBus() {
        return haltForBus;
    }


    public Semaphore getMutex() {
        return mutex;
    }

    public Semaphore getMultiplex() {
        return multiplex;
    }

    public Semaphore getBus() {
        return bus;
    }

    public Semaphore getAllAboard() {
        return allAboard;
    }

    public static void incrementRiders()          // incrementing rider count by 1
    {
        riders++;
    }

    public static void decrementRiders()          // decrement riders count by 1
    {
        riders--;
    }

    public static int getRiderCount()          // ridercount
    {
        return riders;
    }
}
