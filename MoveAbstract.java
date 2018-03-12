import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class MoveAbstract extends AnimatedAbstract {

    public MoveAbstract(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                createActivity(world, imageStore), getActionPeriod());
        scheduler.scheduleEvent(this, createAnimation(0), getAnimationPeriod());
    }

    public boolean adjacent(Point p1, Point p2)
    {
        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) ||
                (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1);
    }

    public Point nextPosition(WorldModel world, Point destPos) {
        /*
        int horiz = Integer.signum(destPos.x - getPosition().x);
        Point newPos = new Point(getPosition().x + horiz,
                getPosition().y);

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.y - getPosition().y);
            newPos = new Point(getPosition().x,
                    getPosition().y + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = getPosition();
            }
        }

        return newPos;
    }*/

        //PathingStrategy strategy = new SingleStepPathingStrategy();
        PathingStrategy strategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = p -> !world.isOccupied(p) && world.withinBounds(p);
        BiPredicate<Point, Point> withinReach = (Point p1, Point p2) -> adjacent(p1, p2);
        List<Point> path = strategy.computePath(getPosition(),
                destPos, canPassThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path == null)
            return getPosition();
        if (path.size() == 0)
            return getPosition();
        return path.get(0);

    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (adjacent(getPosition(), target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!getPosition().equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

}
