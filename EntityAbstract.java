import processing.core.PImage;

import java.util.List;

public abstract class EntityAbstract implements Entity{
    private Point position;
    private final List<PImage> images;
    protected int imageIndex;
    private final String id;

    public EntityAbstract(String id, Point position, List<PImage> images)
    {
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.id =id;
    }
    public PImage getCurrentImage() {
        return images.get(imageIndex);
    }
    public Point getPosition(){return position;}
    public void setPosition(Point p){position = p;}
    protected List<PImage> getImages() {return images;}
    protected String getId() {return id;}
}
