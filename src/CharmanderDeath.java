import processing.core.PImage;

import java.util.List;

public class CharmanderDeath extends DeathEffect{

    public CharmanderDeath(
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
