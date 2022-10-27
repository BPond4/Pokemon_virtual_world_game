import processing.core.PImage;

import java.util.List;

public class Spawner extends Entity{
    public Spawner(String id,
                   Point position,
                   List<PImage> images){
        super(id, position, images);
    }

}
