package de.tud.gdi1.bubbleTrouble.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tud.gdi1.bubbleTrouble.model.GameplayLog;
import de.tud.gdi1.bubbleTrouble.model.action.BallHitAction;
import de.tud.gdi1.bubbleTrouble.model.action.LevelAction;
import de.tud.gdi1.bubbleTrouble.model.events.EntityDestroyedEvent;
import de.tud.gdi1.bubbleTrouble.model.factory.BackgroundFactory;
import de.tud.gdi1.bubbleTrouble.model.factory.BallFactory;
import de.tud.gdi1.bubbleTrouble.model.factory.PlayerFactory;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jigar Jariwala and Bayrem Agrebi
 *
 * Diese Klasse repraesentiert das Spielfenster, indem ein Wassertropfen
 * erscheint und nach unten faellt.
 */
public class GameplayState extends BasicGameState {

	private int stateID; 							// Identifier dieses BasicGameState
	private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
	private static final int spielerX = 750; // DisplayMode.X/2 to center spieler position X
	private static final int spielerY = 548; // Displaymode.Y - 100- 52 to center spieler position Y
	public static int numberOfSmallRedballs;
	public static int numberOfBigRedballs;
	public static int numberOfMediumRedballs;
	private double speedOfBall = 0;
	private long time;
	private long timeLimit;
	
   public GameplayState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
       numberOfSmallRedballs=0;
       numberOfMediumRedballs=0;
       numberOfBigRedballs=0;
       BallHitAction.localScore = 0;
       GameplayLog.getInstance().getTimer().reset();
       GameplayLog.getInstance().setTimeLimit(10000); //setting up Level Time 10 milliseconds
       this.timeLimit = GameplayLog.getInstance().getTimeLimit();
       time = (timeLimit - GameplayLog.getInstance().getTimer().getElapsedTime())/1000;
    }
    
    /**
     * Wird vor dem (erstmaligen) Starten dieses States ausgefuehrt
     */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
          	
		    GameplayLog.getInstance().getTimer().start();
		
		    	if(BubbleTrouble.debug) {
		    		LevelAction.selectedLevel = "Level1";
		    	}
		    
		    	if(LevelAction.selectedLevel == "Level1") {
		    		BackgroundFactory background = new BackgroundFactory("assets/background_1.png", false, new Vector2f(750,300));    	    	
	            	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
	            	StateBasedEntityManager.getInstance().addEntity(stateID, background.createEntity());
	            	BallFactory redBall = new BallFactory("redBallM", new Vector2f(70,200), 1, 2, speedOfBall);
	            	entityManager.addEntity(stateID, redBall.createEntity());
	            	
	            }
	            
	            else if(LevelAction.selectedLevel == "Level2") {
	            	BackgroundFactory background = new BackgroundFactory("assets/background_2.png", false, new Vector2f(750,300));    	    	
	            	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
	            	StateBasedEntityManager.getInstance().addEntity(stateID, background.createEntity());
	            	BallFactory redBall = new BallFactory("redBall", new Vector2f(70,200),1, 1, speedOfBall);
	            	BallFactory redBall2 = new BallFactory("redBallM", new Vector2f(1400,200),2, 2, speedOfBall);
	            	entityManager.addEntity(stateID, redBall.createEntity());
	            	entityManager.addEntity(stateID, redBall2.createEntity());
	            	
	            }
	            else if(LevelAction.selectedLevel == "Level3") {
	            	BackgroundFactory background = new BackgroundFactory("assets/background_3.png", false, new Vector2f(750,300));    	    	
	            	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
	            	StateBasedEntityManager.getInstance().addEntity(stateID, background.createEntity());
	            	BallFactory redBall = new BallFactory("redBallM", new Vector2f(70,300),1, 2, speedOfBall);
	            	BallFactory redBall2 = new BallFactory("redBallM", new Vector2f(1400,200),2, 2, speedOfBall);
	            	BallFactory redBall3 = new BallFactory("redBallS", new Vector2f(1200,100),1, 3, speedOfBall);
	            	entityManager.addEntity(stateID, redBall.createEntity());
	            	entityManager.addEntity(stateID, redBall2.createEntity());
	            	entityManager.addEntity(stateID, redBall3.createEntity());
	            	
	            }
	            BackgroundFactory downBackground = new BackgroundFactory("assets/DownPart.png", false, new Vector2f(750,650));
	            StateBasedEntityManager.getInstance().addEntity(stateID, downBackground.createEntity());
		    
	                     
        if(checkPlayerPosition(new Vector2f(spielerX, spielerY))) {
        	PlayerFactory spieler = new PlayerFactory(new Vector2f(spielerX, spielerY), container, 0.1f, "spieler");
      	    entityManager.addEntity(stateID, spieler.createEntity());
        }
        else {
        	PlayerFactory spieler = new PlayerFactory(new Vector2f(750, 548), container, 0.1f, "spieler");
      	    entityManager.addEntity(stateID, spieler.createEntity());
        }
            
      	   
      	   
      	   
      	   
      	  Entity playerDestroyEntity = new Entity("playerDestroy");
      	  Event playerDestroyEvent = new EntityDestroyedEvent("spieler");    	    	   
      	  playerDestroyEvent.addAction(new ChangeStateAction(BubbleTrouble.LEVELSLISTE_STATE)); 
      	  playerDestroyEntity.addComponent(playerDestroyEvent);
      	  entityManager.addEntity(stateID, playerDestroyEntity);
      	  
      	 
    	// Bei Druecken der ESC-Taste zurueck ins Hauptmenue wechseln
    	Entity esc_Listener = new Entity("ESC_Listener");
    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
    	esc_pressed.addAction(new ChangeStateAction(BubbleTrouble. LEVELSLISTE_STATE));
    	esc_Listener.addComponent(esc_pressed);    	
    	entityManager.addEntity(stateID, esc_Listener);
    	
    }

	public static boolean checkPlayerPosition(Vector2f position) {
		if(position.getX()>23 && position.getX()<1477 && position.getY()==548)
			return true;
		else return false;
	}
  
	/**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException { 
    	if(time==0 || BallHitAction.getNumberOfTrial()==0) {
    		BallHitAction.updateJframe(container, game, delta);
    	}
    	entityManager.updateEntities(container, game, delta);
    	
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		entityManager.renderEntities(container, game, g);
		
		time = (timeLimit - GameplayLog.getInstance().getTimer().getElapsedTime())/1000;

		g.drawRect(container.getWidth()/2-5, 620, 90, 20);
		g.drawRect(container.getWidth()/2-80, 650, 220, 25);
		g.drawRect(1170,605,280,85);
		g.drawRect(235, 650, 85, 25);
		g.drawRect(390, 610, 50, 20);
		g.drawRect(155, 610, 50, 20);
		g.setColor(Color.gray);
		g.fillRect(container.getWidth()/2-80, 650, 220, 25);
		g.fillRect(container.getWidth()/2-5, 620, 90, 20);
		g.fillRect(155, 610, 50, 20);
		g.fillRect(390, 610, 50, 20);
		g.fillRect(235, 650, 85, 25);
		g.fillRect(1170,605,280,85);
		g.setColor(Color.white);
		g.drawString("Zeit: " + (time), container.getWidth()/2, 620);
		g.drawString("Linksbewegung:", 20, 610);
		g.drawString("Rechtsbewegung:", 250, 610);
		g.drawString("Shoot:", 170, 653);
		g.drawString("Space", 245, 653);
		g.drawString("<-", 165, 613);
		g.drawString("->", 405, 613);
		g.drawString("Anzahl des Versuchs: " + BallHitAction.getNumberOfTrial(), container.getWidth()/2-75, 652);
		g.drawString("Aktuelle Score: "+ (BallHitAction.localScore+LevelsListState.highScore), 1180, 650);
		g.drawString("Bis Letzte Level Score: "+LevelsListState.highScore, 1180, 630);
		g.drawString("Spielername: "+LevelsListState.playerName , 1180, 610);//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//updating the score of the player while playing(only if the new score is bigger than the saved one)
		//nutzung von sein playerIndex als Referenz
		File fileNamesObj=new File("fileNames.txt");
		File filePasswordsObj= new File("filePasswords.txt");
		File fileScoresObj= new File("fileScores.txt");
		if(fileNamesObj.exists()&&filePasswordsObj.exists()&&fileScoresObj.exists()){
			try {
				Scanner fileScoresReader=new Scanner(fileScoresObj);
				//FileWriter fileScoresWriter=new FileWriter("fileScores.txt",true);
				for(int i=0;i< LevelsListState.playerIndex-1 ;i++){
					fileScoresReader.nextLine();
				}
				String oldHighestScore=fileScoresReader.nextLine();// old score gespeichert
				if( (BallHitAction.localScore+LevelsListState.highScore) > Integer.parseInt(oldHighestScore) ){
					setNewScore(LevelsListState.playerIndex,BallHitAction.localScore+LevelsListState.highScore+"");
				}

				fileScoresReader.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}


	}


	private static void setNewScore(int lineNumber, String data) throws IOException {
		Path path = Paths.get("fileScores.txt");
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines, StandardCharsets.UTF_8);
	}
	
	

	@Override
	public int getID() {
		return stateID;
	}
}
