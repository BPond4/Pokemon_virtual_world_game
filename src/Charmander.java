import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Charmander extends Pokemon{
    public Charmander(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler, ImageStore imageStore) {
        if(target instanceof Bulbasaur){
            BulbasaurDeath bulbdeath = Factory.createBulbasaurDeath("bulbasaurdeath_"+this.id, target.getPosition(), imageStore.getImageList(Parse.BULBASAURDEATH_KEY));
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            world.addEntity(bulbdeath);
            bulbdeath.scheduleActions(scheduler, world, imageStore);
        }
    }
    public boolean _nextPosition(
            WorldModel world, Point newPos)
    {
        return true;
    }
    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> charmanderTarget =
                world.findNearest(this.position, new ArrayList<Class>(Arrays.asList(Bulbasaur.class, Squirtle.class)));
        if (charmanderTarget.isPresent()) {
            this.moveTo(world, charmanderTarget.get(), scheduler, imageStore);
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }
}
