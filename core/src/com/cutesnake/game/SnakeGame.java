package com.cutesnake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cutesnake.game.states.GameStateManager;
import com.cutesnake.game.states.MenuState;
import com.cutesnake.game.states.PlayState;

import sun.font.TrueTypeFont;

public class SnakeGame extends ApplicationAdapter {
	public static final int WIDTH = 768;
	public static final int HEIGHT = 1280;
	public static final int TILE = 64;
	public static final String TITLE = "Snake";
	public static Texture texture;

	private GameStateManager gsm;
	private SpriteBatch batch;


	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0.297f, 0.528f, 0.176f, 1);
		texture = new Texture("snake-graphics.png");
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
