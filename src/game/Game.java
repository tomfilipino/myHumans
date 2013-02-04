package game;

import enviroment.World;
import game.screen.GameScreen;
import game.screen.Screen;
import game.screen.TitleScreen;
import graphics.Art;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
//import com.zjp.isoplay.Sound;


public class Game implements ApplicationListener {
	
	public static final int SCREEN_SCALE = 1;
	public static final int GAME_WIDTH = DesktopStarter.windowWIDTH/SCREEN_SCALE;
	public static final int GAME_HEIGHT = DesktopStarter.windowHEIGHT/SCREEN_SCALE;

	private boolean running = false;
	private Screen screen;
	private final Input input = new Input();
	private final boolean started = false;
	private float accum = 0;
	
	@Override
	public void create () {		
		Art.load();
		//Sound.load();
		Gdx.input.setInputProcessor(input);		
		setScreen(new TitleScreen());
		running = true;
	}

	@Override
	public void pause () {
		running = false;
	}

	@Override
	public void resume () {
		running = true;
	}

	public void setScreen (Screen newScreen) {
		if (screen != null) screen.removed();
		screen = newScreen;
		if (screen != null) screen.init(this);
		
		if(screen.getClass().equals(GameScreen.class)){
			GameScreen.setGameView();
		}
			
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		accum += Gdx.graphics.getDeltaTime();
		while (accum > 1.0f / input.updateRate) {
			screen.tick(input);
			input.tick();
			accum -= 1/input.updateRate;
		}
		//Gdx.app.log("fps", "> " + Gdx.graphics.getFramesPerSecond() + " <");
		screen.render();
	}
	
	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void dispose () {
	}
	
}