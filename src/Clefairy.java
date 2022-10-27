import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Clefairy extends Moves{
    public Clefairy(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
        Optional<Entity> clefairyTarget =
                world.findNearest(this.position, new ArrayList<Class>(Arrays.asList(Spawner.class)));
        boolean spawn = true;
        if (clefairyTarget.isPresent()) {
            Point tgtPos = clefairyTarget.get().getPosition();

            if (this.moveTo(world, clefairyTarget.get(), scheduler)) {
                Acts growingSpawner = Factory.createGrowingSpawner("growingspawner_" + this.id, tgtPos,
                        imageStore.getImageList(Parse.GROWINGSPAWNER_KEY));
                int randInt = Extra.getNumFromRange(3, 0);
                Point pokePosition = new Point(tgtPos.x, tgtPos.y+1);
                if(world.isOccupied(pokePosition) || !world.withinBounds(pokePosition)){
                    pokePosition = new Point(tgtPos.x, tgtPos.y -1);
                    if(world.isOccupied(pokePosition) || !world.withinBounds(pokePosition)){
                        pokePosition = new Point(tgtPos.x+1, tgtPos.y);
                        if(world.isOccupied(pokePosition) || !world.withinBounds(pokePosition)){
                            pokePosition = new Point(tgtPos.x-1, tgtPos.y);
                            if(world.isOccupied(pokePosition) || !world.withinBounds(pokePosition)){
                                spawn = false;
                            }
                        }
                    }
                }
                if(spawn){
                    Moves pokemon = Factory.createBulbasaur("bulbasaur_" + this.id, pokePosition, 51,300, imageStore.getImageList(Parse.BULBASAUR_KEY));

                    if(randInt == 1){
                        pokemon = Factory.createCharmander("charmander_"+this.id, pokePosition, 51, 300, imageStore.getImageList(Parse.CHARMANDER_KEY));
                    }
                    else if(randInt == 2){
                        pokemon = Factory.createSquirtle("squirtle_"+this.id, pokePosition, 51, 300, imageStore.getImageList(Parse.SQUIRTLE_KEY));
                    }
                    world.addEntity(pokemon);
                    pokemon.scheduleActions(scheduler, world, imageStore);
                }

                world.addEntity(growingSpawner);
                growingSpawner.scheduleActions(scheduler, world, imageStore);

            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }

    public void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
    }

    //this is almost empty because fairy doesn't need any extra checks to figure out where its supposed to move
    public boolean _nextPosition(WorldModel world, Point newPos){
        return true;
    }
}

