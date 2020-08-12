package com.game.src.interfaces;

import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.graphics.Sprite;
import com.game.src.main.Game;
import com.game.src.objects.GameObject;

public abstract class GUIButton implements GameObject
{

    protected Sprite sprite;
    protected Rectangle rect;
    protected boolean fixed;

    public GUIButton(Sprite sprite, Rectangle rect, boolean fixed)
    {
        this.sprite = sprite;
        this.rect = rect;
        this.fixed = fixed;
    }

    @Override
    public void tick(Game game)
    {
    }

    @Override
    public void render(RenderHandler renderer, int xZoom, int yZoom)
    {
    }

    public void render(RenderHandler renderer, int xZoom, int yZoom, Rectangle interfaceRect)
    {
        renderer.renderSprite(sprite, rect.x + interfaceRect.x, rect.y + interfaceRect.y, xZoom, yZoom, fixed);
    }

    @Override
    public boolean handleMouseClick(Rectangle mouseRectangle, Rectangle camera, int xZoom, int yZoom)
    {
        if (mouseRectangle.intersects(rect))
        {
            activate();

            return true;
        }

        return false;
    }

    public abstract void activate();

}
