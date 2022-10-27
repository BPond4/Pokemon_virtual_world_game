/**
 * An action that can be taken by an entity
 */
public final class ActivityAction implements Action
{
    private final Acts entity;
    private final WorldModel world;
    private final ImageStore imageStore;

    public ActivityAction(
            Acts entity,
            WorldModel world,
            ImageStore imageStore)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(
            EventScheduler scheduler)
    {
        this.entity.executeActivity(this.world,
                this.imageStore, scheduler);
    }
}
