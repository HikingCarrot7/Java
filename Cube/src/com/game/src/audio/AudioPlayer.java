package com.game.src.audio;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer
{

    public static Map<String, Sound> soundMap = new HashMap<>();
    public static Map<String, Music> musicMap = new HashMap<>();

    public static void load()
    {
        try
        {
            soundMap.put("sound", new Sound("/res/click_sound.ogg"));

            musicMap.put("music", new Music("/res/background_music.ogg"));

            musicMap.put("paladins", new Music("/res/paladins.ogg"));

        } catch (SlickException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static Music getMusic(String key)
    {
        return musicMap.get(key);
    }

    public static Sound getSound(String key)
    {
        return soundMap.get(key);
    }

}
