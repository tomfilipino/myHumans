package game.screen;

import java.awt.Point;
import java.awt.geom.Point2D;

import enviroment.Cell;
import enviroment.Resource;
import enviroment.World;
import game.DesktopStarter;
import game.Game;
import graphics.Art;
import graphics.CHUNKS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GuideLines{
	
	ShapeRenderer guides;
	
	private static int minimapSIZE = 32;
	
	public static Pixmap MiniMap= new Pixmap(minimapSIZE,minimapSIZE,Pixmap.Format.RGBA4444);
	public static Pixmap MiniMapMask= new Pixmap(minimapSIZE,minimapSIZE,Pixmap.Format.RGBA4444);
	
	
	public static void render(Screen screen){
		
		
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 0.2f);
//		Screen.spriteBatch.draw(Art.guidelines,Screen.LEFT,Screen.TOP);
//		Screen.spriteBatch.setColor(1f, 1f, 1f, 1f);
		String FPS = "FPS "+ Gdx.graphics.getFramesPerSecond();
		screen.drawString(FPS, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 50);
		screen.drawString("displayable cells " + World.DisplayableCells.size, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 40);
		screen.drawString("CHUNKS " + CHUNKS.chunks.size, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 30);
		//screen.drawString("Camera Target " + Camera.getScreenPosition().x +", "+ Camera.getScreenPosition().y, Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 60);
		//screen.drawString("Camera in World " + Camera.getWorldPosition().x + ", " + Camera.getWorldPosition().y , Screen.RIGHT - 150 - FPS.length()*3, Screen.BOTTOM - 70);
		
		
		
		
		drawMinimap(screen);
	}

	private static void drawMinimap(Screen screen) {	
		
		
		
		Sprite minimapSprite = new Sprite(new Texture(MiniMap));
		Sprite minimapMaskSprite = new Sprite(new Texture(MiniMapMask));
		
		Point2D.Float minimapPos = new Point2D.Float(Camera.getScreenPosition().x - Game.GAME_WIDTH/2 + 10, Camera.getScreenPosition().y+ Game.GAME_HEIGHT/2 - minimapSIZE - 10);
		
		Point2D.Float CameraMinimap = Camera.getWorldPosition();
		
		
		MiniMapMask.setColor(0f,0f,0f,0f);
		MiniMapMask.fill();
		MiniMapMask.setColor(0f,0f,0f,1f);
		//MiniMapMask.drawCircle(2+minimapSIZE/2 + (int)(minimapSIZE*CameraMinimap.x/(World.WIDTH)),-1+minimapSIZE/2 + (int)(minimapSIZE*CameraMinimap.y/(World.HEIGHT)),2);
		MiniMapMask.drawCircle(minimapSIZE/2-1 + (int)CameraMinimap.x*minimapSIZE/World.WIDTH,minimapSIZE/2-1 + (int)CameraMinimap.y*minimapSIZE/World.HEIGHT ,2);

		
		minimapSprite.rotate(45);
		minimapSprite.setPosition(minimapPos.x, minimapPos.y);
		minimapSprite.draw(Screen.spriteBatch);
		minimapMaskSprite.rotate(45);
		minimapMaskSprite.setPosition(minimapPos.x, minimapPos.y);
		minimapMaskSprite.draw(Screen.spriteBatch);
		
		
		//Screen.spriteBatch.draw(new Texture(MiniMap), minimapPos.x,minimapPos.y);
		//Screen.spriteBatch.draw(new Texture(MiniMapMask), minimapPos.x,minimapPos.y);
		 
		

	}
	public static void updateMinimap() {
		
		int id;
		Point MinimapPos;
		
		MiniMap.setColor(Color.rgba8888(0f,0f, 0f, 0f));
		MiniMap.fill();
		
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){
				
				id = Cell.getIndex(i, j);
				MinimapPos = new Point(minimapSIZE*(i+World.WIDTH/2)/World.WIDTH , minimapSIZE*(j+World.HEIGHT/2)/World.HEIGHT);
				
				if(id<World.cells.size){
					if(World.resources.get(id).Type.contentEquals("empty")){
						if(World.cells.get(id).Type.contentEquals("grass"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(0.1f, 1f, 0f, 1f));
						else if(World.cells.get(id).Type.contentEquals("sand"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(1f, 1f, 0f, 1f));
						else if(World.cells.get(id).Type.contentEquals("dirt"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(0f, 0f, 1f, 1f));
					}
					else{
						if(World.resources.get(id).Type.contentEquals("tree"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(1f, 0.1f, 0.1f, 1f));
						else if(World.resources.get(id).Type.contentEquals("quartz"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(0.2f, 0f, 1f, 1f));
						else if(World.resources.get(id).Type.contentEquals("dirt"))
							MiniMap.drawPixel(MinimapPos.x,MinimapPos.y, Color.rgba8888(0f, 0f, 1f, 1f));
					}
				}
			}
		}
				
		
		
//		for(int i=0;i<minimapSIZE;i++){
//			for(int j=0;j<minimapSIZE;j++){
//				//MiniMap.drawPixel(i, j, Color.rgba8888(1f, 0.5f, 1f, 1f));
//				
//				id = Cell.getIndex(World.WIDTH*i/minimapSIZE - World.WIDTH/2, World.HEIGHT*j/minimapSIZE - World.HEIGHT/2);
//
//				if(World.resources.get(id).Type.contentEquals("empty")){
//					if(World.cells.get(id).Type.contentEquals("grass"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(0.1f, 1f, 0f, 1f));
//					else if(World.cells.get(id).Type.contentEquals("sand"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(1f, 1f, 0f, 1f));
//					else if(World.cells.get(id).Type.contentEquals("dirt"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(0f, 0f, 1f, 1f));
//				}
//				else{
//					if(World.resources.get(id).Type.contentEquals("tree"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(1f, 0.1f, 0.1f, 1f));
//					else if(World.resources.get(id).Type.contentEquals("quartz"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(0.2f, 0f, 1f, 1f));
//					else if(World.resources.get(id).Type.contentEquals("dirt"))
//						MiniMap.drawPixel(i, j, Color.rgba8888(0f, 0f, 1f, 1f));
//				}
//
//			}
//		}

	}
	
	
}
