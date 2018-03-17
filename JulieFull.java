import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class JulieFull extends MoveAbstract {

    private final int resourceLimit;

    public JulieFull(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount,
                     int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;

    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(getPosition(),
                Restaurant.class);

        if (fullTarget.isPresent() && moveTo(world, fullTarget.get(), scheduler)) {
            transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this,
                    createActivity(world, imageStore),
                    getActionPeriod());
        }
    }

    private void transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        JulieNotFull julieNotFull = Factory.createJulieNotFull(getId(), resourceLimit,
                getPosition(), getActionPeriod(), getAnimationPeriod(),
                getImages());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(julieNotFull);
        julieNotFull.scheduleActions(scheduler, world, imageStore);
    }
}


