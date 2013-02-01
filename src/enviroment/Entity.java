package enviroment;

import game.screen.Screen;

import java.awt.geom.Point2D;
import java.util.Random;
import java.lang.Math;




public abstract class Entity {
	public static int index;
	
	public Point2D.Float pos;	
	public Point2D.Float vel;
	public Point2D.Float acc;
	public String Type;
 
	public boolean isDisplayable;
	
	public void setType(String newtype){
		Type = newtype;
	}
	public boolean isDisplayable(){
		if(getScreenPos().x<Screen.LEFT-20 || getScreenPos().x>=Screen.RIGHT|| getScreenPos().y<Screen.TOP-10 || getScreenPos().y>=Screen.BOTTOM){
			isDisplayable = false;return false;
		}
		else{
			isDisplayable = true;
			return true; 
		}
	}
	public abstract void render();
	public abstract Point2D.Float getScreenPos();
}
