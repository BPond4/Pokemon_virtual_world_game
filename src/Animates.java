import processing.core.PImage;

import java.util.List;

public abstract class Animates extends Entity {

    protected int imageIndex;
    protected final int animationPeriod;

    public Animates(String id, Point position, List<PImage> images, int animationPeriod) {
        super(id, position, images);
        this.imageIndex = 0;
        this.animationPeriod = animationPeriod;
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this,0),
                this.getAnimationPeriod());
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }
    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    @Override
    public PImage getCurrentImage() {
        return (this.images.get(this.imageIndex));
    }
}
