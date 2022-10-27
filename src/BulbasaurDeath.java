import processing.core.PImage;

import java.util.List;

public class BulbasaurDeath extends DeathEffect{
    public BulbasaurDeath(
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
