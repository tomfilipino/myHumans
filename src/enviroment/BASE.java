package enviroment;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class BASE extends Entity {
	
	STOCK stock;

	public BASE(Point2D.Float position) {
		pos = new Point2D.Float(position.x,position.y);
		stock = new STOCK();
	}

	public static void stock(float loadQuantity, String collecting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Float getScreenPos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
