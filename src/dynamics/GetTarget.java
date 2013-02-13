package dynamics;

import enviroment.Cell;
import enviroment.Human;
import enviroment.World;

import java.awt.geom.Point2D;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class GetTarget extends Action{
	
	int TRYLENGHT = 10;
	
	float speed=Human.speed;
	Random random = new Random();
	Point2D.Float vectorTo = new Point2D.Float();

	
	public GetTarget(Point2D.Float target, boolean istrying){
		Target = new Point2D.Float(target.x,target.y);
		isDone=false;
		isExecuting=false;
		isWaiting=false;
		isTrying=istrying;
	}
	
	
	public void getStatus(Human actor) {
		

		if(!isExecuting){ 
			execute(actor);
		}
		else{
			if(actor.pos.distance(Target)<=0.1f){//completed!
				actor.pos= new Point2D.Float(Target.x,Target.y);
				isDone=true;
				actor.stop();
				return;
			}	
			if((actor.isBlocked  || (World.OutofBounds(Target) || World.cells.get(Cell.getIndex(Target.x, Target.y)).isOccupied))){//blocked
				
				if(actor.toDoList.size>=2){
					if(actor.toDoList.get(1).isTrying){						
						actor.toDoList.get(1).isDone=true;					
					}
				}

				//Gdx.app.log("REMOVED", "> " + " <");
				isDone=true;			
				
				return;
			}
			if(isWaiting){
				actor.toDoList.insert(0,new GetTarget(Anywhere(actor),true));		
				isWaiting=false;
				isExecuting=false;
				return;
			}
		}
	}
	
	public void execute(Human actor) {
		isExecuting=true;		
		vectorTo = getVectorTo(actor.pos,Target);
		
		//Gdx.app.log("target", "> " + Target.x + " " + Target.y + " <");		

		if(!isTrying){
			if(vectorTo.x==0||vectorTo.y==0){
				actor.toDoList.insert(0,new GetTarget(new Point2D.Float(Target.x,Target.y),true));
			}
			else{
				if(random.nextFloat()>0.5){
					actor.toDoList.insert(0,new GetTarget(new Point2D.Float(Target.x,Target.y),true));
					actor.toDoList.insert(0,new GetTarget(new Point2D.Float(actor.pos.x,Target.y),true));
				}
				else{
					actor.toDoList.insert(0,new GetTarget(new Point2D.Float(Target.x,Target.y),true));
					actor.toDoList.insert(0,new GetTarget(new Point2D.Float(Target.x,actor.pos.y),true));		
				}
				//isDone=true;				
			}	
			isWaiting=true;
		}
		else{//EXPLORING
			
			if(vectorTo.x==0||vectorTo.y==0){
				actor.vel = new Point2D.Float(getDirectionOf(vectorTo).x*speed,getDirectionOf(vectorTo).y*speed);				
			}
			else{
				if(random.nextFloat()>0.5){
					actor.toDoList.insert(1,new GetTarget(new Point2D.Float(Target.x,Target.y),true));
					actor.toDoList.insert(1,new GetTarget(new Point2D.Float(actor.pos.x,Target.y),true));
				}
				else{
					actor.toDoList.insert(1,new GetTarget(new Point2D.Float(Target.x,Target.y),true));
					actor.toDoList.insert(1,new GetTarget(new Point2D.Float(Target.x,actor.pos.y),true));		
				}
				isDone=true;
			}
		}
	}

	Point2D.Float getVectorTo(Point2D.Float pos,Point2D.Float vector){
		return new Point2D.Float(vector.x-pos.x, vector.y-pos.y);
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
	
	void goToAnywhere(Human actor){
		actor.toDoList.insert(0, new GetTarget(Anywhere(actor),true));
	}
	
	public Point2D.Float Anywhere(Human actor) {
		if(random.nextBoolean()){
			return new Point2D.Float(actor.pos.x + (random.nextInt(2*TRYLENGHT) - TRYLENGHT), actor.pos.y+(random.nextInt(2*TRYLENGHT) - TRYLENGHT));
		}
		else{
			return new Point2D.Float(actor.pos.x + (random.nextInt(2*TRYLENGHT) - TRYLENGHT), actor.pos.y+(random.nextInt(2*TRYLENGHT) - TRYLENGHT));
		}
	}
	
	


}
