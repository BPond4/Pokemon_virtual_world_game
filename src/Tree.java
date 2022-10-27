import processing.core.PImage;
import java.util.*;

public class Tree extends HasHealth{

    public Tree(
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

    public boolean transformTree(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.health <= 0) {
            Stump stump = Factory.createStump(this.id,
                    this.position,
                    imageStore.getImageList(Parse.STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {

        if (!this.transformTree(world, scheduler, imageStore)) {

            super.executeActivity(world, imageStore, scheduler);
        }
    }
}
