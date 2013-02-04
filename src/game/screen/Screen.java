package game.screen;

import game.Game;
import game.Input;
import game.screen.Screen;
import graphics.Art;

import java.util.Random;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;



public abstract class Screen {
	
	private final String[] chars = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", ".,!?:;\"'+-=/\\< "};
	protected static Random random = new Random();	
	private Game game;
	public static SpriteBatch spriteBatch;
	public static int LEFT,RIGHT,BOTTOM,TOP,NEAR,FAR;
	
	public void removed () {
		spriteBatch.dispose();
	}
	
	public final void init (Game game) {
		LEFT=0;
		RIGHT=Game.GAME_WIDTH;
		BOTTOM=Game.GAME_HEIGHT;
		TOP=0;
		NEAR=-1;
		FAR=1;
		this.game = game;
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(LEFT,RIGHT,BOTTOM,TOP,NEAR,FAR);
		spriteBatch = new SpriteBatch(100);
		spriteBatch.setProjectionMatrix(projection);
	}
	
	protected void setScreen (Screen screen) {
		game.setScreen(screen);
	}
	
	public void draw (TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if (width < 0) width = -width;
		spriteBatch.draw(region, x, y, width, region.getRegionHeight());
	}

	public void drawString (String string, int x, int y) {
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			for (int ys = 0; ys < chars.length; ys++) {
				int xs = chars[ys].indexOf(ch);
				if (xs >= 0) {
					draw(Art.font[xs][ys + 9], x + i * 6, y);
				}
			}
		}
	}
	
	public abstract void render ();

	public void tick (Input input) {

	}
}
