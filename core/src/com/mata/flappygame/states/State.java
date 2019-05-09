package com.mata.flappygame.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;// each world is a new camera  in order to have more views
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager){
        this.gameStateManager=gameStateManager;
        mouse= new Vector3();
        cam=new OrthographicCamera();

    }

    protected abstract void handleInput();
    public abstract  void update(float dt); // this's gonna be the difference between one frame
                                    //render and other with a parameter float

    public abstract  void render(SpriteBatch sb);//it's a container for everything we need to render
                                                // to the screen
    public abstract void dispose();
}
