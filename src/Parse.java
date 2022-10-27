import java.util.*;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class contains many functions written in a procedural style.
 * You will reduce the size of this class over the next several weeks
 * by refactoring this codebase to follow an OOP style.
 */
public final class Parse
{
    public static final int PROPERTY_KEY = 0;

    private static final List<String> PATH_KEYS = new ArrayList<>(Arrays.asList("bridge", "dirt", "dirt_horiz", "dirt_vert_left", "dirt_vert_right",
            "dirt_bot_left_corner", "dirt_bot_right_up", "dirt_vert_left_bot"));

    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time
    private static final int SAPLING_NUM_PROPERTIES = 4;
    private static final int SAPLING_ID = 1;
    private static final int SAPLING_COL = 2;
    private static final int SAPLING_ROW = 3;
    private static final int SAPLING_HEALTH = 4;

    public static final String BULBASAURDEATH_KEY = "bulbasaurdeath";
    public static final String SQUIRTLEDEATH_KEY = "squirtledeath";
    public static final String CHARMANDERDEATH_KEY = "charmanderdeath";
    public static final int DEATHEFFECT_HEALTH_LIMIT = 5;
    public static final int DEATHEFFECT_ACTION_PERIOD = 1000;
    public static final int DEATHEFFECT_ANIMATION_PERIOD = 750;
    private static final int DEATHEFFECT_NUM_PROPERTIES = 4;
    private static final int DEATHEFFECT_ID = 1;
    private static final int DEATHEFFECT_COL = 2;
    private static final int DEATHEFFECT_ROW = 3;
    private static final int DEATHEFFECT_HEALTH = 4;

    public static final String GROWINGSPAWNER_KEY = "growingspawner";
    public static final int GROWINGSPAWNER_HEALTH_LIMIT = 5;
    public static final int GROWINGSPAWNER_ANIMATION_PERIOD = 540;
    public static final int GROWINGSPAWNER_ACTION_PERIOD = 1000;
    private static final int GROWINGSPAWNER_NUM_PROPERTIES = 4;
    private static final int GROWINGSPAWNER_ID = 1;
    private static final int GROWINGSPAWNER_COL = 2;
    private static final int GROWINGSPAWNER_ROW = 3;
    private static final int GROWINGSPAWNER_HEALTH = 4;



    public static final String BGND_KEY = "background";
    private static final int BGND_NUM_PROPERTIES = 4;
    private static final int BGND_ID = 1;
    private static final int BGND_COL = 2;
    private static final int BGND_ROW = 3;

    public static final String OBSTACLE_KEY = "obstacle";
    private static final int OBSTACLE_NUM_PROPERTIES = 5;
    private static final int OBSTACLE_ID = 1;
    private static final int OBSTACLE_COL = 2;
    private static final int OBSTACLE_ROW = 3;
    private static final int OBSTACLE_ANIMATION_PERIOD = 4;

    public static final String DUDE_KEY = "dude";
    private static final int DUDE_NUM_PROPERTIES = 7;
    private static final int DUDE_ID = 1;
    private static final int DUDE_COL = 2;
    private static final int DUDE_ROW = 3;
    private static final int DUDE_LIMIT = 4;
    private static final int DUDE_ACTION_PERIOD = 5;
    private static final int DUDE_ANIMATION_PERIOD = 6;

    public static final String HOUSE_KEY = "house";
    private static final int HOUSE_NUM_PROPERTIES = 4;
    private static final int HOUSE_ID = 1;
    private static final int HOUSE_COL = 2;
    private static final int HOUSE_ROW = 3;

    public static final String SPAWNER_KEY = "spawner";
    private static final int SPAWNER_NUM_PROPERTIES = 4;
    private static final int SPAWNER_ID = 1;
    private static final int SPAWNER_COL = 2;
    private static final int SPAWNER_ROW = 3;

    public static final String FAIRY_KEY = "fairy";
    private static final int FAIRY_NUM_PROPERTIES = 6;
    private static final int FAIRY_ID = 1;
    private static final int FAIRY_COL = 2;
    private static final int FAIRY_ROW = 3;
    private static final int FAIRY_ANIMATION_PERIOD = 4;
    private static final int FAIRY_ACTION_PERIOD = 5;

    public static final String CLEFAIRY_KEY = "clefairy";
    private static final int CLEFAIRY_NUM_PROPERTIES = 6;
    private static final int CLEFAIRY_ID = 1;
    private static final int CLEFAIRY_COL = 2;
    private static final int CLEFAIRY_ROW = 3;
    private static final int CLEFAIRY_ANIMATION_PERIOD = 4;
    private static final int CLEFAIRY_ACTION_PERIOD = 5;

    public static final String CHARMANDER_KEY = "charmander";
    public static final String BULBASAUR_KEY = "bulbasaur";
    public static final String SQUIRTLE_KEY = "squirtle";

    private static final int POKEMON_NUM_PROPERTIES = 6;
    private static final int POKEMON_ID = 1;
    private static final int POKEMON_COL = 2;
    private static final int POKEMON_ROW = 3;
    private static final int POKEMON_ANIMATION_PERIOD = 4;
    private static final int POKEMON_ACTION_PERIOD = 5;



    public static final String STUMP_KEY = "stump";

    public static final String TREE_KEY = "tree";
    private static final int TREE_NUM_PROPERTIES = 7;
    private static final int TREE_ID = 1;
    private static final int TREE_COL = 2;
    private static final int TREE_ROW = 3;
    private static final int TREE_ANIMATION_PERIOD = 4;
    private static final int TREE_ACTION_PERIOD = 5;
    private static final int TREE_HEALTH = 6;

    public static final int TREE_ANIMATION_MAX = 600;
    public static final int TREE_ANIMATION_MIN = 50;
    public static final int TREE_ACTION_MAX = 1400;
    public static final int TREE_ACTION_MIN = 1000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;

    public static boolean parseBackground(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
                    Integer.parseInt(properties[BGND_ROW]));
            String id = properties[BGND_ID];
            world.setBackground(pt, new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == BGND_NUM_PROPERTIES;
    }

    public static boolean parseSapling(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SAPLING_COL]),
                    Integer.parseInt(properties[SAPLING_ROW]));
            String id = properties[SAPLING_ID];
            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
            Sapling entity = Factory.createSapling(id, pt, imageStore.getImageList(SAPLING_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == SAPLING_NUM_PROPERTIES;
    }

    public static boolean parseGrowingSpawner(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == GROWINGSPAWNER_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[GROWINGSPAWNER_COL]),
                    Integer.parseInt(properties[GROWINGSPAWNER_ROW]));
            String id = properties[GROWINGSPAWNER_ID];
            int health = Integer.parseInt(properties[GROWINGSPAWNER_HEALTH]);
            GrowingSpawner entity = Factory.createGrowingSpawner(id, pt, imageStore.getImageList(GROWINGSPAWNER_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == GROWINGSPAWNER_NUM_PROPERTIES;
    }

    public static boolean parseDude(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[DUDE_COL]),
                    Integer.parseInt(properties[DUDE_ROW]));
            DudeNotFull entity = Factory.createDudeNotFull(properties[DUDE_ID],
                    pt,
                    Integer.parseInt(properties[DUDE_ACTION_PERIOD]),
                    Integer.parseInt(properties[DUDE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[DUDE_LIMIT]),
                    imageStore.getImageList(DUDE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == DUDE_NUM_PROPERTIES;
    }

    public static boolean parseFairy(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == FAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[FAIRY_COL]),
                    Integer.parseInt(properties[FAIRY_ROW]));
            Fairy entity = Factory.createFairy(properties[FAIRY_ID],
                    pt,
                    Integer.parseInt(properties[FAIRY_ACTION_PERIOD]),
                    Integer.parseInt(properties[FAIRY_ANIMATION_PERIOD]),
                    imageStore.getImageList(FAIRY_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == FAIRY_NUM_PROPERTIES;
    }

    public static boolean parseClefairy(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == CLEFAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[CLEFAIRY_COL]),
                    Integer.parseInt(properties[CLEFAIRY_ROW]));
            Clefairy entity = Factory.createClefairy(properties[CLEFAIRY_ID],
                    pt,
                    Integer.parseInt(properties[CLEFAIRY_ACTION_PERIOD]),
                    Integer.parseInt(properties[CLEFAIRY_ANIMATION_PERIOD]),
                    imageStore.getImageList(CLEFAIRY_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == CLEFAIRY_NUM_PROPERTIES;
    }

    public static boolean parseCharmander(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == POKEMON_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[POKEMON_COL]),
                    Integer.parseInt(properties[POKEMON_ROW]));
            Charmander entity = Factory.createCharmander(properties[POKEMON_ID],
                    pt,
                    Integer.parseInt(properties[POKEMON_ACTION_PERIOD]),
                    Integer.parseInt(properties[POKEMON_ANIMATION_PERIOD]),
                    imageStore.getImageList(CHARMANDER_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == POKEMON_NUM_PROPERTIES;
    }

    public static boolean parseBulbasaur(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == POKEMON_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[POKEMON_COL]),
                    Integer.parseInt(properties[POKEMON_ROW]));
            Bulbasaur entity = Factory.createBulbasaur(properties[POKEMON_ID],
                    pt,
                    Integer.parseInt(properties[POKEMON_ACTION_PERIOD]),
                    Integer.parseInt(properties[POKEMON_ANIMATION_PERIOD]),
                    imageStore.getImageList(BULBASAUR_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == POKEMON_NUM_PROPERTIES;
    }

    public static boolean parseSquirtle(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == POKEMON_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[POKEMON_COL]),
                    Integer.parseInt(properties[POKEMON_ROW]));
            Squirtle entity = Factory.createSquirtle(properties[POKEMON_ID],
                    pt,
                    Integer.parseInt(properties[POKEMON_ACTION_PERIOD]),
                    Integer.parseInt(properties[POKEMON_ANIMATION_PERIOD]),
                    imageStore.getImageList(SQUIRTLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == POKEMON_NUM_PROPERTIES;
    }



    public static boolean parseTree(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[TREE_COL]),
                    Integer.parseInt(properties[TREE_ROW]));
            Tree entity = Factory.createTree(properties[TREE_ID],
                    pt,
                    Integer.parseInt(properties[TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[TREE_HEALTH]),
                    imageStore.getImageList(TREE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == TREE_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Obstacle entity = Factory.createObstacle(properties[OBSTACLE_ID], pt,
                    Integer.parseInt(properties[OBSTACLE_ANIMATION_PERIOD]),
                    imageStore.getImageList(OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseHouse(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[HOUSE_COL]),
                    Integer.parseInt(properties[HOUSE_ROW]));
            House entity = Factory.createHouse(properties[HOUSE_ID], pt,
                    imageStore.getImageList(HOUSE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == HOUSE_NUM_PROPERTIES;
    }

    public static boolean parseSpawner(
            String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == SPAWNER_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SPAWNER_COL]),
                    Integer.parseInt(properties[SPAWNER_ROW]));
            Spawner entity = Factory.createSpawner(properties[SPAWNER_ID], pt,
                    imageStore.getImageList(SPAWNER_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == SPAWNER_NUM_PROPERTIES;
    }
}
