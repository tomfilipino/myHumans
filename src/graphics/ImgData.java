package graphics;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Color;

public class ImgData {
	
	public class Resources{
		public class quartz{
			String imgPath = "data/";  
			Point2D.Float size = new Point2D.Float(0, 0);
			int Nstyles = 2;
			int Nstates = 2;					
		}
		public class tree{
			String imgPath = "ssss";  
			Point2D.Float size = new Point2D.Float(0, 0);
			int Nstyles = 2;
			int Nstates = 2;	
		}
		public class rocks{
			String imgPath = "ssss";  
			Point2D.Float size = new Point2D.Float(0, 0);
			int Nstyles = 2;
			int Nstates = 2;	
		}		
	}
	public class Terrain{
		//code generated
		public class grass{
			Color main = new Color(100,100,100,1);
			int REDfluctuation=10;
			int GREENfluctuation=10;
			int BLUEfluctuation=10;
			float ALPHAfluctuation=0.1f;
		}
		public class sand{
			Color main = new Color(100,100,100,1);
			int REDfluctuation=10;
			int GREENfluctuation=10;
			int BLUEfluctuation=10;
			float ALPHAfluctuation=0.1f;
		}
		public class dirty{
			Color main = new Color(100,100,100,1);
			int REDfluctuation=10;
			int GREENfluctuation=10;
			int BLUEfluctuation=10;
			float ALPHAfluctuation=0.1f;
		}	
		
	}

}
