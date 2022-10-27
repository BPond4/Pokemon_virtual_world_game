import processing.core.PImage;

import java.util.*;

public class GrowingSpawner extends HasHealth{

    public GrowingSpawner(
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

    public boolean transformGrower(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.health >= this.healthLimit)
        {
            Spawner spawner = Factory.createSpawner("spawner_" + this.id,
                    this.position,
                    imageStore.getImageList(Parse.SPAWNER_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(spawner);


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
        if (!this.transformGrower(world, scheduler, imageStore))
        {
            super.executeActivity(world, imageStore, scheduler);
        }
    }
}
