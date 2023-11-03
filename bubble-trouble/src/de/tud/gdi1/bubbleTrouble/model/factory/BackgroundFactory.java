package de.tud.gdi1.bubbleTrouble.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tud.gdi1.bubbleTrouble.ui.BubbleTrouble;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class BackgroundFactory implements IEntityFactory {
	private final String file;
	private final boolean debug;
	private final Vector2f pos;
	private Music music;
	
	public BackgroundFactory(String file, boolean debug, Vector2f pos){
		this.file = file;
		this.debug = debug;
		this.pos = pos;
	}

	@Override
	public Entity createEntity() {
		Entity background = new Entity("background");
		background.setPosition(pos);
		background.setScale(1.3f);
		try {
			if(!debug)
				if(!BubbleTrouble.debug) background.addComponent(new ImageRenderComponent(new Image(file)));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			music = new Music("assets/GameSound.wav");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	     music.setVolume(0.01f);
		 music.loop();
    	
		return background;
	}

}
