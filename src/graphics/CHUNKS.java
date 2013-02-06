package graphics;

import java.awt.geom.Point2D;

import enviroment.Cell;
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
	public static Pixmap buffer = new Pixmap(bufferSIZE,bufferSIZE,Format.RGBA4444);
	public static Array<chunk> chunkList = new Array<chunk>(0);
	public static Array<Integer> cacheID = new Array<Integer>(0);
	private static Matrix4 projection = Screen.spriteBatch.getProjectionMatrix();	
	
	static class chunk{
		Point2D.Float pos;
		Pixmap data = new Pixmap(chunkSIZE,chunkSIZE,Pixmap.Format.RGBA4444);		
		public chunk(Point2D.Float p, Pixmap pixmap){
			pos = new Point2D.Float(p.x, p.y);
			data = pixmap;
		}
	}
	
	
	


}
