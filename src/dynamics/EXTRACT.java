package dynamics;

import enviroment.Human;
import enviroment.Resource;
import enviroment.World;

import java.awt.geom.Point2D;
import java.util.Random;

import com.badlogic.gdx.Gdx;




public class EXTRACT extends Action {

	boolean hasResource;
	
	Point2D.Float extractPlace;
	Point2D.Float base;
	Point2D.Float active;
	
	public EXTRACT(Point2D.Float b, Point2D.Float r){
		isDone=false;
		isExecuting=false;
		isWaiting=false;
		extractPlace = r;
		base = b;
		hasResource=true;
		active = new Point2D.Float();
		isTrying=false;
	}
	

	public void getStatus(Human actor) {
	
		
		if(isDone(actor)){
			isDone=true;
			actor.foundResource=false;
			return;
		}	
		
		if(atBase(actor)){
			actor.stockResource();
			active.setLocation(extractPlace);
		}
		else if(atExtraction(actor)){
			actor.extractResource();
			active.setLocation(base);
		}
		
		execute(actor);
	}
	
	public void execute(Human actor) {
		//Gdx.app.log("extract", "> " + " <");
		//call subAction
		actor.toDoList.insert(0,new GetTarget(active,false));

	}
	
	
	boolean atBase(Human actor){
		if(actor.pos.distance(base)<=1){
			return true;
		}
		return false;
	}
	boolean atExtraction(Human actor){
		if(actor.pos.distance(extractPlace)<=1){
			return true;
		}
		return false;
	}
	
	boolean isDone(Human actor){
		if(World.resources.get(actor.extractID).Type.contentEquals("empty") && atExtraction(actor) && actor.loadQuantity==0){//back exploring from where resource was found
			return true;
		}
		return false;
	}

//		if(atBase(actor)){//completed
//			actor.pos = new Point2D.Float(base.x,base.y);
//			actor.stockResource();
//			active.setLocation(extractPlace);
//			actor.stop();
//			if(World.resources.get(Resource.getIndex(actor.ResourceTarget.x,actor.ResourceTarget.y)).Type.contentEquals("empty")){
//				isDone=true;
//				actor.foundResource=false;
//				//actor.stop();
//			}
//			actor.toDoList.insert(0,new GetTarget(active,false));
//			return;
//		}
//		if(atExtraction(actor)){//completed
//			actor.extractResource();
//			active.setLocation(base);
//			actor.stop();
//			if(World.resources.get(Resource.getIndex(actor.ResourceTarget.x,actor.ResourceTarget.y)).Type.contentEquals("empty")){
//				isDone=true;
//				actor.foundResource=false;
//				//actor.stop();
//			}
//			actor.toDoList.insert(0,new GetTarget(active,false));
//			return;
//		}
//	}

	

	
	


}
