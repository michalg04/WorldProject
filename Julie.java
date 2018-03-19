import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Julie extends MoveAbstract {

    private final int resourceLimit;
    private int resourceCount;

    public Julie(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount,
                 int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;

    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> julieTarget = world.findNearest(getPosition(), Ore.class);
        long nextPeriod = getActionPeriod();

        if (julieTarget.isPresent()) {
            Point tgtPos = julieTarget.get().getPosition();

            if (this.moveTo(world, julieTarget.get(), scheduler)) {
                Cookie cookie = Factory.createCookie("cookie_" +
                        tgtPos.getX() + "_" + tgtPos.y, tgtPos, imageStore.getImageList("cookie"));

                world.addEntity(cookie);
            }
        }

        scheduler.scheduleEvent(this,
                createActivity(world, imageStore),
                nextPeriod);
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


