package com.mata.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mata.flappygame.FlappyMain;

public class MenuState extends State {
    private Texture background;
    private Texture playbtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        cam.setToOrtho(false,FlappyMain.WIDTH / 2, FlappyMain.HEIGHT / 2);// this changes all the camera view and the orientation of all Y vector
        background=new Texture("bg.png");
        playbtn=new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        //if the user touches the screen it's gonna instance the playstate
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
            // this prevent a future lag and free the memory
        }

    }

    @Override
    public void update(float dt) {
        handleInput();  //call the method for every update of screen
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(background,0,0, FlappyMain.WIDTH,FlappyMain.HEIGHT);
        //sb.draw(playbtn,(FlappyMain.WIDTH / 2 )-(playbtn.getWidth() / 2),(FlappyMain.HEIGHT/2));
        sb.draw(background,0,0);
        sb.draw(playbtn,cam.position.x - playbtn.getWidth() / 2,cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
