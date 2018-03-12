import processing.core.PImage;

import java.util.List;
import java.util.Random;

public abstract class ActiveAbstract extends EntityAbstract implements ActiveEntity{
    private final int actionPeriod;
    protected static final Random rand = new Random();


    public ActiveAbstract(String id, Point position, List<PImage> images, int actionPeriod)
    {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }

    protected int getActionPeriod() {return actionPeriod;}

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                createActivity(world, imageStore), getActionPeriod());
    }

    public Action createActivity(WorldModel world, ImageStore imageStore)
    {
        return new Activity(this, world, imageStore);
    }
}
