package de.tud.gdi1.bubbleTrouble.model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class BouncingEvent extends Event {
	
	private Vector2f pos;

	public BouncingEvent(Vector2f pos) {
		super("BouncingBallEvent");
		this.pos = pos;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {

		return true;
	}

}
