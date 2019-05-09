package com.mata.flappygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mata.flappygame.FlappyMain;
import com.mata.flappygame.sprites.Bird;
import com.mata.flappygame.sprites.Tube;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125;//spacing between tubes
    private static final int TUBE_COUNT = 4;// how many tubes in any giving time the game have
    private static final int GROUND_Y_OFFSET = -30;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Texture gameOver;
    private boolean gameOve;
    private Sound gmido;
    private Array<Tube>tubes;
    private Vector2 groundPos1,groundPos2;
    private String score;
    private int scoreValue;
    private BitmapFont bitmapFontScore;
    private FlappyMain main;


    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        main = new FlappyMain();
        scoreValue = 0;
        score = "0";
        bitmapFontScore = new BitmapFont();

        //bird=new Texture("bluebirdmid.png"); passed all the parameters to the other class that is going to be all of the player possition and textures
        cam.setToOrtho(false,FlappyMain.WIDTH / 2, FlappyMain.HEIGHT / 2);// this changes all the camera view and the orientation of all Y vector
        bird= new Bird(50,250);

        bg= new Texture("bg.png");//this texture is for after touching the screen
        ground = new Texture("ground.png");
        gameOver= new Texture("gameover.png");

        groundPos1 = new Vector2(cam.position.x- cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 =  new Vector2((cam.position.x-cam.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);
        tubes= new Array<Tube>();
        gmido =  Gdx.audio.newSound(Gdx.files.internal("gmido.mp3"));
        for (int i =1; i<=TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        gameOve= false;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            if(gameOve) {
                gameStateManager.set(new PlayState(gameStateManager));

            }else {
                bird.jump();
                //dispose();// this prevent a future lag and free the memory
            }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        //ground.dispose();

        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;// the cam is gonna be moving along the bird position to focus on it
        //here gonna be the respawn the tube after running out
        for(int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosButtonTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                scoreValue++;
                gameStateManager.setScore(scoreValue);
                score= ""+scoreValue;
                System.out.println("El score es: "+ gameStateManager.getScore());
            }
            if(bird.getPosition().x > tube.getPosButtonTube().x && bird.getPosition().x <tube.getPosTopTube().x + tube.getTopTube().getWidth() -50){
                // here is a sound where passes the tubes through

                System.out.println("El score es: "+ gameStateManager.getScore());
            }
            if (tube.collide(bird.getBounds())) {
                //main.stopMusic();
                gmido.play();
                gameStateManager.set(new GameOverState(gameStateManager)); // the instance is gonna appear only if the bird and the tubes collide
                System.out.println("Tube collided");
                if(gameStateManager.getScore()>scoreValue){
                    System.out.println("Score is"+gameStateManager.getScore());
                }else{
                    gameStateManager.setRecord(scoreValue);
                }
                System.out.println("El score es: "+ gameStateManager.getScore());
                //gmido.dispose();

            }
            if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
                    //main.stopMusic();
                    gmido.play(1);
                    gameStateManager.set(new GameOverState(gameStateManager));
                    System.out.println("hit the ground");
                System.out.println("El score es: "+ gameStateManager.getScore());
                    break;
                    //gmido.dispose();
            }


        }
        cam.update();// updates where the camera is
    }


    @Override
    public void render(SpriteBatch sb) {//here we create and draw everything to the screen
        sb.setProjectionMatrix(cam.combined);//sets the object bigger that it is
        sb.begin();
        //bitmapFontScore
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);//this draw the background after touching

        for(Tube tube :tubes){
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getButtonTube(), tube.getPosTopTube().x,tube.getPosButtonTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x, groundPos2.y);

        sb.draw(bird.getTextureBird(),bird.getPosition().x,bird.getPosition().y);

        bitmapFontScore.setColor(1.0f,1.0f,1.f,1.0f);
        bitmapFontScore.getData().setScale(1,1);
        bitmapFontScore.draw(sb,score,cam.position.x - cam.viewportWidth+230 , 400);
        sb.end();


    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube: tubes){
            tube.dispose();
        }
        System.out.println("Player state disposed");
    }
    public void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > (groundPos1.x + ground.getWidth()))
            groundPos1.add(ground.getWidth() * 2,0 );
        if(cam.position.x - (cam.viewportWidth / 2) > (groundPos2.x + ground.getWidth()))
            groundPos2.add(ground.getWidth() * 2,0 );
    }
}
