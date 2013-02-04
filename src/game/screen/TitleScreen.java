package game.screen;

import game.Input;
import enviroment.World;
import game.screen.GameScreen;
import game.screen.Screen;
import graphics.Art;

import java.awt.geom.Point2D;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
//import com.zjp.isoplay.Sound;

public class TitleScreen extends Screen  {

	private int time = 0;
	
	@Override
	public void render () {
		String TITLE = "My Humans";
		spriteBatch.begin();
		//draw(Art.bg, 0, 0);
		drawString(TITLE, 160 - TITLE.length() * 3, 140 - 3);
		spriteBatch.end();
	}
	
	@Override
	public void tick (Input input) {
		time++; //
		if (input.buttons[Input.SPACE] && !input.oldButtons[Input.SPACE]) {
			//Sound.startgame.play();
			World.create();			
			setScreen(new GameScreen());
			//input.releaseAllKeys();
		}
	}
}
