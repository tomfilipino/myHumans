package game.screen;

import enviroment.World;
import game.Game;
import graphics.Art;
import graphics.CHUNKS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GuideLines{
	
	ShapeRenderer guides;
	
	public static void render(Screen screen){
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 0.2f);
//		Screen.spriteBatch.draw(Art.guidelines,Screen.LEFT,Screen.TOP);
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
		String FPS = "FPS "+ Gdx.graphics.getFramesPerSecond();
		screen.drawString(FPS, Screen.RIGHT - 100 - FPS.length()*3, Screen.BOTTOM - 50);
		screen.drawString("draw calls " + World.DisplayableCells.size, Screen.RIGHT - 100 - FPS.length()*3, Screen.BOTTOM - 40);
		//screen.drawString("CHUNKS " + CHUNKS.chunks.size, Screen.RIGHT - 100 - FPS.length()*3, Screen.BOTTOM - 30);
	}
	
	
}
