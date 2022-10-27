import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Bulbasaur extends Pokemon{
    public Bulbasaur(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler, ImageStore imageStore) {
        if(target instanceof Squirtle){
            SquirtleDeath squirtdeath = Factory.createSquirtleDeath("squirtledeath_"+this.id, target.getPosition(), imageStore.getImageList(Parse.SQUIRTLEDEATH_KEY));
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            world.addEntity(squirtdeath);
            squirtdeath.scheduleActions(scheduler, world, imageStore);
        }
    }
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> bulbasaurTarget =
                world.findNearest(this.position, new ArrayList<Class>(Arrays.asList(Charmander.class, Squirtle.class)));
        if (bulbasaurTarget.isPresent()) {
            this.moveTo(world, bulbasaurTarget.get(), scheduler, imageStore);
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }
}
