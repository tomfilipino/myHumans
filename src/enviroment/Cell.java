package enviroment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.Art;
import game.Game;
import game.screen.*;

import java.util.Random;
import java.awt.geom.Point2D;




public class Cell extends Entity {
		
	public static int width = 17;
	public static int height = 9;
	public String Type;
	
	public static int styles=20;
	
	
	int TextureType;
	public boolean isOccupied;
	
	public static int getIndex(float x,float y){
		return (int)((World.WIDTH+1)*(World.HEIGHT/2 + Math.round(y))  + (World.WIDTH/2+Math.round(x)));
	}
	
	public Cell(int X, int Y,String type) {		
		pos = new Point2D.Float(X,Y);
		setType(type);
		isOccupied=false;
	}
	public void setType(String newtype){
		Type = newtype;
		Random r = new Random();
		TextureType = r.nextInt(styles);
	}
	public float GenerativeSpread(){
		float prob=0.2f;
		Random r = new Random();
		int[] vizinhos = new int[4];
		
	//	vizinhos[0] = id + World.WIDTH;
	//	vizinhos[1] = id + World.WIDTH-1;
	//	vizinhos[2] = id + World.WIDTH+1;
	//	vizinhos[3] = id + 1;
	//	vizinhos[4] = id - 1;
	//	vizinhos[5] = id - World.WIDTH;
	//	vizinhos[6] = id - World.WIDTH-1;
	//	vizinhos[7] = id - World.WIDTH+1;
		
		vizinhos[0] = getIndex(pos.x,pos.y+1);
		vizinhos[1] = getIndex(pos.x,pos.y-1);
//		vizinhos[0] = index + World.WIDTH;
//		vizinhos[1] = index - World.WIDTH;		
		vizinhos[2] = getIndex(pos.x-1,pos.y);
		vizinhos[3] = getIndex(pos.x+1,pos.y);
		

		for(int i=0;i<4;i++){
			if(vizinhos[i]>=0 && vizinhos[i]<World.cells.size){					
				if(!World.cells.get(vizinhos[i]).Type.contentEquals(Type)){
					if(r.nextFloat()<prob && !Type.contentEquals("grass")){ //spread over grass
						World.cells.get(vizinhos[i]).setType(Type);
					}
				}
			}
		}
		
		
		return prob;
	}

	public Point2D.Float getScreenPos(){
		return new Point2D.Float((pos.x-Cell.width/2) + pos.y*(Cell.width/2) + pos.x*(float)(Cell.width/2-1), -Human.height +2 + (pos.y+Cell.height) - pos.y*(Cell.height/2+1) + pos.x*(Cell.height/2));
	}

	@Override
	public void render() {
		Screen.spriteBatch.draw(Art.getTexture(Type,TextureType),(float)getScreenPos().getX(),(float)getScreenPos().getY());		
	}
	
}

