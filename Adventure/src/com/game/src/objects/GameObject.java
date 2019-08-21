package com.game.src.objects;

import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.main.Game;

public interface GameObject
{

    public void tick(Game game);

    public void render(RenderHandler renderer, int xZoom, int yZoom);

    public boolean handleMouseClick(Rectangle mouseRectangle, Rectangle camera, int xZoom, int yZoom);

}
