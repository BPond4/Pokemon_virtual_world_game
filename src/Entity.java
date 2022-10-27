import processing.core.PImage;

import java.util.List;

public abstract class Entity {

    protected final String id;
    protected Point position;
    protected final List<PImage> images;

    public Entity(
            String id,
            Point position,
            List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
    }

    public String getId() {
        return id;
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point p) {this.position = p;}
    public PImage getCurrentImage() {
        return (this.images.get(0));
    }
}
