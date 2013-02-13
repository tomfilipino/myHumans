package dynamics;

import enviroment.Human;

import java.awt.geom.Point2D;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Explore extends Action {	
	
	public int TRYLENGHT = 10;
	
	Random random = new Random();
	
	public Array<Point2D.Float> path;
	public Point2D.Float active = new Point2D.Float();
	
	public Explore(){
		isDone=false;
		isExecuting=false;
		isWaiting=false;
		isTrying=false;
	}
	
	
	@Override

	
	public void getStatus(Human actor) {
		//Gdx.app.log("EXPLORE", "> " + " <");
		
		if(actor.foundResource){	//RESOURCE FOUND!
			//Gdx.app.log("FOUND", "> " + " <");		
			//actor.stop();
			isDone=true;
			actor.foundResource=false;
			//INSERT NEXT ACTION: EXTRACT
			actor.toDoList.insert(1, new EXTRACT(new Point2D.Float(0, 0),actor.extractTarget));
		}
		else{
			//isDone=true;
			execute(actor);
		}
		
	}

	public void execute(Human actor) {// ADD RANDOM WALK TO ACTION LIST WHILE EXPLORE WAITS
		actor.toDoList.insert(0,new GetTarget(Anywhere(actor),true));

	}
	
	private Point2D.Float Anywhere(Human actor) { 
		//RANDOM WALK
		return new Point2D.Float(actor.pos.x + (random.nextInt(2*TRYLENGHT) - TRYLENGHT), actor.pos.y+(random.nextInt(2*TRYLENGHT) - TRYLENGHT));
	}
}
