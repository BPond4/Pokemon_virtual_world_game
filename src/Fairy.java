import processing.core.PImage;

import java.util.*;
import java.util.function.Predicate;

public class Fairy extends Moves {

    public Fairy(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> fairyTarget =
                world.findNearest(this.position, new ArrayList<Class>(Arrays.asList(Stump.class)));
        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                Acts sapling = Factory.createSapling("sapling_" + this.id, tgtPos,
                        imageStore.getImageList(Parse.SAPLING_KEY));

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }

    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
    }

    //this is almost empty because fairy doesn't need any extra checks to figure out where its supposed to move
    public boolean _nextPosition(
            WorldModel world, Point newPos)
    {
        return true;
    }
}
