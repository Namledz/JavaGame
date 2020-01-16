package main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	public static void load() {
		try {
			soundMap.put("menu_sound", new Sound("resource/click_sound.ogg"));

			musicMap.put("music", new Music("resource/background_music.ogg"));

			soundMap.put("coins_sound", new Sound("resource/coins_sound.ogg"));
			
			soundMap.put("hitting_sound", new Sound("resource/hitting_sound.ogg"));
			
			soundMap.put("losing_sound", new Sound("resource/losing_sound.ogg"));
			

		} catch (SlickException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	public static Music getMusic(String key) {
		return musicMap.get(key);
	}

	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

}
