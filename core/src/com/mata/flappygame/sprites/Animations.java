package com.mata.flappygame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animations {

    private Array<TextureRegion> frames;
    private float maxTimeframe ; // this's gonna tell how long it's gonna stay in view before the next one come over
    private float currentFrameTime; //time the animation has been in frame
    private int frameCount; //time the frames has passed
    private int frame; //current frame we're in

    public Animations(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i<frameCount; i++){
            //temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());;
            frames.add(new TextureRegion(region, i* frameWidth, 0, frameWidth, region.getRegionHeight()));
            //frames.add(temp);
        }
        this.frameCount = frameCount;
        maxTimeframe = cycleTime / frameCount;
        frame = 0;


    }
    public void update (float dt){
        currentFrameTime += dt;
        if (currentFrameTime  > maxTimeframe){
            frame++;
            currentFrameTime = 0;

        }
        if(frame >= frameCount)
            frame = 0;
    }
    public TextureRegion getFrame(){
        return  frames.get(frame);
    }
}
