package dynamics;

import enviroment.World;

import java.awt.geom.Point2D;


import com.badlogic.gdx.Gdx;


public class DYNAMICS {
	
	
	public static void update(){	
		for(int i=0;i<World.humans.size;i++){
			World.humans.get(i).update();
			World.humans.get(i).AI();
		}
	}

	
}
