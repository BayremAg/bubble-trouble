package de.tud.gdi1.bubbleTrouble.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

public class BallAction implements Action {

	private Vector2f pos;
    Entity ballAction;
    private int direction;
    
    double velocity;
	double changeY;
	double changeX;
	boolean flag;
	private double levelSpeed;
	int xAxis;
	double gravity;
	int radius;
	int ground_y;
	double friction;

	
	public BallAction(Vector2f positionOfBall, Entity entity, int direction,int typeOfBall, double levelSpeed) {
		this.pos = positionOfBall;
		this.ballAction = entity;
		this.levelSpeed = levelSpeed;
		this.friction = 0.98;
		if(typeOfBall ==1) {
			this.radius = 50;
			
		}
		else if(typeOfBall ==2) {
			this.radius = 25;
			
		}
		else  {
			this.radius = 15;
			
		}
		this.xAxis =(int)positionOfBall.x;
		this.direction = direction;
		this.velocity=0;
		this.changeY=0;
		this.changeX=0;
		this.flag=true;
		this.gravity = 0.01;
	}
	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int arg2, Component arg3) {		
		
		if(flag) {
			if(this.direction == 1){
				changeX = changeX - 0.4;
			}
			if(this.direction == 2){
				changeX = changeX + 0.4;
			}
		
		}
		
		if(!flag){
			if(direction == 1){
				changeX = changeX + 0.4;
			}
			if(direction == 2){
				changeX = changeX - 0.4;
			}
		}
		
		if(this.pos.y + this.radius > gc.getHeight()-100){
			velocity = - velocity*friction;
			
		}
		
		else {
			this.velocity +=gravity;
		}
		
		if(direction == 1){
			if(xAxis + changeX > gc.getWidth()-this.radius){
				flag=true;
			}
		
			if(xAxis + changeX < this.radius){
				flag=false;
			}
		}
		if(direction == 2){
			if(xAxis + changeX > gc.getWidth()-this.radius){
				flag=false;
			}
		
			if(xAxis + changeX < this.radius){
				flag=true;
			}
		}
		pos.x = (int)changeX + xAxis;
		pos.y += velocity + levelSpeed;
		
		ballAction.setPosition(new Vector2f(pos.x, pos.y));
		
		
		
	}
	
	

}
