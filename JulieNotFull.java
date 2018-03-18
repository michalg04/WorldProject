import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class JulieNotFull extends MoveAbstract {

    private final int resourceLimit;
    private int resourceCount;
    private ImageStore imageStore;

    public JulieNotFull(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount,
                        int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;

    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> notFullTarget = world.findNearest(getPosition(), Ore.class);

        if (!notFullTarget.isPresent() ||
                !moveTo(world, notFullTarget.get(), scheduler) ||
                !transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this,
                    createActivity(world, imageStore),
                    getActionPeriod());
        }
    }

    private boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (resourceCount >= resourceLimit) {
            JulieFull julie = Factory.createJulieFull(getId(), resourceLimit,
                    getPosition(), getActionPeriod(), getAnimationPeriod(),
                    getImages());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(julie);
            julie.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (super.moveTo(world, target, scheduler)) {
            resourceCount += 1;
            ((Ore)target).transformToCookie(world,scheduler,imageStore);
            return true;
        } else {
            return false;
        }
    }

}


