package de.tud.gdi1.bubbleTrouble.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tud.gdi1.bubbleTrouble.model.action.BallAction;
import de.tud.gdi1.bubbleTrouble.model.action.BallHitAction;
import de.tud.gdi1.bubbleTrouble.model.entities.Ball;
import de.tud.gdi1.bubbleTrouble.model.events.BouncingEvent;
import de.tud.gdi1.bubbleTrouble.ui.GameplayState;
import de.tud.gdi1.bubbleTrouble.ui.BubbleTrouble;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.interfaces.IEntityFactory;

public class BallFactory implements IEntityFactory {

	private Vector2f positionOfBall;
	private String name;
	private Component component;
	private int typeOfBall;
	private int direction;
	private double speedOfBall;
	
	public BallFactory(String name, Vector2f positionOfBall, int direction, int typeOfBall, double speedOfBall) {
		this.positionOfBall=positionOfBall;
		this.name = name;
		this.typeOfBall = typeOfBall;
		this.direction = direction;
		this.speedOfBall = speedOfBall;
		if(typeOfBall == 1) {
			try {
				if(!BubbleTrouble.debug) this.component = new ImageRenderComponent(new Image("assets/redBall.png"));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameplayState.numberOfBigRedballs++;
			
		}
		if(typeOfBall == 2) {
			try {
				if(!BubbleTrouble.debug) this.component = new ImageRenderComponent(new Image("assets/redBallMedium.png"));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameplayState.numberOfMediumRedballs++;
			
		}
		if(typeOfBall == 3) {
			try {
				if(!BubbleTrouble.debug) this.component = new ImageRenderComponent(new Image("assets/redBallSmall.png"));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GameplayState.numberOfSmallRedballs++;
			
		}
	}
	
	
	
	
	@Override
	public Entity createEntity() {
		Entity ball = new Ball(name, positionOfBall);
		ball.setPosition(positionOfBall);
		ball.addComponent(this.component);
		
		Event bouncingEvent = new BouncingEvent(positionOfBall);
		BallAction actionOfBall = new BallAction(positionOfBall, ball, direction, typeOfBall, speedOfBall);
		bouncingEvent.addAction(actionOfBall);
		ball.addComponent(bouncingEvent);
		
		bouncingEvent = new CollisionEvent();
		bouncingEvent.addAction(new BallHitAction(positionOfBall, speedOfBall));
		ball.addComponent(bouncingEvent);
		
		return ball;
		
	}
	
	

}
