package com.game.src.graphics;

import java.awt.image.BufferedImage;

import com.game.src.main.BufferedImageLoader;

public class Textures
{

    private SpriteSheet bs, ps;

    public BufferedImage[] block;

    public BufferedImage[] player;

    public BufferedImage[] playerJump;

    public Textures()
    {
        BufferedImageLoader loader = new BufferedImageLoader();

        bs = new SpriteSheet(loader.loadImage("/block_sheet.png"));

        ps = new SpriteSheet(loader.loadImage("/player_sheet.png"));

        block = new BufferedImage[2];

        player = new BufferedImage[14];

        playerJump = new BufferedImage[6];

        getTexture();

    }

    private void getTexture()
    {
        for (int i = 0; i < block.length; i++)
        {
            block[i] = bs.grabImage(1, i + 1, 32, 32);
        }

        for (int i = 0; i < player.length; i++)
        {
            if (i < 7)
            {
                player[i] = ps.grabImage(1, i + 1, 32, 64);
            } else
            {
                player[i] = ps.grabImage(1, 27 - i, 32, 64);
            }
        }

        for (int i = 0; i < playerJump.length; i++)
        {
            if (i < 3)
            {
                playerJump[i] = ps.grabImage(2, 8 + i, 32, 64);
            } else
            {
                playerJump[i] = ps.grabImage(2, 16 - i, 32, 64);
            }
        }

    }

}
