import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Rat extends MoveAbstract{
    private static final String QUAKE_KEY = "quake";

    public Rat(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> cookieTarget = world.findNearest(getPosition(), Cookie.class);
        long nextPeriod = getActionPeriod();

        if (cookieTarget.isPresent())
        {

            moveTo(world, cookieTarget.get(), scheduler);

        }

        scheduler.scheduleEvent(this,
                createActivity(world, imageStore),
                nextPeriod);
    }

    public Point nextPosition(WorldModel world, Point destPos)
    { 
        int horiz = Integer.signum(destPos.x - getPosition().x);
        Point newPos = new Point(getPosition().x + horiz, getPosition().y);

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get() instanceof Cookie)))
        {
            int vert = Integer.signum(destPos.y - getPosition().y);
            newPos = new Point(getPosition().x, getPosition().y + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get() instanceof Cookie)))
            {
                newPos = getPosition();
            }
        }
        return newPos;
  
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (super.moveTo(world, target, scheduler)) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        } else {
            return false;
        }
    }

}
