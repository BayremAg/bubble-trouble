package de.tud.gdi1.bubbleTrouble.model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class BowEvent extends Event{
	
	private Vector2f pos;

	public BowEvent(String id,Vector2f pos) {
		super(id);
		this.pos=pos;
	}

	@Override
	protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
		return true;
	}

}
