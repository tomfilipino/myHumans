package game.screen;

import game.Art;
import game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GuideLines{
	
	ShapeRenderer guides;
	
	public static void render(Screen screen){
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 0.2f);
//		Screen.spriteBatch.draw(Art.guidelines,Screen.LEFT,Screen.TOP);
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
		String FPS = "FPS "+ Gdx.graphics.getFramesPerSecond();
		screen.drawString(FPS, Screen.RIGHT - 50 - FPS.length()*3, Screen.BOTTOM - 50);
	}
	
	
}
