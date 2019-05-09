package com.mata.flappygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY=-25;
    private Vector3 position;
    private  static final int MOVEMENT = 100;//this's for the horizontal movement
    private Vector3 velocity;
    private Texture textureBird;
    private Rectangle boundBird;
    private  Animations birdAnimation;
    private Texture text;

    private Sound touchSound;
    public Bird(int x, int y ){

        position= new Vector3(x,y,0);
        velocity= new Vector3(0,0,0);
        //textureBird= new Texture("bluebirdmid.png");
         text= new Texture("birdanimation.png");
        birdAnimation = new Animations(new TextureRegion(text),3,0.5f);
        boundBird= new Rectangle ( x, y, text.getWidth() /3 ,text.getHeight());
        touchSound= Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));

    }
    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY,0);//here's adding only gravety to it's position
        velocity.scl(dt);// this is gonna scale the velocity by it's changing time
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y < 0)
            position.y=0;

        velocity.scl( 1 / dt);//check this helps to see smoother the jump

        boundBird.setPosition(position.x,position.y);
    }
    public Rectangle getBounds(){//the bounds are from the rectangle that remains in the texture bounds from the bird
        return boundBird;
    }


    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTextureBird() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y=300;
        touchSound.play(0.1f);
    }
    public void dispose(){
        text.dispose();
        touchSound.dispose();
    }

}
