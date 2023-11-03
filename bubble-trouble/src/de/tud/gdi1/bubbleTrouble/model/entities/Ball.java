package de.tud.gdi1.bubbleTrouble.model.entities;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;


public class Ball extends Entity{
	
	private Vector2f positionOfBall;

	public Ball(String entityID, Vector2f pos) {
		super(entityID);
		this.positionOfBall  = pos;
		
	}
	
	/**public void setPosition(Vector2f positionOfBall) {
		this.positionOfBall = positionOfBall;
	}
	
	public Vector2f getPosition() {
		return this.positionOfBall;
	} */
	

}
