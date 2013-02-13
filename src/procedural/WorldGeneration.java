package procedural;

import enviroment.Cell;
import enviroment.Resource;
import enviroment.World;

public class WorldGeneration {
	
	public static void setNaturalWorld(){		
			float r;
			
			for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
				for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){	
					r=World.randomnumber.nextFloat();
					if(r<0.0000)
						World.cells.add(new Cell(i,j,"sand"));					
					else if(r<=0.999)
						World.cells.add(new Cell(i,j,"grass"));
					else{
						World.cells.add(new Cell(i,j,"dirt"));					
					}
					r=World.randomnumber.nextFloat();
					if(r<0.001){
						if(!World.cells.get(Cell.getIndex(i, j)).isOccupied)
							World.resources.add(new Resource(i,j,"quartz"));
					}
					else if (r<0.002){
						if(!World.cells.get(Cell.getIndex(i, j)).isOccupied)
							World.resources.add(new Resource(i,j,"tree"));
					}
					else{
						if(!World.cells.get(Cell.getIndex(i, j)).isOccupied)
							World.resources.add(new Resource(i,j,"empty"));
					}
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
		}
	public static void setTestWorld(){//for development
		for(int j=-World.HEIGHT/2;j<=World.HEIGHT/2;j++){
			for(int i=-World.WIDTH/2;i<=World.WIDTH/2;i++){	
					World.cells.add(new Cell(i,j,"dirt"));
					World.resources.add(new Resource(i,j,"empty"));
			}
		}
	}


}
