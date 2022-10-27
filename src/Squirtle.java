import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Squirtle extends Pokemon{
    public Squirtle(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler, ImageStore imageStore) {
        if(target instanceof Charmander){
            CharmanderDeath chardeath = Factory.createCharmanderDeath("charmanderdeath_"+this.id, target.getPosition(), imageStore.getImageList(Parse.CHARMANDERDEATH_KEY));
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            world.addEntity(chardeath);
            chardeath.scheduleActions(scheduler, world, imageStore);
        }
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> squirtleTarget =
                world.findNearest(this.position, new ArrayList<Class>(Arrays.asList(Bulbasaur.class, Charmander.class)));
        if (squirtleTarget.isPresent()) {
            this.moveTo(world, squirtleTarget.get(), scheduler, imageStore);
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }
}
