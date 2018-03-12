import processing.core.PImage;

import java.util.List;

public abstract class AnimatedAbstract extends ActiveAbstract implements AnimatedEntity {
    private final int animationPeriod;

    public AnimatedAbstract(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }

    public int getAnimationPeriod()
    {
        return animationPeriod;
    }
    public void nextImage()
    {
        imageIndex = (imageIndex + 1) % getImages().size();
    }
    public Action createAnimation(int repeatCount)
    {
        return new Animation(this, repeatCount);
    }
}
