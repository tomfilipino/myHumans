package dynamics;

import enviroment.Cell;
import enviroment.Human;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import com.badlogic.gdx.utils.Array;


public class GetTargetThrough extends Action {
	
	int STEP;
	int direction;
	boolean toEND;
	Array<Point2D.Float> Path;
	
	

	public GetTargetThrough(Point2D.Float target, Array<Float> extractionPath, Human actor) {
		isDone=false;
		isExecuting=false;
		isWaiting=false;
		toEND=false;
		//Path = new Array<Point2D.Float>(0);;
		Path = extractionPath;
		for(int i=0;i<Path.size;i++){
			if(Path.get(i).equals(actor.pos)){
				STEP=i;
			}
		}
	}

	public void execute(Human actor) {
		isExecuting=true;
		
		if(toEND==true)
			direction=1;
		else
			direction=-1;

		actor.toDoList.add(new GetTarget(Path.get(STEP),false));
		
		isExecuting=false;
		
		if(STEP==Path.size||STEP==0){
			isDone=true;			
		}

	}

	@Override
	public void getStatus(Human actor) {
		
		STEP+=direction;


	}

}
