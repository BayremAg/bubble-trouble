package de.tud.gdi1.bubbleTrouble.model.factory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tud.gdi1.bubbleTrouble.model.action.PlayerAction;
import de.tud.gdi1.bubbleTrouble.model.entities.Player;
import de.tud.gdi1.bubbleTrouble.ui.BubbleTrouble;
import eea.engine.action.basicactions.MoveLeftAction;
import eea.engine.action.basicactions.MoveRightAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.MovementDoesNotCollideEvent;
import eea.engine.interfaces.IEntityFactory;

public class PlayerFactory implements IEntityFactory {
	
	
	private Vector2f pos;
	private GameContainer gc;
	private float speed;
	private Input in;
	private Image imageOfPlayer;
	private Component component;
	private String name;
	
	
	public PlayerFactory(Vector2f pos, GameContainer gc, float speed, String name) {
		this.pos = pos;
		this.gc = gc;
		this.speed =speed;
		this.name = name;
		this.in = gc.getInput();
		try {
			if(!BubbleTrouble.debug) this.imageOfPlayer = new Image("assets/spielerBack.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!BubbleTrouble.debug) this.component = new ImageRenderComponent(this.imageOfPlayer);
	}

	@Override
	public Entity createEntity() {
		Entity player = new Player (name);
		player.setPosition(pos);
		
		player.addComponent(component);
		
		
		// player moves left
    	Event playerEvent = new ANDEvent(new KeyDownEvent(Input.KEY_LEFT),
    			new MovementDoesNotCollideEvent(speed * 10, new MoveLeftAction(speed)));
		playerEvent.addAction(new PlayerAction(pos, gc, player));
		player.addComponent(playerEvent);
		
		// player moves right
		playerEvent = new ANDEvent(new KeyDownEvent(Input.KEY_RIGHT),
    			new MovementDoesNotCollideEvent(speed*10, new MoveRightAction(speed)));
		playerEvent.addAction(new PlayerAction(pos, gc, player));
		player.addComponent(playerEvent);
		
		
		// player stays ideal
		playerEvent = new ANDEvent(new KeyDownEvent(Input.KEY_SPACE), 
				new MovementDoesNotCollideEvent(speed*10, new MoveRightAction(speed)));
		PlayerAction playerAction = new PlayerAction(pos, gc, player);
				
		playerEvent.addAction(playerAction);
		player.addComponent(playerEvent);		
		
		return player;
	}
	
	

}
