package com.mata.flappygame.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ContinueState extends  State {
    private Texture bg;
    private String scoreName;
    private String recordName;
    private BitmapFont scoreFontName;
    private int score,record;


    protected ContinueState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
