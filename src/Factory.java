import processing.core.PImage;
import java.util.List;


public final class Factory {

    public static House createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Spawner createSpawner(String id, Point position, List<PImage> images){
        return new Spawner(id, position, images);
    }

    public static Obstacle createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static Tree createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health, 0);
    }

    public static Stump createStump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Sapling createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images,
                Parse.SAPLING_ACTION_ANIMATION_PERIOD, Parse.SAPLING_ACTION_ANIMATION_PERIOD, 0, Parse.SAPLING_HEALTH_LIMIT);
    }

    public static CharmanderDeath createCharmanderDeath(
            String id,
            Point position,
            List<PImage> images)
    {
        return new CharmanderDeath(id, position, images,
                Parse.DEATHEFFECT_ACTION_PERIOD, Parse.DEATHEFFECT_ANIMATION_PERIOD, 0, Parse.DEATHEFFECT_HEALTH_LIMIT);
    }

    public static SquirtleDeath createSquirtleDeath(
            String id,
            Point position,
            List<PImage> images)
    {
        return new SquirtleDeath(id, position, images,
                Parse.DEATHEFFECT_ACTION_PERIOD, Parse.DEATHEFFECT_ANIMATION_PERIOD, 0, Parse.DEATHEFFECT_HEALTH_LIMIT);
    }

    public static BulbasaurDeath createBulbasaurDeath(
            String id,
            Point position,
            List<PImage> images)
    {
        return new BulbasaurDeath(id, position, images,
                Parse.DEATHEFFECT_ACTION_PERIOD, Parse.DEATHEFFECT_ANIMATION_PERIOD, 0, Parse.DEATHEFFECT_HEALTH_LIMIT);
    }

    public static GrowingSpawner createGrowingSpawner(
            String id,
            Point position,
            List<PImage> images)
    {
        return new GrowingSpawner(id, position, images,
                Parse.GROWINGSPAWNER_ACTION_PERIOD, Parse.GROWINGSPAWNER_ANIMATION_PERIOD, 0, Parse.GROWINGSPAWNER_HEALTH_LIMIT);
    }

    public static Fairy createFairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Fairy(id, position, images, actionPeriod, animationPeriod);
    }

    public static Clefairy createClefairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Clefairy(id, position, images, actionPeriod, animationPeriod);
    }

    public static Bulbasaur createBulbasaur(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images){
        return new Bulbasaur(id, position, images, actionPeriod, animationPeriod);
    }

    public static Squirtle createSquirtle(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images){
        return new Squirtle(id, position, images, actionPeriod, animationPeriod);
    }

    public static Charmander createCharmander(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images){
        return new Charmander(id, position, images, actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static DudeNotFull createDudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new DudeNotFull(id, position, images, resourceLimit, 0,
                actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static DudeFull createDudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new DudeFull(id, position, images, resourceLimit,
                actionPeriod, animationPeriod);
    }

    public static Action createAnimationAction(Animates entity, int repeatCount) {
        return new AnimationAction(entity,  repeatCount);
    }

    public static Action createActivityAction(
            Acts entity, WorldModel world, ImageStore imageStore)
    {
        return new ActivityAction(entity, world, imageStore);
    }
}
