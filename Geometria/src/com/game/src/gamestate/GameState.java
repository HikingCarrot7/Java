package com.game.src.gamestate;

import com.game.src.object.MovingObject;
import com.game.src.object.Player;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class GameState
{

    private Player player;
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
