package game.screen;

import enviroment.Cell;
import enviroment.World;
import game.Game;
import graphics.Art;
import graphics.CHUNKS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GuideLines{
	
	ShapeRenderer guides;
	
	public static Pixmap MiniMap= new Pixmap(128,128,Pixmap.Format.RGBA4444);
	
	
	public static void render(Screen screen){
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 0.2f);
//		Screen.spriteBatch.draw(Art.guidelines,Screen.LEFT,Screen.TOP);
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
		String FPS = "FPS "+ Gdx.graphics.getFramesPerSecond();
		screen.drawString(FPS, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 50);
		screen.drawString("displayable cells " + World.DisplayableCells.size, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 40);
		screen.drawString("CHUNKS " + CHUNKS.chunks.size, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 30);
		drawMinimap();
	}

	private static void drawMinimap() {
		


	}
	public static void updateMinimap() {
		
		for(int i=0;i<128;i++){
			for(int j=0;j<128;j++){
				if(World.cells.get(Cell.getIndex(-World.WIDTH*(128-2*i)/256, -World.HEIGHT*(128-2*j)/256)).Type.contentEquals("grass"))
						MiniMap.drawPixel(i, j, Color.rgba4444(1f, 1f, 0f, 1f));
				else{
					MiniMap.drawPixel(i, j, Color.rgba4444(0f, 0f, 0f, 1f));
				}
			}
		}

	}
	
	
}
