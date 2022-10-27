import processing.core.PImage;

import java.util.List;

public abstract class HasHealth extends Acts{

    protected int health;
    protected final int healthLimit;

    public HasHealth(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
        this.healthLimit = healthLimit;
    }

    public int getHealth() { return health; }
    public void setHealth(int h) {this.health = h;}

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
    }
}
