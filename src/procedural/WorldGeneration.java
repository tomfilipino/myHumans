package procedural;

import enviroment.Cell;
import enviroment.Resource;
import enviroment.World;

public class WorldGeneration {
	
	public static void setNaturalWorld(){		
		float r;
		
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){
				World.cells.add(new Cell(i,j,"grass"));
				World.resources.add(new Resource(i,j,"empty"));
			}
		}
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
 			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){
 				if(World.randomnumber.nextFloat()<0.001f)
 					World.cells.get(Cell.getIndex(i, j)).setType("sand");
 				if(World.randomnumber.nextFloat()<0.001f)
 					World.resources.get(Resource.getIndex(i, j)).setType("tree");
			}
		}
		
		
		
		//procedural spread
		for(int t=0;t<30;t++){
			for(int i=0;i<World.cells.size;i++){
				World.cells.get(i).GenerativeSpread();				
			}
		}
		for(int t=0;t<50;t++){
			for(int i=0;i<World.resources.size;i++){
				World.resources.get(i).GenerativeSpread();				
			}
		}
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){
				if(i*i<=400 && j*j<=400){					
					World.resources.get(Resource.getIndex(i, j)).setType("empty");
				}
			}
		}
		

		
	}
	public static void setTestWorld(){//for development
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){					
					World.cells.add(new Cell(i,j,"grass"));
					World.resources.add(new Resource(i,j,"empty"));
			}
		}
		World.cells.get(Cell.getIndex(World.HEIGHT/2, -World.WIDTH/2)).setType("dirt");
		World.cells.get(Cell.getIndex(World.HEIGHT/2, World.WIDTH/2)).setType("dirt");
		World.cells.get(Cell.getIndex(-World.HEIGHT/2, World.WIDTH/2)).setType("dirt");
		World.cells.get(Cell.getIndex(-World.HEIGHT/2, -World.WIDTH/2)).setType("dirt");
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){		
					if(i*i+j*j<100)
						World.cells.get(Cell.getIndex(i, j)).setType("sand");	
					if(i*i+j*j<500 && i*i+j*j>400)
						World.cells.get(Cell.getIndex(i, j)).setType("sand");
					if(i>20&&i<50 &&j>-2&&j<2)
						World.cells.get(Cell.getIndex(i, j)).setType("dirt");
					if(j>20&&j<50 &&i>-2&&i<2)
						World.cells.get(Cell.getIndex(i, j)).setType("dirt");
			}
		}
		
		
	}


}
