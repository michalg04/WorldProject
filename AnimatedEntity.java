public interface AnimatedEntity extends ActiveEntity {

    Action createAnimation(int repeatCount);
    void nextImage();
    int getAnimationPeriod();
}
