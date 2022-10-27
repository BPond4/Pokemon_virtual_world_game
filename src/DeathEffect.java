import processing.core.PImage;

import java.util.List;

public abstract class DeathEffect extends HasHealth{
    public DeathEffect(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit)
    {
        super(id, position, images, animationPeriod, actionPeriod, health, healthLimit);
    }

    public boolean transformEffect(
            WorldModel world,
            EventScheduler scheduler)
    {
        if (this.health >= this.healthLimit)
        {
            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);


            return true;
        }

        return false;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        this.health++;
        if (!this.transformEffect(world, scheduler))
        {
            super.executeActivity(world, imageStore, scheduler);
        }
    }
}
