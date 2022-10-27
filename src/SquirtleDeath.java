import processing.core.PImage;

import java.util.*;

public class SquirtleDeath extends DeathEffect{

    public SquirtleDeath(
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


}
