package graphics;


import enviroment.Cell;
import enviroment.Resource;

import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;

public class Art {
	
	
	public static TextureRegion[][] font;
	public static TextureRegion[][] humanGIF; //facing and step
	public static TextureRegion[][] quartz; //style and state
	public static TextureRegion[][] tree;
	public static TextureRegion[] grass;
	public static Pixmap[] pgrass;
	public static TextureRegion[] sand;
	public static TextureRegion[] dirt;
	public static TextureRegion[][] cells;
	
	//development
	public static TextureRegion guidelines;
	

	
	//GENERATION OF TEXTURE FOR CELLS
	public static void GenerateTextures(){
		grass = new TextureRegion[Cell.styles];
		pgrass = new Pixmap[Cell.styles];
		
		sand = new TextureRegion[Cell.styles];
		dirt = new TextureRegion[Cell.styles];
		
		cells = new TextureRegion[3][Cell.styles];
				
		Random random = new Random();
		Pixmap pixmap = new Pixmap(32,32,Pixmap.Format.RGBA4444);
		pixmap.setColor(Color.rgba8888(0, 0, 0, 0));
		pixmap.fill();
		for(int m=0;m<Cell.styles;m++){				
			for(int i=0;i<Cell.width;i++){
				for(int j=0;j<Cell.height;j++){
					if(i>=Cell.width/2 -2*j && i<=Cell.width/2+2*j && j<i/2+Cell.height/2 +1 && j<-(i-1)/2+3*Cell.height/2 -1){
						pixmap.drawPixel(i,j,Color.rgba8888(0.2f, 0.7f + 0.3f*random.nextFloat(), 0.2f, 1f));						
					}
				}
			}
			pgrass[m] = pixmap;
			grass[m] = new TextureRegion(new Texture(pixmap),Cell.width,Cell.height);
			pixmap.fill();
		}
		
		for(int m=0;m<Cell.styles;m++){				
			for(int i=0;i<Cell.width;i++){
				for(int j=0;j<Cell.height;j++){
					if(i>=Cell.width/2 -2*j && i<=Cell.width/2+2*j && j<i/2+Cell.height/2 +1 && j<-(i-1)/2+3*Cell.height/2 -1){
						pixmap.drawPixel(i,j,Color.rgba8888(1f, 0.9f + 0.1f*random.nextFloat(), 0.3f + 0.25f*random.nextFloat(), 1f));
					}
				}
			}
			sand[m] = new TextureRegion(new Texture(pixmap),Cell.width,Cell.height);
			pixmap.fill();
		}
		
		for(int m=0;m<Cell.styles;m++){				
			for(int i=0;i<Cell.width;i++){
				for(int j=0;j<Cell.height;j++){
					if(i>=Cell.width/2 -2*j && i<=Cell.width/2+2*j && j<i/2+Cell.height/2 +1 && j<-(i-1)/2+3*Cell.height/2 -1){
						pixmap.drawPixel(i,j,Color.rgba8888(0.6f + 0.25f*random.nextFloat(), 0.4f + 0.2f*random.nextFloat(), 0.1f + 0.25f*random.nextFloat(), 1f));
					}
				}
			}
			dirt[m] = new TextureRegion(new Texture(pixmap),Cell.width,Cell.height);	
			pixmap.setColor(Color.rgba8888(0, 0, 0, 0));
			pixmap.fill();
		}
		pixmap.dispose();
		
		

		for(int j=0;j<3;j++){
			for(int i=0;i<Cell.styles;i++){
				if(j==0){
					cells[j][i] = grass[i];
				}
				if(j==1){
					cells[j][i] = sand[i];
				}
				if(j==2){
					cells[j][i] = dirt[i];
				}
			}
		}
		
		
	}
	
	public static void load () {
		font = split("data/guys.png", 6, 6);
		humanGIF = split("data/human.png",8,16);	
		quartz = split("data/quartz20x12_6464.png",20,12);
		tree = split("data/tree_40x50.png",40,50);
		
		guidelines = load("data/guidelines3.png",1024,1024);
		
		GenerateTextures(); //procedural
		
		//Object root = new JsonReader().parse("data/imgData.json");
	}
	
	public static TextureRegion getTexture(String type,int style){		
//		if(type.contentEquals("grass")){
//			return grass[style];					
//		}
//		if(type.contentEquals("sand")){
//			return sand[style];
//		}
//		if(type.contentEquals("dirt")){
//			return dirt[style];
//		}
//		else{
//			return grass[style]; // implement error texture
//		}
		if(type.contentEquals("grass")){
			return cells[0][style];					
		}
		if(type.contentEquals("sand")){
			return cells[1][style];
		}
		if(type.contentEquals("dirt")){
			return cells[2][style];
		}
		else{
			return grass[style]; // implement error texture
		}
	}
	
	private static TextureRegion[][] split (String name, int width, int height) {
		return split(name, width, height, false);
	}

	private static TextureRegion[][] split (String name, int width, int height, boolean flipX) {
		Texture texture = new Texture(Gdx.files.internal(name));
		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		TextureRegion[][] res = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				res[x][y] = new TextureRegion(texture, x * width, y * height, width, height);
				res[x][y].flip(flipX, true);
			}
		}
		return res;
	}
	
	public static TextureRegion load (String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		region.flip(false, true);
		return region;
	}
	
	

}
