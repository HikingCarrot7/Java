package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Carga
{
    //seccion de carga de imagenes,audio,etc

    public static BufferedImage imageloader(String path)
    {
        try
        {
            return ImageIO.read(Carga.class.getResource(path));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
