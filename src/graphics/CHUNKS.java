package graphics;

import java.awt.geom.Point2D;

import enviroment.Cell;
import enviroment.World;
import game.screen.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

public class CHUNKS {
	
	private static int i;
	private static int index;
	private static int chunkSIZE=256;
	public static int CellsPerSideChunk = (int)(chunkSIZE/Cell.width);  
	public static Array<Chunk> chunkList = new Array<Chunk>(0);
	public static Array<Integer> cacheID = new Array<Integer>(0);
	private static Matrix4 projection = Screen.spriteBatch.getProjectionMatrix();	
	
	static class Chunk{
		Point2D.Float pos;
		Pixmap data;		
		public Chunk(Point2D.Float p, Pixmap pixmap){
			pos = new Point2D.Float(p.x, p.y);
			data = pixmap;
		}
	}
		
		
	public static void load(){
		
		Point2D.Float varPos = new Point2D.Float();
		
		int inChunkCount=0;
		
		index=0;
		chunkList.add(new Chunk(varPos,new Pixmap(chunkSIZE,chunkSIZE,Pixmap.Format.RGBA4444)));
		for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){
			for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){

				if(inChunkCount==0){//set anchor point to chunk
					varPos.setLocation(World.cells.get(Cell.getIndex(i, j)).getScreenPos().x, World.cells.get(Cell.getIndex(i, j)).getScreenPos().y  );
				}
				
				
				chunkList.get(index).data.drawPixmap(Art.Pgrass[World.cells.get(Cell.getIndex(i, j)).TextureType], (int)(World.cells.get(Cell.getIndex(i, j)).getScreenPos().x - varPos.x), (int)(World.cells.get(Cell.getIndex(i, j)).getScreenPos().y - varPos.y));
				inChunkCount++;
				if(inChunkCount==CellsPerSideChunk){
					index++;
					chunkList.add(new Chunk(varPos,new Pixmap(chunkSIZE,chunkSIZE,Pixmap.Format.RGBA4444)));
					inChunkCount=0;
				}				
			}
			
		}
		
	}
	
	
	


}
