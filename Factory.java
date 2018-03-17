import processing.core.PImage;

import java.util.List;

public class Factory {

    private static final String QUAKE_ID = "quake";
    private static final int QUAKE_ACTION_PERIOD = 1100;
    private static final int QUAKE_ANIMATION_PERIOD = 100;


    public static Blacksmith createBlacksmith(String id, Point position,
                                              List<PImage> images) {
        return new Blacksmith(id, position, images);
    }

    public static Restaurant createRestaurant(String id, Point position,
                                              List<PImage> images) {
        return new Restaurant(id, position, images);
    }

    public static MinerFull createMinerFull(String id, int resourceLimit,
                                            Point position, int actionPeriod, int animationPeriod,
                                            List<PImage> images) {
        return new MinerFull(id, position, images,
                resourceLimit, resourceLimit, actionPeriod, animationPeriod);
    }

    public static MinerNotFull createMinerNotFull(String id, int resourceLimit,
                                                  Point position, int actionPeriod, int animationPeriod,
                                                  List<PImage> images) {
        return new MinerNotFull(id, position, images, resourceLimit, 0, actionPeriod, animationPeriod);
    }

    public static JulieNotFull createJulieNotFull(String id, int resourceLimit,
                                                  Point position, int actionPeriod, int animationPeriod,
                                                  List<PImage> images) {
        return new JulieNotFull(id, position, images, resourceLimit, 0, actionPeriod, animationPeriod);
    }

    public static JulieFull createJulieFull(String id, int resourceLimit,
                                                   Point position, int actionPeriod, int animationPeriod,
                                                   List<PImage> images) {
        return new JulieFull(id, position, images, resourceLimit, 0, actionPeriod, animationPeriod);
    }

    public static Obstacle createObstacle(String id, Point position,
                                          List<PImage> images) {
        return new Obstacle(id, position, images);
    }

    public static Ore createOre(String id, Point position, int actionPeriod,
                                List<PImage> images) {
        return new Ore(id, position, images, actionPeriod);
    }

    public static Cookie createCookie(String id, Point position, int actionPeriod,
                                      List<PImage> images) {
        return new Cookie(id, position, images, actionPeriod);
    }

    public static Flower createFlower(String id, Point position, List<PImage> images) {
        return new Flower(id, position, images);
    }

    public static OreBlob createOreBlob(String id, Point position,
                                        int actionPeriod, int animationPeriod, List<PImage> images) {
        return new OreBlob(id, position, images, actionPeriod, animationPeriod);
    }

    public static Rat createRat(String id, Point position,
                                int actionPeriod, int animationPeriod, List<PImage> images) {
        return new Rat(id, position, images, actionPeriod, animationPeriod);
    }

    public static Quake createQuake(Point position, List<PImage> images) {
        return new Quake(QUAKE_ID, position, images, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
    }

    public static Vein createVein(String id, Point position, int actionPeriod,
                                  List<PImage> images) {
        return new Vein(id, position, images, actionPeriod);
    }
}
