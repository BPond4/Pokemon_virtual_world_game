import processing.core.PImage;

import java.util.List;

public abstract class Acts extends Animates{

    protected int actionPeriod;

    public Acts(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    @Override
    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        super.scheduleActions(scheduler, world, imageStore);
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }

    abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
