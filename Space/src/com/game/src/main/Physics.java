package com.game.src.main;

import com.game.src.main.interfaces.Entity;

public class Physics
{

    public static <T extends Entity> boolean Collision(T entidadA, T entidadB)
    {
        if (entidadA.getBounds(9, 54).intersects(entidadB.getBounds(40, 20)))
        {
            return true;
        }

        return false;
    }

}
