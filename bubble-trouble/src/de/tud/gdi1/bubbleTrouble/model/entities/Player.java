package de.tud.gdi1.bubbleTrouble.model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;

public class Player extends Entity{
	
	private Vector2f pos;
	private float speed;
	

	public Player(String entityID) {
		super(entityID);
		pos = new Vector2f(400,548);
		speed=0;
	}
	

}
