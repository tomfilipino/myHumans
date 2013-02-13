package game;

import enviroment.Human;
import enviroment.World;
import game.screen.Camera;
import game.screen.GameScreen;

import java.awt.geom.Point2D;


import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor{
	
	//mouse
	
	public static int mouseX;
	public static int mouseY;
	
	
	//keyboard
	
	public final float updateRate = 20; //times per second
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int CENTERVIEW = 4;
	
	
	public static final int humanLEFT = 5;
	public static final int humanRIGHT = 6;
	public static final int humanUP = 7;
	public static final int humanDOWN = 8;
	public static final int humanSearch = 9;
	
	
	public static final int ESCAPE = 6;
	public static final int SPACE = 7;

	
	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];

	
	public void set (int key, boolean down) {
		
		int button = -1;

		if (key == Keys.A) button = LEFT;
		if (key == Keys.D) button = RIGHT;
		if (key == Keys.W) button = DOWN;
		if (key == Keys.S) button = UP;
		if (key == Keys.C) button = CENTERVIEW;
		
		if (key == Keys.UP) button = humanUP;
		if (key == Keys.DOWN) button = humanDOWN;
		if (key == Keys.RIGHT) button = humanRIGHT;
		if (key == Keys.LEFT) button = humanLEFT;
		
		if (key == Keys.SPACE) button = SPACE;
		

//		if (key == Buttons.LEFT) button = LEFT;
//		if (key == Buttons.RIGHT) button = RIGHT;
//		if (key == Buttons.MIDDLE) button = MIDDLE;
//
//		if (key == Keys.ESCAPE || key == Keys.MENU) button = ESCAPE;
//		
//		
		if (button >= 0) {
			buttons[button] = down;
		}			
	}
	
	public void tick () {
		for (int i = 0; i < buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}		
	}
	
	public void releaseAllKeys () {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}
	
	public boolean noAction () {
		for (int i = 0; i < buttons.length; i++) {
			if(buttons[i] == true && i>=5 && i<=8)return false;
		}
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//MOUSE INPUT
	
	@Override
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		Point2D.Float worldCoords = GameScreen.screenPosToWorldCoords(new Point2D.Float(Gdx.input.getX(), Gdx.input.getY()));
		mouseX = Math.round(worldCoords.x);
		mouseY = Math.round(worldCoords.y);
		
		Gdx.app.log("mouse ", "> " + mouseX + "  " + mouseY + " <");
		Gdx.app.log("mouse ", "> " + Gdx.input.getX() + "  " + Gdx.input.getY() + " <");
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
				
		Camera.moveto(new Point2D.Float(Camera.getScreenPosition().x-Gdx.input.getDeltaX()/3, Camera.getScreenPosition().y-Gdx.input.getDeltaY()/3));

        return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
