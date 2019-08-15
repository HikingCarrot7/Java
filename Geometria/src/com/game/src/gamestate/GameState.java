package com.game.src.gamestate;

import com.game.src.object.MovingObject;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class GameState
{
    private final ArrayList<MovingObject> objects;
    
    public GameState()
    {
        objects = new ArrayList<>();
    }
    
    public ArrayList<MovingObject> getMovingObjects()
    {
        return objects;
    }
}
