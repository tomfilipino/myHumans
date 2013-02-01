package game;

import java.awt.geom.Point2D;
import java.util.Vector;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter {
	
	public static final int PIXEL_ASPECT_RATIO = 1;
	public static final int SIZE = 150;
	public static Point2D.Float SCALE =new Point2D.Float(4, 3);
	
	public static int windowWIDTH=(int)SCALE.x*SIZE;
	public static int windowHEIGHT=(int)SCALE.y*SIZE;
	
	public static void main (String[] args) {
        new LwjglApplication(new Game(), "MY HUMAN", windowWIDTH, windowHEIGHT, false);
	}

}
