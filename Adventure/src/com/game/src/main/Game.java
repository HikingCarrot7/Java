package com.game.src.main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.src.graphics.AnimatedSprite;
import com.game.src.graphics.Map;
import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.graphics.Sprite;
import com.game.src.graphics.SpriteSheet;
import com.game.src.graphics.Tiles;
import com.game.src.input.KeyInput;
import com.game.src.input.MouseInput;
import com.game.src.input.MapLoader;
import com.game.src.interfaces.GUI;
import com.game.src.interfaces.GUIButton;
import com.game.src.interfaces.SDKButton;
import com.game.src.objects.GameObject;
import com.game.src.objects.Player;

public class Game extends Canvas
{

    private static final long serialVersionUID = 1L;

    public static int ANCHO = 1000, ALTO = 800;

    public static int alpha = 0xFFFF00DC;

    private int xZoom = 2, yZoom = 2;
    private int selectTileID = 2;

    private static RenderHandler renderer;

    private SpriteSheet mapSheet;
    private SpriteSheet playerSheet;
    private SpriteSheet flowerSheet;

    private AnimatedSprite playerAnimation;
    private AnimatedSprite[] flowerAnimations;

    private Tiles tiles;
    private Map map;

    private GameObject[] objects;

    private GUI gui, inventory;
    private GUIButton[] buttons, inventoryButtons;
    private Sprite[] tileSprite;

    private KeyInput keyInput;
    private MouseInput mouseInput;

    public static void main(String[] args)
    {
        renderer = new RenderHandler(1000, 800);

        new Window(ANCHO, ALTO, "Adventure", new Game(), renderer);
    }

    public void init()
    {
        createBufferStrategy(3);

        new MapLoader(this);
        renderer = new RenderHandler(getWidth(), getHeight());

        //Sheets
        mapSheet = new SpriteSheet(loadImage("/Tiles1.png"));
        playerSheet = new SpriteSheet(loadImage("/player.png"));
        flowerSheet = new SpriteSheet(loadImage("/flower.png"));

        //Loading sprites
        mapSheet.loadSprite(16, 16);
        playerSheet.loadSprite(20, 26);
        flowerSheet.loadSprite(49, 51);

        //Map
        tiles = new Tiles(new File("Tiles.txt"), mapSheet);
        map = new Map(new File("Map.txt"), tiles);

        //Animations
        playerAnimation = new AnimatedSprite(playerSheet, 8);
        flowerAnimations = new AnimatedSprite[100];

        for (int i = 0; i < flowerAnimations.length; i++)
            flowerAnimations[i] = new AnimatedSprite(flowerSheet, 8, i * 50, 70);

        //SDK BUTTONS--------------------------
        //SDK
        buttons = new GUIButton[tiles.tiles()];
        tileSprite = tiles.getSprites();

        for (int i = 0; i < buttons.length; i++)
            buttons[i] = new SDKButton(tileSprite[i], i, new Rectangle(0, i * (16 * xZoom + 2), 16 * xZoom, 16 * yZoom), this);

        gui = new GUI(buttons, 15, 15, true);

        //INVENTORY
        inventoryButtons = new GUIButton[25];

        for (int y = 0; y < 5; y++)
            for (int x = 0; x < 5; x++)
                inventoryButtons[x + y * 5] = new SDKButton(tileSprite[0], 0, new Rectangle(y * (16 * xZoom + 2), x * (16 * xZoom + 2), 16 * xZoom, 16 * yZoom), this);

        //inventory = new GUI(inventoryButtons, ANCHO/2 - 80, ALTO/2 - 80, true);
        //Objects
        objects = new GameObject[2];
        objects[0] = new Player(playerAnimation);
        objects[1] = gui;
        //objects[2] = inventory;

        keyInput = new KeyInput(this);
        mouseInput = new MouseInput(this);

        requestFocus();
        addKeyListener(keyInput);
        addFocusListener(keyInput);
        addMouseMotionListener(mouseInput);
        addMouseListener(mouseInput);

    }

    public void tick()
    {
        for (GameObject gameObject : objects)
            gameObject.tick(this);

        for (int i = 0; i < flowerAnimations.length; i++)
            flowerAnimations[i].tick(this);
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        super.paint(g);

        map.renderMap(renderer, xZoom, yZoom);

        for (GameObject gameObject : objects)
            gameObject.render(renderer, xZoom, yZoom);

        for (AnimatedSprite flowerAnimation : flowerAnimations)
            flowerAnimation.render(renderer, xZoom, yZoom);

        renderer.render(g);

        g.dispose();
        bs.show();
        renderer.clear();
    }

    public BufferedImage loadImage(String path)
    {
        try
        {
            BufferedImage loadedImage = ImageIO.read(Game.class.getResource(path));

            BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);

            return formattedImage;

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public void LeftPressed(int x, int y)
    {
        Rectangle mouseRect = new Rectangle(x, y, 1, 1);

        boolean stoppedChecking = false;

        for (GameObject O : objects)
            if (!stoppedChecking)
                stoppedChecking = O.handleMouseClick(mouseRect, renderer.getCamera(), xZoom, yZoom);

        if (!stoppedChecking)
        {
            x = (int) Math.floor((x + renderer.getCamera().x) / (16.0 * xZoom));
            y = (int) Math.floor((y + renderer.getCamera().y) / (16.0 * yZoom));

            map.setTile(x, y, selectTileID);
        }

    }

    public void RightPressed(int x, int y)
    {
        x = (int) Math.floor((x + renderer.getCamera().x) / (16.0 * xZoom));
        y = (int) Math.floor((y + renderer.getCamera().y) / (16.0 * yZoom));

        map.removeTile(x, y);

    }

    public void changeTile(int selectTileID)
    {
        this.selectTileID = selectTileID;
    }

    public int getSelectedTile()
    {
        return selectTileID;
    }

    public void ZoomMax()
    {
        if (xZoom < 4)
        {
            xZoom++;
            yZoom++;
        }
    }

    public void ZoomMin()
    {
        if (xZoom > 1)
        {
            xZoom--;
            yZoom--;
        }
    }

    public void handleCTRL(boolean[] keys)
    {
        if (keys[KeyEvent.VK_S])
            map.saveMap();
    }

    public KeyInput getKeyListener()
    {
        return keyInput;
    }

    public MouseInput getMouseInput()
    {
        return mouseInput;
    }

    public RenderHandler getRenderer()
    {
        return renderer;
    }

}
