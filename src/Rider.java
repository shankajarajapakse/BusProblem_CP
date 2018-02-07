/**
 * Created by Sankaja on 2/7/2018.
 */
public class Rider extends Thread {

    public Rider()
    {
        System.out.println("Rider "+ this.getId()+ " created");
    }

    public void boardBus()
    {
        System.out.println("rider " + this.getId() +" boarded.");
    }

    @Override
    public void run()
    {
        try
        {
            SemaphoreHolder.getInstance().getMultiplex().acquire(1);
            SemaphoreHolder.getInstance().getMutex().acquire(1);
            SemaphoreHolder.getInstance().incrementRiders();
            SemaphoreHolder.getInstance().getMutex().release(1);
            SemaphoreHolder.getInstance().getBus().acquire(1);
            SemaphoreHolder.getInstance().getMultiplex().release(1);
            this.boardBus();
            SemaphoreHolder.getInstance().decrementRiders();
            if (SemaphoreHolder.getInstance().getRiderCount() == 0) {
                SemaphoreHolder.getInstance().getAllAboard().release(1);

            } else {
                SemaphoreHolder.getInstance().getBus().release(1);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e.toString());
        }

    }

}
