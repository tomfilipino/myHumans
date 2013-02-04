package graphics;

import game.screen.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

public class GRAPHICS {
	
	private static int i;
	private static int index;
	public static Array<SpriteCache> chunks = new Array<SpriteCache>(0);
	public static Array<Integer> cacheID = new Array<Integer>(0);
	private static Matrix4 projection = Screen.spriteBatch.getProjectionMatrix();	
	
	
	
	public static void ADDcache(TextureRegion T, float x, float y){		
		if(index==0&&i==0){
			BEGINcache();	
		}
		chunks.get(index).add(T,x,y);
		i++;
		if(i==100){
			ENDcache();
			i=0;
			index++;
			BEGINcache();
		}
	}

	public static void BEGINcache(){		
		chunks.add(new SpriteCache());
		chunks.get(index).beginCache();
		chunks.get(index).setProjectionMatrix(projection);
	}
	
	
	public static void ENDcache(){			
		cacheID.add(chunks.get(index).endCache());	
	}
	public static void CLOSEcache(){	
		cacheID.add(chunks.get(index).endCache());
	}
	
	
	public static void CLEARcache() {
		i=0;
		index=0;
		cacheID.clear();
		for(int i=0;i<chunks.size;i++){
			chunks.get(i).clear();
		}
		chunks.clear();		
		projection = Screen.spriteBatch.getProjectionMatrix();			
	}
	public static void RENDERcache() {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		//Gdx.app.log("RENDER INFO", "> " + chunks.size + " " + cacheID.size + " <");

		for(int i=0;i<chunks.size;i++){
			int id = cacheID.get(i);
			chunks.get(i).begin();
			chunks.get(i).draw(id);
			chunks.get(i).end();
		}
	}
	

}
