package graphics;

import java.awt.image.BufferedImage;

public class Assets
{

    public static BufferedImage speeds;
    public static BufferedImage player;
    public static BufferedImage blueLaser, greenLaser, redLaser;

	//enemigo
    public static BufferedImage ufo;

	//vida 
    public static BufferedImage life;

	//puntaje
    public static BufferedImage[] numbers = new BufferedImage[11];

	//efecto de exlosion
    public static BufferedImage[] exp = new BufferedImage[9];

	//meteoros
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];

    public static void init()
    {
        player = carga.imageloader("/naves1/player.png");
        speeds = carga.imageloader("/effects/fire14.png");
        blueLaser = carga.imageloader("/lasers/laserBlue01.png");
        greenLaser = carga.imageloader("/lasers/laserGreen11.png");
        redLaser = carga.imageloader("/lasers/laserRed01.png");

        for (int i = 0; i < bigs.length; i++)
        {
            bigs[i] = carga.imageloader("/meteors/meteorGrey_big" + (i + 1) + ".png");
        }

        for (int i = 0; i < meds.length; i++)
        {
            meds[i] = carga.imageloader("/meteors/meteorGrey_med" + (i + 1) + ".png");
        }

        for (int i = 0; i < smalls.length; i++)
        {
            smalls[i] = carga.imageloader("/meteors/MeteorGrey_small" + (i + 1) + ".png");
        }

        for (int i = 0; i < tinies.length; i++)
        {
            tinies[i] = carga.imageloader("/meteors/meteorGrey_tiny" + (i + 1) + ".png");
        }

        for (int i = 0; i < exp.length; i++)
        {
            exp[i] = carga.imageloader("/explosion/" + i + ".png");
        }

        ufo = carga.imageloader("/naves1/ufo.png");

        life = carga.imageloader("/vida/life.png");

        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i] = carga.imageloader("/vida/" + i + ".png");
        }

    }
}
