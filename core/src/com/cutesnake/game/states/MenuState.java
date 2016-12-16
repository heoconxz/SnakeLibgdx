package com.cutesnake.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cutesnake.game.SnakeGame;

/**
 * Created by heoconxz on 12/12/2016.
 */

public class MenuState extends State {

    private TextButton playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        //playBtn = new TextButton("Play",new TextButton.TextButtonStyle());

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm) );
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(playBtn, 0,0, (SnakeGame.WIDTH/2), (SnakeGame.HEIGHT/2));
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
