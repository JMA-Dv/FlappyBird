package com.mata.flappygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mata.flappygame.states.GameStateManager;
import com.mata.flappygame.states.MenuState;

public class FlappyMain extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE = "Flappy clone";
	private GameStateManager gsm;//works for render and update
	private SpriteBatch batch; //are heavy and only one for imagestate

	Texture img;
	private Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm= new GameStateManager();
		music =  Gdx.audio.newMusic(Gdx.files.internal("Naruto flauta.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();

		img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(0, 1, 0, 1);//it whites all the screen
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());// is going to tell the diference between out render times
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
		music.stop();

	}
	public void stopMusic(){
		music.pause();

		music.stop();
	}


}
