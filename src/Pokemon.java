import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Pokemon extends Moves{
    public Pokemon(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    abstract void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler, ImageStore imageStore);
    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler){

    }
    public boolean _nextPosition(
            WorldModel world, Point newPos)
    {
        return true;
    }

    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler, ImageStore imageStore) {
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            moveToHelper(world, target, scheduler, imageStore);
            return true;
        }
        else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


}
