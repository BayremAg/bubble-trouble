package de.tud.gdi1.bubbleTrouble.model.action;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tud.gdi1.bubbleTrouble.model.NumberOfTrial;
import de.tud.gdi1.bubbleTrouble.model.factory.BallFactory;
import de.tud.gdi1.bubbleTrouble.ui.GameplayState;
import de.tud.gdi1.bubbleTrouble.ui.BubbleTrouble;
import de.tud.gdi1.bubbleTrouble.ui.LevelsListState;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.CollisionEvent;

public class BallHitAction implements Action{
	
	private static NumberOfTrial life = NumberOfTrial.getInstance();
	private double speedOfBall;
	private Vector2f pos;
	public static int localScore=0;
	public static int finalHighScore;
	
	public BallHitAction(Vector2f positionOfBall, double speedOfBall){
		this.pos = positionOfBall;
		this.speedOfBall = speedOfBall;
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		if(CollisionEvent.class.isInstance(event)){
			CollisionEvent colider = (CollisionEvent) event;
			Entity entity = colider.getCollidedEntity();
			
			if(entity.getID().equals("spieler")) {
			    this.updateJframe(gc, sb, delta);
			    StateBasedEntityManager.getInstance().updateEntities(gc, sb, delta);
			}
			
			
			else if (entity.getID().equals("bow")){
				double dummyScoreWithLevel = this.speedOfBall;
				int scoreWithLevel = (int)(dummyScoreWithLevel*100);
				
				
				if(colider.getOwnerEntity().getID() == "redBall") {
									
					BallFactory redBall1 = new BallFactory("redBallM", new Vector2f(pos.x -100,pos.y-10), 1, 2, speedOfBall);
					BallFactory redBall2 = new BallFactory("redBallM", new Vector2f(pos.x +100,pos.y-10), 2, 2, speedOfBall);
					StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
					GameplayState.numberOfBigRedballs--;
					localScore = localScore + scoreWithLevel +  50;
					StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), redBall1.createEntity());
					StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), redBall2.createEntity());
					
				}
				
				if(colider.getOwnerEntity().getID() == "redBallM") {
					
					BallFactory redBall1 = new BallFactory("redBallS", new Vector2f(pos.x -100,pos.y-10), 1, 3, speedOfBall);
					BallFactory redBall2 = new BallFactory("redBallS", new Vector2f(pos.x +100,pos.y-10), 2, 3, speedOfBall);
					StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
					GameplayState.numberOfMediumRedballs--;
					localScore = localScore + scoreWithLevel + 100;
					StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), redBall1.createEntity());
					StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), redBall2.createEntity());
					
				}
				
				if(colider.getOwnerEntity().getID() == "redBallS") {
					StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
					GameplayState.numberOfSmallRedballs--;
					localScore = localScore + scoreWithLevel + 200;
					if(GameplayState.numberOfBigRedballs==0 && GameplayState.numberOfMediumRedballs==0 
							&& GameplayState.numberOfSmallRedballs==0) {
						
						LevelsListState.highScore = LevelsListState.highScore + localScore;
						if(LevelAction.selectedLevel == "Level1") {
							LevelAction.selectedLevel = "Level2";
					    	  
					       }
						else if(LevelAction.selectedLevel== "Level2") {
					    	   LevelAction.selectedLevel = "Level3";
					       }
						else if(LevelAction.selectedLevel == "Level3") {
							   JFrame frame  = new JFrame("");//your final NOT high
							   JOptionPane.showMessageDialog(frame, "Congratulation! "+ "Your High Score is: " + localScore,"Update",JOptionPane.PLAIN_MESSAGE);
					       }
						StateBasedEntityManager.getInstance().clearEntitiesFromState(BubbleTrouble.GAMEPLAY_STATE);
					    GameplayState newLevel = new GameplayState(BubbleTrouble.GAMEPLAY_STATE);
							try {
								newLevel.init(gc, sb);
							} catch (SlickException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
					}
					
					
					
						
					}
					
				
				
			}
			
			
		}
		
	}
	
	 
	public static int getNumberOfTrial() {
		return life.getNumberOfTrial();
	} 
	
	public static void updateJframe(GameContainer gc, StateBasedGame sb, int delta) {
		
		JFrame frame  = new JFrame("");
		
		life.looseLife();
			if(life.getNumberOfTrial() >0) {
				int n =JOptionPane.showOptionDialog(frame, "You have " + life.getNumberOfTrial() + " try left","Update",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null);
				StateBasedEntityManager.getInstance().clearEntitiesFromState(BubbleTrouble.GAMEPLAY_STATE);
				GameplayState refresh = new GameplayState(BubbleTrouble.GAMEPLAY_STATE);
				try {
					refresh.init(gc, sb);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else {
				LevelsListState.highScore = LevelsListState.highScore + localScore;
				finalHighScore=LevelsListState.highScore;

				if(!BubbleTrouble.debug) JOptionPane.showMessageDialog(frame, "Game Over!","Update",JOptionPane.PLAIN_MESSAGE);
				life.setNumberOfTrial(3);
				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), StateBasedEntityManager.getInstance().getEntity(BubbleTrouble.GAMEPLAY_STATE, "spieler"));	
				
			}
			
			
		
	}

}
