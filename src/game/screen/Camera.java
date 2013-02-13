package game.screen;

import enviroment.Cell;
import enviroment.World;
import game.Game;

import java.awt.geom.Point2D;


import com.badlogic.gdx.math.Matrix4;



public class Camera {

		public int x, y, width, height;


		public Camera (int width, int height) {
			this.width = width;
			this.height = height;
		}
		
		
		public static void move(String action){
			
					
			
			int displacement=1;
			
			Matrix4 newprojection = Screen.spriteBatch.getProjectionMatrix();	

			if(action == "LEFT"){
				newprojection.translate(displacement, 0, 0);
				Screen.LEFT-=displacement;
				Screen.RIGHT-=displacement;
			}
			if(action == "RIGHT"){
				newprojection.translate(-displacement, 0, 0);
				Screen.LEFT+=displacement;
				Screen.RIGHT+=displacement;
			}			
			if(action == "DOWN"){
				newprojection.translate(0, displacement, 0);
				Screen.TOP-=displacement;
				Screen.BOTTOM-=displacement;
			}
			if(action == "UP"){
				newprojection.translate(0, -displacement, 0);
				Screen.TOP+=displacement;
				Screen.BOTTOM+=displacement;
			}
			Screen.spriteBatch.setProjectionMatrix(newprojection);
			World.getDisplayables("all");
		}
		public static void moveto(Point2D.Float center){
			Matrix4 newprojection = new Matrix4();		
			Screen.LEFT = (int)(center.getX()-Game.GAME_WIDTH/2);
			Screen.RIGHT = (int)center.getX()+Game.GAME_WIDTH/2;
			Screen.TOP = (int)center.getY()-Game.GAME_HEIGHT/2;
			Screen.BOTTOM = (int)center.getY()+Game.GAME_HEIGHT/2;
			newprojection.setToOrtho(Screen.LEFT, Screen.RIGHT, Screen.BOTTOM, Screen.TOP, Screen.NEAR, Screen.FAR);
			Screen.spriteBatch.setProjectionMatrix(newprojection);
			World.getDisplayables("all");
		}
		
		public static Point2D.Float getScreenPosition(){
			return new Point2D.Float(Screen.LEFT + Game.GAME_WIDTH/2, Screen.BOTTOM - Game.GAME_HEIGHT/2);
		}
		public static Point2D.Float getWorldPosition(){
			return GameScreen.screenPosToWorldCoords(new Point2D.Float(getScreenPosition().x/Cell.width + Game.GAME_WIDTH, getScreenPosition().y/Cell.height + Game.GAME_HEIGHT));
		}
}
