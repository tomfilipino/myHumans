package enviroment;

import game.screen.Screen;
import graphics.Art;

import java.awt.geom.Point2D;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import dynamics.Action;
import dynamics.Explore;


public class Human extends Entity {
	
	public static int width = 8;
	public static int height = 16;
	
	public static float speed = 0.3f;
	
	int id;

	String over;
	
	
	int facing;
	int stepState=0;
	public int Nstep=6;
	float stepPos=0;
	float stepSize=0.5f;
	//public boolean displayable;
	public boolean isBlocked;
	
	public boolean foundResource;	
	public boolean hasResource;
	public int extractID;
	public String Collecting;
	public Point2D.Float ResourceTarget = new Point2D.Float();
	public Point2D.Float extractTarget = new Point2D.Float();
	public float loadQuantity;
	
	public Array<Action> toDoList;
	public Array<Point2D.Float> extractionPath;
	

	public Human(Point2D.Float p, Point2D.Float v, Point2D.Float a, int index){
		pos = new Point2D.Float((int)p.x,(int)p.y);
		vel = new Point2D.Float((int)v.x,(int)v.y);
		acc = new Point2D.Float((int)a.x,(int)a.y);
		toDoList = new Array<Action>(0);
		id = index;
		isBlocked=false;
		foundResource=false;
		hasResource=false;
		extractionPath = new Array<Point2D.Float>(0);;
	}

	public void update(){
		pos.setLocation(pos.x+vel.x, pos.y+vel.y);
		vel.setLocation(vel.x+acc.x, vel.y+acc.y);
		acc.setLocation(0, 0);
		if(vel.getX()>0){facing= 0;}
		if(vel.getX()<0){facing= 2;}
		if(vel.getY()>0){facing= 3;}
		if(vel.getY()<0){facing= 1;}	
		World.getDisplayables("humans");
		
		stepPos = stepPos + (float)vel.distance(0, 0);
		if(stepPos>=stepSize){
			stepState++;	
			stepPos=0;
		}
		if(stepState>=Nstep-1){
			stepState=0;
			stepPos=0;
		}
		if((float)vel.distance(0, 0)==0){
			stepState=0;
			stepPos=0;			
		}
		
		
		
		if(World.OutofBounds(pos)){
			pos.setLocation(pos.x-vel.x, pos.y-vel.y);
			isBlocked=true;
			stop();
			return;
		}
		else{
			over = World.cells.get(Cell.getIndex(pos.x , pos.y)).Type;		
			
						
			if(World.cells.get(Cell.getIndex(pos.x, pos.y)).isOccupied){						
				//BLOCK AND FOUND
				isBlocked=true;
				foundResource=true;
				Collecting=World.resources.get(Resource.getIndex(pos.x, pos.y)).Type;
				
				ResourceTarget=new Point2D.Float(pos.x, pos.y);
				extractID = Resource.getIndex(this.ResourceTarget.x, this.ResourceTarget.y);
				//Gdx.app.log("Rpos", "> " + pos.x + " " + pos.y + " <");
				//step back
				//pos = new Point2D.Float(pos.x-vel.x,pos.y-vel.y);
				pos = new Point2D.Float(pos.x-getDirectionOf(vel).x,pos.y-getDirectionOf(vel).y);
				extractTarget=new Point2D.Float(pos.x, pos.y);
				//Gdx.app.log("Epos", "> " + pos.x + " " + pos.y + " <");
				stop();
			}
			else{
				isBlocked=false;
			}			
		}
		
	}
	
	Point2D.Float getDirectionOf(Point2D.Float vector){
		float x,y;
		if(vector.x>0)x=1;
		else if(vector.x<0)
			x=-1;
		else
			x=0;		
		if(vector.y>0)y=1;
		else if(vector.y<0)
			y=-1;
		else
			y=0;
		return new Point2D.Float(x,y);
	}
	
	
	public Point2D.Float getScreenPos(){
		return new Point2D.Float((pos.x-Human.width/2) + pos.y*(Cell.width/2) + pos.x*((float)Cell.width/2-1.5f),  -Human.height +2 + (pos.y-Cell.height/2+1) - pos.y*(Cell.height/2+1) + pos.x*(Cell.height/2)+1);
	}

	@Override
	public void render() {
		Screen.spriteBatch.draw(Art.humanGIF[stepState][facing],(float)getScreenPos().getX(),(float)getScreenPos().getY());		
	}
	
	public void AI(){
		//Gdx.app.log("AI SIZE", "> " + toDoList.size + " <");		
		if(toDoList.size>0){	
			Action exec = toDoList.first();
			exec.getStatus(this);
			if(exec.isDone)
				toDoList.removeIndex(0);
		}
		else{	
			this.toDoList.add(new Explore());
			stop();
		}
		for(int i=0;i<toDoList.size;i++){
			if(toDoList.get(i).isDone){
				toDoList.removeIndex(i);
			}
		}
		toDoList.shrink();
		
	}
	
	public void stop(){
		this.vel = new Point2D.Float(0, 0);
	}

	public void stockResource() {
		World.base.stock.add(loadQuantity,Collecting);
		loadQuantity=0;
	}

	public void extractResource() {		
		loadQuantity = World.resources.get(extractID).extract();		
	}
	
	
}






