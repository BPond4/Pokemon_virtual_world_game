import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Stream;

public abstract class Moves extends Acts {
    public Moves(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    abstract void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler);

    boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            moveToHelper(world, target, scheduler);
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

    protected abstract boolean _nextPosition(WorldModel world, Point newPos);

    public Point nextPosition(
            WorldModel world, Point destPos)
    {
        AStarPathingStrategy pathingStrategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = s -> (!world.isOccupied(s) || !_nextPosition(world, s)) && (world.withinBounds(s));
        BiPredicate<Point,Point> withinReach = (p1, p2) -> ((Math.abs(p1.x -p2.x) ==1)&&(Math.abs(p1.y - p2.y) == 0)) || ((Math.abs(p1.y - p2.y) ==1)&&(Math.abs(p1.x - p2.x) == 0));
        Function<Point, Stream<Point>> potentialNeighbors = (p1) -> Stream.of(new Point(p1.x+1, p1.y), new Point(p1.x-1, p1.y), new Point(p1.x,p1.y+1), new Point(p1.x, p1.y-1));
        List<Point> singlePoint = pathingStrategy.computePath(this.getPosition(), destPos, canPassThrough, withinReach, potentialNeighbors);
        if(singlePoint.size() == 0){
            return this.getPosition();
        }
        return singlePoint.get(0);
    }
}
