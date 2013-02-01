package dynamics;

import enviroment.Human;

import java.awt.geom.Point2D;




public abstract class Action {
	
	public boolean isDone;
	public boolean isExecuting;
	boolean isInitiating;
	boolean isWaiting;
	public boolean isTrying;

	public String Type;
	public Point2D.Float Target;
	public abstract void execute(Human actor);
	//public abstract void resume(Human actor);
	public abstract void getStatus(Human actor);

}