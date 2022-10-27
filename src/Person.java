import processing.core.PImage;

import java.util.List;
import java.util.function.Predicate;

public abstract class Person extends Moves{

    protected int resourceLimit;

    public Person(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int resourceLimit) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }

    public boolean _nextPosition(WorldModel world, Point newPos){
        return !(world.getOccupancyCell(newPos) instanceof Stump);
    }


}

