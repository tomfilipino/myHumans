package enviroment;
import graphics.Art;
import graphics.CHUNKS;

import java.awt.geom.Point2D;

import java.util.Comparator;
import java.util.Random;

import procedural.WorldGeneration;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteCache;

//import dynamics.Explore;


public class World {
	
	public static final int LAYERS = 2;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	private static final int Nhumans = 20;
	
	public static BASE base;
	
	//population
	public static Array<Human> humans;
	public static Array<Human> DisplayableHumans;
	
	
	//Environment
	public static Array<Cell> cells; //CELL SPACE
	public static Array<Cell> DisplayableCells;
	
	public static Array<Resource> resources; //CELL SPACE
	public static Array<Resource> DisplayableResources;
	
	//render
	public static Array<Entity> depthQueue;
	public static SpriteCache terrain = new SpriteCache(); 
	
	public static Random randomnumber = new Random();	

	
	//METHODS
	
	//CREATE WORLD
	
	public static void create () {
		float r;
	
		humans = new Array<Human>(0);
		cells = new Array<Cell>(0);
		resources = new Array<Resource>(0);
		
		base = new BASE(new Point2D.Float(0,0));
		
		for(int i=0;i<Nhumans;i++){
			humans.add(new Human(new Point2D.Float(0,0),new Point2D.Float(0, 0),new Point2D.Float(0, 0),humans.size-1));
		}


		//WorldGeneration.setTestWorld();
		WorldGeneration.setNaturalWorld();
		
		
		cells.shrink();
		
		DisplayableCells = new Array<Cell>(0);
		DisplayableHumans = new Array<Human>(0);
		DisplayableResources = new Array<Resource>(0);
		depthQueue = new Array<Entity>(0);
		
		for(int i=0;i<cells.size;i++){
			if(cells.get(i).isDisplayable())
				DisplayableCells.add(cells.get(i));
		}		
		for(int i=0;i<humans.size;i++){
			if(humans.get(i).isDisplayable())
				DisplayableHumans.add(humans.get(i));
		}
		for(int i=0;i<resources.size;i++){
			if(resources.get(i).isDisplayable())
				DisplayableResources.add(resources.get(i));
		}
				
	}
	
	
	//RENDER WORLD
	public static void render () {	
				
		//MAIN BACKGROUND				
		for(int i=0;i<DisplayableCells.size;i++){			
			DisplayableCells.get(i).render();
		}
		
		//GRAPHICS.RENDERcache();


		
		//Gdx.app.log("dspsize", "> " + DisplayableCells.size + " <");
		
		//DEPTH QUEUE: HUMANS AND RESOURCES		
		renderDepthQueue();

	}
	
	static class byDepth implements Comparator<Entity> {

		@Override
		public int compare(Entity arg0, Entity arg1) {	
			float d0 = -arg0.pos.x + arg0.pos.y;
			float d1 = -arg1.pos.x + arg1.pos.y;
			if(d0 > d1){
				return -1;
			}
			if(d0 < d1){
				return 1;
			}
			if(d0==d1){
				return 0;
			}
			else
				return 0;
		}
	}
	
	static class byDiagonal implements Comparator<Entity> {

		@Override
		public int compare(Entity arg0, Entity arg1) {	
			float d0 = -arg0.pos.x + arg0.pos.y;
			float d1 = -arg1.pos.x + arg1.pos.y;
			float r0 = arg0.pos.x + arg0.pos.y;
			float r1 = arg1.pos.x + arg1.pos.y;
			if(d0 > d1){
				return -1;
			}
			if(d0 < d1){
				return 1;
			}
			if(d0==d1){
				if(r0>r1)
					return -1;
				if(r0<r1)
					return 1;
				if(r0==r1)
					return 0;			
				return 0;
			}
			else
				return 0;
		}
	}
	
	
	private static void renderDepthQueue(){
		depthQueue.clear();
		for(int i=0;i<DisplayableResources.size;i++){
			depthQueue.add(DisplayableResources.get(i));
		}
		for(int i=0;i<DisplayableHumans.size;i++){
			depthQueue.add(DisplayableHumans.get(i));
		}
		//depthQueue.sort(new byDepth());	
		depthQueue.sort(new byDiagonal());	
		
		
		
		
		
		for(int i=0;i<depthQueue.size;i++){
			depthQueue.get(i).render();
		}
		
	}

	public static void getDisplayables(String entity){
		if(entity.contentEquals("all")){
			DisplayableCells.clear();
			DisplayableHumans.clear();
			DisplayableResources.clear();
			for(int i=0;i<cells.size;i++){
				if(cells.get(i).isDisplayable())
					DisplayableCells.add(cells.get(i));
			}
			for(int i=0;i<humans.size;i++){
				if(humans.get(i).isDisplayable())
					DisplayableHumans.add(humans.get(i));
			}
			for(int i=0;i<resources.size;i++){
				if(resources.get(i).isDisplayable()&&!resources.get(i).Type.contentEquals("empty"))
					DisplayableResources.add(resources.get(i));
			}
		}
		
		
		//each one
		else{
			if(entity.contentEquals("humans")){
				DisplayableHumans.clear();
				for(int i=0;i<humans.size;i++){
					if(humans.get(i).isDisplayable())
						DisplayableHumans.add(humans.get(i));
				}
			}
			if(entity.contentEquals("cells"));
			{
				DisplayableCells.clear();
				for(int i=0;i<cells.size;i++){
					if(cells.get(i).isDisplayable())
						DisplayableCells.add(cells.get(i));
				}				
			}
		}

		
		CHUNKS.CLEARcache();
		for(int i=0;i<DisplayableCells.size;i++){			
			CHUNKS.ADDcache(Art.getTexture(DisplayableCells.get(i).Type, DisplayableCells.get(i).TextureType), DisplayableCells.get(i).getScreenPos().x,DisplayableCells.get(i).getScreenPos().y);
		}
//		for(int i=0;i<DisplayableResources.size;i++){			
//			CHUNKS.ADDcache(DisplayableResources.get(i).getTexture(), DisplayableResources.get(i).getScreenPos().x,DisplayableResources.get(i).getScreenPos().y);			
//		}
		CHUNKS.CLOSEcache();

		
	}
	
	public static boolean OutofBounds(Point2D.Float v){
		if(v.x<-WIDTH/2 || v.x>WIDTH/2 || v.y>HEIGHT/2 || v.y<-HEIGHT/2){
			return true;
		}
		return false;
	}
	
}
		
	