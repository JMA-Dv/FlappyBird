package com.mata.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mata.flappygame.FlappyMain;
import com.mata.flappygame.sprites.Bird;

import javax.swing.MenuSelectionManager;

public class GameOverState extends State{

    private Texture over;
    private Texture background;
    private Bird soundStop;
    private String scoreName;
    private String recordName;
    private BitmapFont scoreFontName;//this is for printing score as image
    private BitmapFont recordFontName;
    private int score, record;
    FlappyMain flappy = new FlappyMain();
     GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        cam.setToOrtho(false, FlappyMain.WIDTH / 2, FlappyMain.HEIGHT / 2);// this changes all the camera view and the orientation of all Y vector
        over = new Texture("gameover.png");
        background = new Texture("background-night.png");
        scoreFontName = new BitmapFont();
        recordFontName = new BitmapFont();
        score = gameStateManager.getScore();
        record = gameStateManager.getRecord();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gameStateManager.set(new MenuState(gameStateManager));
            gameStateManager.setScore(0);
            System.out.println("Score se resetea a 0");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
         scoreName = "Your score: " + score;
         recordName = "Record: " + record;


        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(over,cam.position.x - (over.getWidth() /2 ),cam.position.y);
        recordFontName.setColor(Color.RED);
        recordFontName.getData().setScale(1,1);//score size
        recordFontName.draw(sb,recordName,cam.position.x /2  - scoreFontName.getSpaceXadvance()/2,380);//print score

        recordFontName.setColor(Color.YELLOW);
        recordFontName.getData().setScale(1, 1);
        recordFontName.draw(sb,scoreName,(cam.position.x /2 - scoreFontName.getSpaceXadvance()/2) +20, 350);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        over.dispose();
        System.out.println("Player state GameOver Disposed");

    }
}
