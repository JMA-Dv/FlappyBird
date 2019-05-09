package com.mata.flappygame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH =52;//the width of the image
    private static final int FLUCTUATION = 130;//this is gonna move up and down     randomly between 130 and 0
    private static final  int TUBE_GAP = 100;// this's for the difference between the space of each tube
    private static final int LOWEST_OPENING = 120;

    private Rectangle boundsTop, boundBot;
    private Random rand;
    private Vector2 posTopTube, posButtonTube;
    private Texture topTube,buttonTube;

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosButtonTube() {
        return posButtonTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getButtonTube() {
        return buttonTube;
    }

    public Tube(float x){
        topTube= new Texture("toptube.png");
        buttonTube = new Texture("buttonTubegreen.png");
        rand= new Random();

        posTopTube= new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);//
        posButtonTube= new Vector2(x,posTopTube.y-TUBE_GAP-buttonTube.getHeight());

        boundsTop= new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundBot = new Rectangle(posButtonTube.x,posButtonTube.y,buttonTube.getWidth(),buttonTube.getHeight());

    }
    public void reposition(float x){
    posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
    posButtonTube.set(x,posTopTube.y - TUBE_GAP - buttonTube.getHeight());

    boundsTop.setPosition(x, posTopTube.y);
    boundBot.setPosition(x, posButtonTube.y);

    }
    public boolean collide(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundBot);// this returns if the player (which is bird is overlapping with each other)

    }
    public void dispose(){
        topTube.dispose();
        buttonTube.dispose();
    }
}
