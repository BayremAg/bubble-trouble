package de.tud.gdi1.bubbleTrouble.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

public class LevelAction implements Action{
	
	public static String selectedLevel;
	private Entity entity;
	
	 
	public LevelAction(Entity entity){
		this.entity = entity;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		this.selectedLevel = this.entity.getID();
	}
	

}
