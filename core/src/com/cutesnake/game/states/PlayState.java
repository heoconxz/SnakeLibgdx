package com.cutesnake.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cutesnake.game.SimpleDirectionGestureDetector;
import com.cutesnake.game.SnakeGame;
import com.cutesnake.game.sprites.Snake;

import static com.badlogic.gdx.Input.Keys.*;

/**
 * Created by heoconxz on 12/12/2016.
 */

public class PlayState extends State {

    private Snake snake;
    private BitmapFont font;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont(Gdx.files.internal("bubble.fnt"),false);
        snake = new Snake();
        cam.setToOrtho(false, SnakeGame.WIDTH,SnakeGame.HEIGHT);
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                snake.changeDirection(0,1);
                System.out.println("swip up");
            }

            @Override
            public void onRight() {
                snake.changeDirection(1,0);
            }

            @Override
            public void onLeft() {
                snake.changeDirection(-1,0);
            }

            @Override
            public void onDown() {
                snake.changeDirection(0,-1);
                System.out.println("swip down");
            }
        }));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            snake.changeDirection(0,-1);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            snake.changeDirection(0,1);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            snake.changeDirection(-1,0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            snake.changeDirection(1,0);
    }

    @Override
    public void update(float dt) {
        handleInput();
        snake.update(dt);
        if(snake.died)
            gsm.set(new MenuState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font.setColor(1,1,1,1);
        font.draw(sb,"score: "+snake.getScore(),SnakeGame.WIDTH/2-30,SnakeGame.HEIGHT);
        snake.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        snake.dispose();
    }
}
