package gameObject;

public class Constants
{

    //dimesiones de los fotogramas (frames)
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    //propiedades del jugador
    public static final int FIRERATE = 300;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL = 7.0;
    public static final long FLICKER_TIME = 200;
    public static final long SPAWNIG_TIME = 3000;

    //propiedades del laser
    public static final double LASER_VEl = 15.0;

    //propiedades de los meteoros
    public static final double METEOR_VEL = 2.0;
    public static final int METEOR_SCORE = 20;

    //enemigo
    public static final double NODE_RADIUS = 160;
    public static final double UFO_MASS = 60;
    public static final double UFO_MAX_VEL = 3;
    public static final long UFO_FIRERATE = 1000;
    public static double UFO_ANGLE_RANGE = Math.PI / 2;
    public static final int UFO_SCORE = 40;

}
