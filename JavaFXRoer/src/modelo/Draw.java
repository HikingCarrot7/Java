package modelo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Mohammed
 */
public class Draw
{

    public Canvas canvas;

    public Draw(Canvas canvas)
    {
        this.canvas = canvas;
    }

    public void draw()
    {
        GraphicsContext g2d = canvas.getGraphicsContext2D();

        g2d.fillOval(50, 50, 50, 50);

    }

}
