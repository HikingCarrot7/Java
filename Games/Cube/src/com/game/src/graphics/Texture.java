package com.game.src.graphics;

import java.awt.image.BufferedImage;

public class Texture
{

    private final SpriteSheet images;

    public BufferedImage player, bounceEnemy, fastEnemy, smartEnemy;

    public Texture()
    {

        BufferedImageLoader loader = new BufferedImageLoader();

        images = new SpriteSheet(loader.loadImage("img/sprite_sheet.png"));

        getTextures();

    }

    private void getTextures()
    {
        player = images.grabImage(1, 1, 32, 32);
        bounceEnemy = images.grabImage(1, 3, 16, 16);
        fastEnemy = images.grabImage(1, 5, 16, 16);
        smartEnemy = images.grabImage(1, 7, 16, 16);
    }

}
