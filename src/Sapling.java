import processing.core.PImage;

import java.util.*;

public class Sapling extends HasHealth{

    public Sapling(
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

    public boolean transformSapling(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.health <= 0) {
            Entity stump = Factory.createStump(this.id,
                    this.position,
                    imageStore.getImageList(Parse.STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);

            return true;
        }
        else if (this.health >= this.healthLimit)
        {
            Tree tree = Factory.createTree("tree_" + this.id,
                    this.position,
                    Extra.getNumFromRange(Parse.TREE_ACTION_MAX, Parse.TREE_ACTION_MIN),
                    Extra.getNumFromRange(Parse.TREE_ANIMATION_MAX, Parse.TREE_ANIMATION_MIN),
                    Extra.getNumFromRange(Parse.TREE_HEALTH_MAX, Parse.TREE_HEALTH_MIN),
                    imageStore.getImageList(Parse.TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

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
        if (!this.transformSapling(world, scheduler, imageStore))
        {
            super.executeActivity(world, imageStore, scheduler);
        }
    }
}
