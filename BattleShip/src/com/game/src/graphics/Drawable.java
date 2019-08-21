package com.game.src.graphics;

import java.awt.Graphics2D;

/**
 *
 * @author HikingCarrot7
 */
public interface Drawable
{

    public abstract void render(Graphics2D g);

    public abstract void tick();
}
