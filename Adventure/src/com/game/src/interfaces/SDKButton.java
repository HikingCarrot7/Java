package com.game.src.interfaces;

import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.graphics.Sprite;
import com.game.src.main.Game;

public class SDKButton extends GUIButton
{

    private Game game;
    private int tileID;

    private boolean isGreen = false;

    public SDKButton(Sprite tileSprite, int tileID, Rectangle rect, Game game)
    {
        super(tileSprite, rect, true);

        this.game = game;
        this.tileID = tileID;

        rect.generateGraphics(0xFFFFFF00);

    }

    @Override
    public void tick(Game game)
    {
        if (tileID == game.getSelectedTile())
        {
            if (!isGreen)
            {
                rect.generateGraphics(0xFF4CFF00);
                isGreen = true;
            }

        } else if (isGreen)
        {
            rect.generateGraphics(0xFFFFFF00);
            isGreen = false;
        }
    }

    @Override
    public void render(RenderHandler renderer, int xZoom, int yZoom)
    {
    }

    @Override
    public void render(RenderHandler renderer, int xZoom, int yZoom, Rectangle interfaceRect)
    {
        renderer.renderRectangle(rect, interfaceRect, 1, 1, fixed);
        renderer.renderSprite(sprite,
                rect.x + interfaceRect.x + (xZoom - (xZoom - 1)) * rect.w / 2 / xZoom,
                rect.y + interfaceRect.y + (yZoom - (yZoom - 1)) * rect.h / 2 / yZoom,
                xZoom - 1,
                yZoom - 1,
                fixed);
    }

    @Override
    public void activate()
    {
        game.changeTile(tileID);
    }

}
