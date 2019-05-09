package com.mata.flappygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mata.flappygame.FlappyMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//these config are for  sizable windows
		config.width=FlappyMain.WIDTH;
		config.height=FlappyMain.HEIGHT;
		config.title=FlappyMain.TITLE;
		config.resizable=false;
		new LwjglApplication(new FlappyMain(), config);
	}
}
