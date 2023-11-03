package de.tud.gdi1.bubbleTrouble.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveUpAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.LoopEvent;

public class PlayerAction implements Action {

	private Vector2f pos;
    public  Input in;
    private Component component =null;
    private Entity playerAction;
    float positionX;
    private Sound sound;
    
    public PlayerAction(Vector2f positionOfPlayer, GameContainer gc, Entity player){
    	this.pos = positionOfPlayer;
    	this.in =gc.getInput();
    	this.playerAction = player;
    }
    
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2, Component arg3) {
		
		
		//Picture size is 42 * 99, that's why the last center will be 22 on left and 778 on right
		if(in.isKeyPressed(in.KEY_LEFT)) {
			in.enableKeyRepeat();
			
			
			moveLeft(pos.x);
			try {
				component = new ImageRenderComponent(new Image("assets/spielerLeft.png"));
				// Bild laden und zuweisen
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else if(in.isKeyPressed(in.KEY_RIGHT)) {
			in.enableKeyRepeat();
			moveRight(pos.x);
			try {
				component = new ImageRenderComponent(new Image("assets/spielerRight.png"));
				// Bild laden und zuweisen
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
		}
		
		else if(in.isKeyPressed(in.KEY_SPACE)) {
			in.disableKeyRepeat();
			try {
				sound = new Sound("assets/Attack.wav");
				sound.play();
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pos.x = pos.x;
			
		try {
				component = new ImageRenderComponent(new Image("assets/spielerBack.png"));
				// Bild laden und zuweisen
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
		
			float newPosY = pos.y-200;
			
			Entity bow  = new Entity("bow");
			bow.setPosition(new Vector2f(pos.x, newPosY));
			
			try {
				// Bild laden und zuweisen
				bow.addComponent(new ImageRenderComponent(new Image("assets/Bow.png")));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
			
			// Bow geht nach oben
			LoopEvent loop = new LoopEvent();
	    	loop.addAction(new MoveUpAction(0.2f));
	    	bow.addComponent(loop);
			
			
	    	StateBasedEntityManager.getInstance().addEntity(sbg.getCurrentStateID(), bow);
			
			
		} 
		
		playerAction.addComponent(component);
		//playerAction.setPosition(new Vector2f(pos.x, pos.y));
		
		
		
	}
	
	public void moveLeft(float x) {
		if(pos.x > 42)
		  pos.x -= 20;
		
	}
	
	public void moveRight(float x) {
		if(pos.x < 1458)
		  pos.x += 20;
	}
	
	
	

}
