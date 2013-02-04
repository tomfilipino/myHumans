package enviroment;

import game.screen.Screen;
import graphics.Art;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resource extends Entity{

	
	public String Type;
	
	public static int QUARTZstyles=4;
	public static int QUARTZstates=3;
	public static int TREEstyles=4;
	public static int TREEstates=3;
	float quantity;
	int TextureType;
	int state;
	

		
	public static int getIndex(float x,float y){
		return (int)((World.WIDTH+1)*(World.HEIGHT/2 + Math.round(y))  + (World.WIDTH/2+Math.round(x)));
	}
	
	public Resource(int X, int Y,String type) {		
		pos = new Point2D.Float(X,Y);
		setType(type);
	}
	public void setType(String newtype){
		Type = newtype;		
		Random r = new Random();
		
		quantity = 1;

		if(!newtype.contentEquals("empty"))
		{
			World.cells.get(Cell.getIndex(pos.x, pos.y)).isOccupied=true;
			TextureType = r.nextInt(getStyles(Type));
			state = 2;//initial state is 0
		}
		else{
			World.cells.get(Cell.getIndex(pos.x, pos.y)).isOccupied=false;
			quantity=0;
		}
	}
	public void GenerativeSpread(){
		float prob=0.1f;
		Random r = new Random();
		int[] vizinhos = new int[4];
		
		vizinhos[0] = getIndex(pos.x,pos.y+1);
		vizinhos[1] = getIndex(pos.x,pos.y-1);	
		vizinhos[2] = getIndex(pos.x-1,pos.y);
		vizinhos[3] = getIndex(pos.x+1,pos.y);

		for(int i=0;i<4;i++){
			if(vizinhos[i]>=0 && vizinhos[i]<World.resources.size){					
				if(!World.resources.get(vizinhos[i]).Type.contentEquals(Type)){
					if(r.nextFloat()<prob && !Type.contentEquals("empty") && !World.cells.get(Cell.getIndex(World.resources.get(vizinhos[i]).pos.x, World.resources.get(vizinhos[i]).pos.x)).isOccupied){
						World.resources.get(vizinhos[i]).setType(Type);
					}
				}
			}
		}
	}

	public Point2D.Float getScreenPos(){		
		if(Type.contentEquals("quartz"))
			return new Point2D.Float((pos.x-Resource.getSize(Type).x/2) + pos.y*(Cell.width/2) + pos.x*(float)(Cell.width/2-1), -Human.height +2 + (pos.y+Cell.height/2 -1) - pos.y*(Cell.height/2+1) + pos.x*(Cell.height/2));
		if(Type.contentEquals("tree"))
			return new Point2D.Float((pos.x-Resource.getSize(Type).x/2+0.5f) + pos.y*(Cell.width/2) + pos.x*(float)(Cell.width/2-1),  -Human.height +2 + (pos.y - Resource.getSize(Type).y/2 - Cell.height -1) - pos.y*(Cell.height/2+1) + pos.x*(Cell.height/2));
		return new Point2D.Float(0, 0);
	}
	
	
	public TextureRegion getTexture() {
		//Gdx.app.log("depth", "> " + "inside!" + " <");
		if(!Type.contentEquals("empty") && Type.contentEquals("quartz")){
			return Art.quartz[state][TextureType];
			//Screen.spriteBatch.draw(Art.quartz[state][TextureType],(float)getScreenPos().getX(),(float)getScreenPos().getY());
		}
		if(!Type.contentEquals("empty") && Type.contentEquals("tree")){
			return Art.tree[state][TextureType];
			//Screen.spriteBatch.draw(Art.tree[state][TextureType],(float)getScreenPos().getX(),(float)getScreenPos().getY());
		}
		return null;
	}
	
	public int getStyles(String type){
		if(type.contentEquals("quartz"))
			return 4;
		if(type.contentEquals("tree"))
			return 2;
		return 1;
	}
	public int getStates(String type){
		if(type.contentEquals("quartz"))
			return 3;
		if(type.contentEquals("tree"))
			return 3;	
		return 1;
	}
	public static Point2D.Float getSize(String type){
		if(type.contentEquals("quartz"))
			return new Point2D.Float(20, 12);
		if(type.contentEquals("tree"))
			return new Point2D.Float(40, 50);	
		return new Point2D.Float(0,0);						
	}


	public float extract() {
		quantity-=0.5f;
		Gdx.app.log("QUANTITU", "> " + quantity +  " <");
		if(quantity<=0f){
			this.setType("empty");
			return 0f;
		}
		return 0.1f;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	
}

