import processing.core.PImage;
import java.util.*;

public class Obstacle extends Animates{

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }

    public PImage getCurrentImage() {
        return (this.images.get(this.imageIndex));
    }
}
