package game.screen;

import game.Game;
import game.Input;
import graphics.Art;
import graphics.CHUNKS;
import enviroment.Cell;
import enviroment.Human;
import enviroment.World;

import java.awt.geom.Point2D;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;

import dynamics.DYNAMICS;
import dynamics.GetTarget;


public class GameScreen extends Screen {
	
	int time=0;	

	public final static Camera camera = new Camera(Game.GAME_WIDTH, Game.GAME_HEIGHT);

	public boolean mayRespawn = false;
	
	
	public static void setGameView(){
		//Camera.moveto(World.cells.get(Cell.getindex(0, 0)).pos);
		Camera.moveto(new Point2D.Float(0,0));
	}
	
	
	@Override
	public void render() {
		//CHUNKS.RENDERcache();
		spriteBatch.begin();
		//World.render();
		GuideLines.render(this);
		spriteBatch.end();	
		DYNAMICS.update();
	}
	public void tick (Input input) {
		//camera
		
		if (input.buttons[Input.LEFT]){
			Camera.move("LEFT");}
		if (input.buttons[Input.RIGHT]){
			Camera.move("RIGHT");}
		if (input.buttons[Input.UP]){
			Camera.move("UP");}
		if (input.buttons[Input.DOWN]){
			Camera.move("DOWN");}	
		
		if (input.buttons[Input.CENTERVIEW]){
			//Camera.moveto(World.cells.get(Cell.getindex(0, 0)).pos);
			Camera.moveto(new Point2D.Float(0,0));
		}
		
		//human movements (for development) AI to be created for many
		
		float a = Human.speed;
		
//		if (input.buttons[Input.humanDOWN]){
//			World.humans.first().vel=new Point2D.Float(0, -1*a);
//		}
//		if (input.buttons[Input.humanUP]){
//			World.humans.first().vel=new Point2D.Float(0, +1*a);
//		}
//		if (input.buttons[Input.humanLEFT]){
//			World.humans.first().vel=new Point2D.Float(-1*a, 0);
//		}
//		if (input.buttons[Input.humanRIGHT]){
//			World.humans.first().vel=new Point2D.Float(1*a, 0);
//		}
//		if (Gdx.input.isTouched() && World.humans.first().toDoList.size==0){
//			World.humans.first().toDoList.add(new GetTarget(new Point2D.Float(Input.mouseX,Input.mouseY),false));			
//		}
//		if (input.noAction() && World.humans.first().toDoList.size==0){
//			World.humans.first().vel=new Point2D.Float(0,0);
//		}		
	}	
	public static Point2D.Float screenPosToWorldCoords(Point2D.Float screenPos){
		float x,y;
		float a11,a12,a21,a22;
		
		a11 = (float)1/(Cell.width-1);
		a12 = (float)1/(Cell.height-1);
		a21 = (float)1/(Cell.width-1);
		a22 = -(float)1/(Cell.height-1);
		
		x = a11*(Screen.LEFT + screenPos.x/Game.SCREEN_SCALE) + a12*(Screen.TOP + screenPos.y/Game.SCREEN_SCALE);
		y = a21*(Screen.LEFT + screenPos.x/Game.SCREEN_SCALE) + a22*(Screen.TOP + screenPos.y/Game.SCREEN_SCALE);				
		return new Point2D.Float(Math.round(x), Math.round(y));
	}
}
