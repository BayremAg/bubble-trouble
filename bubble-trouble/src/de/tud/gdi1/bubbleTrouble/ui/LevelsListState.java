package de.tud.gdi1.bubbleTrouble.ui;

import de.tud.gdi1.bubbleTrouble.model.action.LevelAction;
import de.tud.gdi1.bubbleTrouble.model.highScore.HighScoreList;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
/**
 * @author Jigar Jariwala and Bayrem Agrebi
 *
 */
public class LevelsListState extends BasicGameState {

  private int stateID; 							// Identifier von diesem BasicGameState
  private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
  private final int distance = 100;
  private final int start_Position = 340;
  public static int highScore=0;

  public static String playerName="";
  public static String playerPassword="";
  public static int playerIndex;
  static{
    playerIndex=0;
  }


  public LevelsListState(int sid){
    stateID = sid;
    entityManager = StateBasedEntityManager.getInstance();
    highScore = 0;
  }

 


  @Override
  public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    //Hintergrund laden
    Entity background = new Entity("menu");	// Entitaet fuer Hintergrund
    background.setPosition(new Vector2f(750,350));	// Startposition des Hintergrunds
    if(!BubbleTrouble.debug) {
    	background.addComponent(new ImageRenderComponent(new Image("/assets/menu1.png"))); // Bildkomponente
    }

    // Hintergrund-Entitaet an StateBasedEntityManager uebergeben
    entityManager.addEntity(stateID, background);


    //* Level1-Entitaet*//
    Entity enter_Level1_Entity= new Entity("Level1");
    // Setze Position und Bildkomponente
    //1
    enter_Level1_Entity.setPosition(new Vector2f(190,350));//218,190
    enter_Level1_Entity.setScale(0.28f);
    if(!BubbleTrouble.debug) {
    	enter_Level1_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
    }
    // Erstelle das Ausloese-Event und die zugehoerige Action
    //1
    ANDEvent levelsEvents_l1=new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
    Action to_Level1_Action= new LevelAction(enter_Level1_Entity);
    KeyPressedEvent pressed_1 = new KeyPressedEvent(Input.KEY_1);
    pressed_1.addAction(to_Level1_Action);
    levelsEvents_l1.addAction(to_Level1_Action);
    pressed_1.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    levelsEvents_l1.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    enter_Level1_Entity.addComponent(pressed_1);
    enter_Level1_Entity.addComponent(levelsEvents_l1);
    // Fuege die Entity zum StateBasedEntityManager hinzu
    //1
    entityManager.addEntity(this.stateID,enter_Level1_Entity);


    //* Level2-Entitaet*//
    Entity enter_Level2_Entity= new Entity("Level2");
    // Setze Position und Bildkomponente
    //2
    enter_Level2_Entity.setPosition(new Vector2f(190,450));//218,290
    enter_Level2_Entity.setScale(0.28f);
    if(!BubbleTrouble.debug) {
    	enter_Level2_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
    }
    // Erstelle das Ausloese-Event und die zugehoerige Action
    //2
    ANDEvent levelsEvents_l2=new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
    Action to_Level2_Action= new LevelAction(enter_Level2_Entity);
    levelsEvents_l2.addAction(to_Level2_Action);
    levelsEvents_l2.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    KeyPressedEvent pressed_2 = new KeyPressedEvent(Input.KEY_2);
    pressed_2.addAction(to_Level2_Action);
    pressed_2.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    
    enter_Level2_Entity.addComponent(levelsEvents_l2);
    enter_Level2_Entity.addComponent(pressed_2);
    // Fuege die Entity zum StateBasedEntityManager hinzu
    //2
    entityManager.addEntity(this.stateID,enter_Level2_Entity);


    //* Level3-Entitaet*//
    Entity enter_Level3_Entity= new Entity("Level3");
    // Setze Position und Bildkomponente
    //3
    enter_Level3_Entity.setPosition(new Vector2f(190,550));//218,390
    enter_Level3_Entity.setScale(0.28f);
    if(!BubbleTrouble.debug) {
    	enter_Level3_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
    }
    // Erstelle das Ausloese-Event und die zugehoerige Action
    //3
    ANDEvent levelsEvents_l3=new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
    Action to_Level3_Action= new LevelAction(enter_Level3_Entity);
    levelsEvents_l3.addAction(to_Level3_Action);
    levelsEvents_l3.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    
    KeyPressedEvent pressed_3 = new KeyPressedEvent(Input.KEY_3);
    pressed_3.addAction(to_Level3_Action);
    pressed_3.addAction(new ChangeStateInitAction(BubbleTrouble.GAMEPLAY_STATE));
    
    
    enter_Level3_Entity.addComponent(levelsEvents_l3);
    enter_Level3_Entity.addComponent(pressed_3);
    // Fuege die Entity zum StateBasedEntityManager hinzu
    //3
    entityManager.addEntity(this.stateID,enter_Level3_Entity);

    //*Back-Entitaet*//
    Entity back_Entity= new Entity("back");
    // Setze Position und Bildkomponente
    back_Entity.setPosition(new Vector2f(190,650));//218,490
    back_Entity.setScale(0.28f);
    if(!BubbleTrouble.debug) {
    	back_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
    }
    // Erstelle das Ausloese-Event und die zugehoerige Action
    //3
    ANDEvent levelsEvents_b=new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
    Action back_Action= new ChangeStateInitAction(BubbleTrouble.MAINMENU_STATE);
    levelsEvents_b.addAction(back_Action);
    back_Entity.addComponent(levelsEvents_b);
    // Fuege die Entity zum StateBasedEntityManager hinzu
    //3
    entityManager.addEntity(this.stateID,back_Entity);




    /*BestScores-Entitaet*/
    Entity bestScores_Entity= new Entity("BestScores");
    // Setze Position und Bildkomponente
    bestScores_Entity.setPosition(new Vector2f(560,550));
    bestScores_Entity.setScale(0.28f);
    if(!BubbleTrouble.debug) bestScores_Entity.addComponent(new ImageRenderComponent(new Image("assets/Scores5.png")));
    // Fuege die Entity zum StateBasedEntityManager hinzu
    entityManager.addEntity(this.stateID,bestScores_Entity);



    // Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
    Entity esc_Listener = new Entity("ESC_Listener");
    KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
    esc_pressed.addAction(new ChangeStateAction(BubbleTrouble.MAINMENU_STATE));
    esc_Listener.addComponent(esc_pressed);
    entityManager.addEntity(stateID, esc_Listener);

  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    entityManager.updateEntities(container, game, delta);
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    entityManager.renderEntities(container, game, g);

    int counter = 0;
    int counter1=0;
    g.setColor(Color.darkGray);
    g.drawString("Level 1", 160, start_Position+counter*distance); counter++;
    g.drawString("Level 2", 160, start_Position+counter*distance); counter++;
    g.drawString("Level 3", 160, start_Position+counter*distance); counter++;
    g.drawString("Zur�ck", 160, start_Position+counter*distance); counter++;
    g.drawString("Settings", 1380, 800);
    g.setColor(Color.white);
    g.drawString("Spielername: "+playerName , 1180, 50);
    g.setColor(Color.darkGray);
    g.drawString("BEST SCORES ", 520, 405);

    //last score zugriff von der player
    //nutzung von sein playerIndex als Referenz
    //ich hab es hier die suche gemacht damit in jeder frame actualisierung die such wird wieder hier gemacht,
    // damit wenn er verliert und updates the file und geht zurück zu diesem state, the updated file wird wieder scanned.
    File fileNamesObj=new File("fileNames.txt");
    File filePasswordsObj= new File("filePasswords.txt");
    File fileScoresObj= new File("fileScores.txt");
    if(fileNamesObj.exists()&&filePasswordsObj.exists()&&fileScoresObj.exists()){
      try {
        Scanner fileScoresReader=new Scanner(fileScoresObj);
        for(int i=0;i< playerIndex-1 ;i++){
          fileScoresReader.nextLine();
        }
        String score=fileScoresReader.nextLine();
        g.setColor(Color.white);
        g.drawString("Hoechste erzielte Punktzahl:"+ score , 1180, 70);//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        fileScoresReader.close();
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    //_________________________________________________________________________________________________________
    Path path1= Paths.get("fileNames.txt");
    Path path2= Paths.get("fileScores.txt");
    
    try(Stream<String> stream1=Files.lines(path1);Stream<String> stream2= Files.lines(path2) ){
      String[] names=stream1.toArray(String[]::new);/////////////////////////////////////////////////////////
      String[] scores= stream2.toArray(String[]::new);
      
      //array2 sortierung und gleichzeitig parallel array1 änderung
      HighScoreList highScore = new HighScoreList(names, scores);
      String[][] arrays = highScore.sortTwoParallelArrays(names, scores);
      g.setColor(Color.black);
      for(int i=0;i<arrays[0].length;i++){
        g.drawString((i+1)+". "+arrays[0][i]+":   "+arrays[1][i] , 500, 430+20*counter1); counter1++;
      }

    }
    catch (IOException exc){
      exc.printStackTrace();
    }


  }

  
  @Override
  public int getID() {
    return stateID;
  }
}



