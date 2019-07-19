package com.game.src.graphics;

import com.game.src.objects.GameId;
import com.game.src.objects.GameObject;
import com.game.src.objects.Player;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot
 */
public class RenderHandler 
{
    private ArrayList<GameObject> objects;
    
    public RenderHandler()
    {
        objects = new ArrayList<>();
    }
    
    public void tick()
    {
        for (int i = 0; i < objects.size(); i++) 
        {
            GameObject object = objects.get(i);
            
            object.tick();
        }
        
    }
    
    public void render(Graphics2D g)
    {
        for (int i = 0; i < objects.size(); i++) 
        {
            GameObject object = objects.get(i);
            
            object.render(g);
        }
        
    }
    
    public ArrayList<GameObject> getObjects() 
    {
        return objects;
    }

    public void addObject(GameObject object) 
    {
        objects.add(object);
    }
    
    public void removeObject(GameObject object) 
    {
        objects.remove(object);
    }
    
    public Player getPlayer()
    {
        for (int i = 0; i < objects.size(); i++) 
        {
            GameObject object = objects.get(i);
            
            if(object.getGameId().equals(GameId.Player))
                return (Player) object;
        }
        
        return null;
    }
}
