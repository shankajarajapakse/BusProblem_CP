
/**
 * Created by Sankaja on 2/7/2018.
 */
public class Bus extends Thread
{
    public void arrive()
    {
        System.out.println("Bus arrived");
    }

    public void depart()
    {
        System.out.println("Bus departed");
        System.out.println("Remaining riders count :" + SemaphoreHolder.getInstance().getRiderCount()+" .");
        System.out.println("PS. This count is current remaining riders count. More riders may spawn later");
    }

    @Override
    public void run()
    {
        try {
            SemaphoreHolder.getInstance().getHaltForBus().acquire();
            this.arrive();
            SemaphoreHolder.getInstance().getMutex().acquire(1);
            if (SemaphoreHolder.getInstance().getRiderCount() > 0) {
                SemaphoreHolder.getInstance().getBus().release(1);
                SemaphoreHolder.getInstance().getAllAboard().acquire(1);
            }
            SemaphoreHolder.getInstance().getMutex().release(1);
            this.depart();
            SemaphoreHolder.getInstance().getHaltForBus().release();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.toString());
        }

    }
}
