package com.mata.flappygame.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
private Stack<State> states;
int score;
int record;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if(this.score >record){
            record = this.score;
        }
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public GameStateManager(){
states= new Stack<State>();
}

public void push(State state){
    states.push(state);
}
public void pop(){
    states.pop().dispose();//dispose means the state and we're not gonna play it again
}
public void set(State state){
    states.pop().dispose();
    states.push(state);
}

public void update(float dt){
    states.peek().update(dt);

}
public void render (SpriteBatch sb){
    states.peek().render(sb);
}
}
