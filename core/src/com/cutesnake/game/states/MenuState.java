package com.cutesnake.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cutesnake.game.SnakeGame;

/**
 * Created by heoconxz on 12/12/2016.
 */

public class MenuState extends State {

    private TextButton playBtn;
    Skin skin;
    private Stage stage;
    BitmapFont font;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        //playBtn = new TextButton("Play",new TextButton.TextButtonStyle());
        Pixmap pixmap = new Pixmap(200,100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        font = new BitmapFont(Gdx.files.internal("bubble.fnt"),false);
        skin = new Skin();
        skin.add("white",new Texture(pixmap));
        skin.add("default",font);

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.up = skin.newDrawable("white",Color.DARK_GRAY);
        tbs.down = skin.newDrawable("white",Color.DARK_GRAY);
        tbs.checked = skin.newDrawable("white",Color.BLUE);
        tbs.over = skin.newDrawable("white",Color.LIGHT_GRAY);
        tbs.font = skin.getFont("default");

        skin.add("default",tbs);

        final TextButton playBtn = new TextButton("PLAY",tbs);
        playBtn.setPosition((SnakeGame.WIDTH/2-playBtn.getWidth()/2),SnakeGame.HEIGHT/2);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(playBtn);


        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playBtn.setText("Start");
                startGame();
            }
        });



    }

    public void startGame()
    {
        gsm.set(new PlayState(gsm));
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
        stage.draw();
        font.draw(sb,"Snake!",100,100);
        sb.end();
    }

    @Override
    public void dispose() {

        skin.dispose();

    }
}
