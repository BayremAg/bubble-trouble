package de.tud.gdi1.bubbleTrouble.ui;


import eea.engine.component.Component;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import de.tud.gdi1.bubbleTrouble.model.highScore.HighScoreList;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Jigar Jariwala and Bayrem Agrebi
 * Diese Klasse repraesentiert das Menuefenster, indem ein neues
 * Spiel gestartet werden kann und das gesamte Spiel beendet 
 * werden kann.
 */
public class MainMenuState extends BasicGameState {

	private int stateID; 							// Identifier von diesem BasicGameState
	private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
	
	private final int distance = 100;
    private final int start_Position = 390;//290
    private Sound sound;

    MainMenuState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
    /**
     * Wird vor dem (erstmaligen) Starten dieses State's ausgefuehrt
     */
    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	// Hintergrund laden
    	if(!BubbleTrouble.debug) {
    		Entity background = new Entity("menu");	// Entitaet fuer Hintergrund
        	background.setPosition(new Vector2f(750,350));	// Startposition des Hintergrunds
        	background.addComponent(new ImageRenderComponent(new Image("/assets/menu1.png"))); // Bildkomponente
        	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
        	entityManager.addEntity(stateID, background);
        	
        	Entity about = new Entity("about");
        	about.setPosition(new Vector2f(1482,18));
        	about.addComponent(new ImageRenderComponent(new Image("/assets/about.png"))); 
        	//Erstelle das Ausloese-Event und die zugehoerige Action
    		ANDEvent mainEvents_i= new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
    		KeyPressedEvent i_pressed = new KeyPressedEvent(Input.KEY_I);
    		
    		Action about_Action= new Action() {

				@Override
				public void update(GameContainer gameContainer, StateBasedGame sb, int i, Component component) {
					String message = "   " + " Im Spiel Bubble Trouble spielt ein_eine Spieler_in. Dieser_e Spieler_in steuert dabei einen Spielscharakter\n"
							+ "und versucht die dotzende Bälle mit einem Pfeil zu schießen. Der Spielscharakter kann nur nach rechts und links\n"
							+ "bewegen, um sich nicht von der dotzenden Bälle zu treffen. Die Bällestehen in verschiendenen Höhen und wurden in\n"
							+ "kleineren Bällen geteilt. Das Spiel ist rundenbasiert: Zunächst tätigt der/die Spieler_in den Pfeil-Wurf. Würden\n"
							+ "alle Bälle in eine gewissene Level in einer gewissenen Zeit dürch den geworfenen Pfeilen geschoßen, so wurde die\n"
							+ "Runde gewonnen, ansonsten verliert der/die Spieler_in einen Versuch. Ein Wurf wird durch Space-Taste Eingabe\n"
							+ "gesteuert.\n";
					frameMessagePop(message);
					
				}
    			
    		};
    		
    		i_pressed.addAction(about_Action);
    		mainEvents_i.addAction(about_Action);
    		
    		
    		
    		
    		about.addComponent(mainEvents_i);
    		about.addComponent(i_pressed);
        	entityManager.addEntity(stateID, about);

        	sound = new Sound("assets/Beep.wav");
    		
    	}
    	
    	
		/*Register-Entitaet*/
		Entity register_Entity=new Entity("Register");
		// Setze Position und Bildkomponente
		register_Entity.setPosition(new Vector2f(180,400));//180,300
		register_Entity.setScale(0.28f);
		if(!BubbleTrouble.debug) register_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
		

		//Erstelle das Ausloese-Event und die zugehoerige Action
		ANDEvent mainEvents_r= new ANDEvent(new MouseEnteredEvent(),new MouseClickedEvent());
		KeyPressedEvent r_pressed = new KeyPressedEvent(Input.KEY_R);
		
			Action register_Action= new Action() {
				@Override
				public void update(GameContainer gameContainer, StateBasedGame sb, int i, Component component) {
					
					JPanel panel = new JPanel(null);
					JFrame frame = new JFrame("Registrierung");
					frame.setSize(500,300);
					frame.setLocation(550,300);

					// label,field fÃ¼r name
					JLabel name_label = new JLabel("Neuer Name:  ");
					name_label.setLocation(50,59);
					name_label.setSize(100,100);
					JTextField nameField = new JTextField(10);
					nameField.setLocation(150,100);
					nameField.setSize(150,20);



					// label,field fÃ¼r password
					JLabel password_label = new JLabel("Neues Passwort: ");
					password_label.setLocation(45,79);
					password_label.setSize(100,100);
					JPasswordField passwordField= new JPasswordField(10);
					passwordField.setLocation(150,120);
					passwordField.setSize(150,20);

					// Button and his action
					JButton regButton=new JButton("Registrieren");
					regButton.setLocation(350,120);
					regButton.setSize(120,20);
					regButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							//creating a file for names if it does not exist
							//creating a file for Passwords if it does not exist
							//if any file exists it will not be recreated   :)))))
							File fileNamesObj=new File("fileNames.txt");
							File filePasswordsObj= new File("filePasswords.txt");
							File fileScoresObj= new File("fileScores.txt");
							try {
								if(fileNamesObj.createNewFile())
									System.out.println("File created "+fileNamesObj.getName());
								else
									System.out.println("File of Names already exists");
								if(filePasswordsObj.createNewFile())
									System.out.println("File created "+filePasswordsObj.getName());
								else
									System.out.println("File of Passwords already exists");
								if(fileScoresObj.createNewFile())
									System.out.println("File created "+fileScoresObj.getName());
								else
									System.out.println("File of Scores already exists");
							}
							catch (IOException ex){
								System.out.println("Erooooor");
								ex.printStackTrace();
							}

							if(fileNamesObj.exists()&&filePasswordsObj.exists()&&fileScoresObj.exists()) { //hier all the work parallel with the files so if an error occurs they all stay Parallel with infos
								System.out.println("file names/passwords/Scores exists to write");
								
								 //_________________________________________________________________________________________________________
							    Path path1= Paths.get("fileNames.txt");
							    Path path2= Paths.get("fileScores.txt");
							    							    
							    try(Stream<String> stream1=Files.lines(path1);Stream<String> stream2= Files.lines(path2) ){
							    	String[] names=stream1.toArray(String[]::new);/////////////////////////////////////////////////////////
							    	String[] scores= stream2.toArray(String[]::new);
							      
							    //array2 sortierung und gleichzeitig parallel array1 Ã¤nderung
							      HighScoreList highScore = new HighScoreList(names, scores);
							      
							      String[][] highScoreList = highScore.addHighScore(nameField.getText(), "0");
							      
							      names = highScoreList[0];
							      scores = highScoreList[1];
							   
									FileWriter fileNamesWriter=new FileWriter("fileNames.txt",true);
									FileWriter filePasswordsWriter=new FileWriter("filePasswords.txt",true);
									FileWriter fileScoresWriter=new FileWriter("fileScores.txt",true);
									fileNamesWriter.write(names[names.length-1]+"\n");
									fileNamesWriter.close();
									filePasswordsWriter.write(String.valueOf(passwordField.getPassword())+"\n");
									filePasswordsWriter.close();
									fileScoresWriter.write(scores[scores.length-1]+"\n");
									fileScoresWriter.close();//or not closed so they get actualised
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}

							frame.setVisible(false);
							sb.enterState(BubbleTrouble.MAINMENU_STATE);
						}
					});

					// alle Js in dem Jpanel addieren
					panel.add(name_label);
					panel.add(password_label);
					panel.add(passwordField);
					panel.add(nameField);
					panel.add(regButton);

					panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Registrierung"));
					frame.add(panel);
					sound.play();
					frame.setVisible(true);

				}
			};
			mainEvents_r.addAction(register_Action);
			r_pressed.addAction(register_Action);
		
		
		
		
		register_Entity.addComponent(mainEvents_r);
		register_Entity.addComponent(r_pressed);
		// Fuege die Entity zum StateBasedEntityManager hinzu
		entityManager.addEntity(this.stateID, register_Entity);



		/* Neues Spiel starten-Entitaet */
    	Entity new_Game_Entity = new Entity("Neues Spiel starten");
    	
    	// Setze Position und Bildkomponente
    	new_Game_Entity.setPosition(new Vector2f(180, 500));//180,400
    	new_Game_Entity.setScale(0.28f);
    	if(!BubbleTrouble.debug) new_Game_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));

    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	KeyPressedEvent n_pressed = new KeyPressedEvent(Input.KEY_N);
    	
    		if(BubbleTrouble.debug) {
    			n_pressed.addAction(new ChangeStateAction(BubbleTrouble.LEVELSLISTE_STATE));
    		}
    		
    		else {
    		Action new_Game_Action = new Action() {
    			@Override
    			public void update(GameContainer gc, StateBasedGame sb, int delta,
    							   Component event) {
    				JFrame frame = new JFrame("Login");
    				frame.setSize(500,300);
    				frame.setLocation(550,300);
                    JPanel panel = new JPanel(null);//new GridBagLayout()


    				JLabel label = new JLabel("Name eingeben: ");
    				label.setLocation(50,59);
    				label.setSize(100,100);
    				JTextField textField = new JTextField(10);
    				textField.setLocation(150,100);
    				textField.setSize(150,20);

    				JLabel password_label = new JLabel("Passwort eingeben:");
    				password_label.setLocation(35,79);
    				password_label.setSize(130,100);
    				JPasswordField passwordField= new JPasswordField(10);
    				passwordField.setLocation(150,120);
    				passwordField.setSize(150,20);


    				JButton logButton=new JButton("Login");
    				logButton.setLocation(350,108);
    				logButton.setSize(70,20);
    				logButton.addActionListener(new ActionListener() {
    					@Override
    					public void actionPerformed(ActionEvent e) {
    						//if files they didnt even got created(for ex when once someone registred) u cant launch the game and u stay in main menu.
    						// anderenfalls ifthey exist then all the lines get checked and u enter the levelsListe state.
    						File fileNamesObj=new File("fileNames.txt");
    						File filePasswordsObj= new File("filePasswords.txt");
    						File fileScoresObj= new File("fileScores.txt");
    						boolean test=false;
    						if(fileNamesObj.exists()&&filePasswordsObj.exists()&&fileScoresObj.exists()) { //hier all the work parallel with the files so if an error occurs they all stay Parallel with infos
    							//System.out.println("file names/passwords/scores exists to read");
    							try{
    								Scanner fileNamesReader= new Scanner(fileNamesObj);
    								Scanner filePasswordsReader= new Scanner(filePasswordsObj);
    								Scanner fileScoresReader=new Scanner(fileScoresObj);
    								LevelsListState.playerIndex=0;
    								while(fileNamesReader.hasNextLine() &&  filePasswordsReader.hasNextLine() && fileScoresReader.hasNextLine()) {
    									String name=fileNamesReader.nextLine();
    									String password= filePasswordsReader.nextLine();
    									String score=fileScoresReader.nextLine();
    									LevelsListState.playerIndex=LevelsListState.playerIndex+1;//+++++++++
    									//System.out.println(name);
    									//System.out.println(password);
    									//System.out.println(score);
    									if(name.equals(textField.getText()) && password.equals(String.valueOf(passwordField.getPassword()))) {//files lines parallel check
    										test=true;
    										break;
    									}
    								}
    								fileNamesReader.close();
    								filePasswordsReader.close();
    								fileScoresReader.close();
    							}
    							catch (FileNotFoundException ex){
    								//System.out.println("Erooooor2");
    							}
    							if(test) {
    								//System.out.println("WELCOME: "+textField.getText()+" "+String.valueOf(passwordField.getPassword())+" indx:"+LevelsListState.playerIndex);
    								frame.setVisible(false);
    								sound.play();
    								frameMessagePop("Willkommen : "+textField.getText()+"   ");
    								LevelsListState.playerName=textField.getText();//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    								LevelsListState.playerPassword=String.valueOf(passwordField.getPassword());//++++++++++++++++++++++++++++++++++++++++
    								sb.enterState(BubbleTrouble.LEVELSLISTE_STATE);
    							}
    							else{
    								//System.out.println("Account doesn't exist please Register");//fenster zeigen -auf die andere- mit button to exit.
    								//wenn exit passiert dann beide zusammen geschlossen werden
    								sound.play();
    								frame.setVisible(false);
    								frameMessagePop("Konto existiert nicht, bitte registrieren Sie sich");

    							}
    						}
    						else{ //falls die files existieren nicht
    							//System.out.println("file names/passwords existieren nicht to read");
    							sound.play();
    							frameMessagePop("Konto existiert nicht, bitte registrieren Sie sich");
    							frame.setVisible(false);
    						}



    					}
    				});


    				panel.add(label);
    				panel.add(textField);
    				panel.add(password_label);
    				panel.add(passwordField);
    				panel.add(logButton);

    				panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Login"));
    				frame.add(panel);
                    sound.play();
    				frame.setVisible(true);
    			}


    		};
    		mainEvents.addAction(new_Game_Action);
        	n_pressed.addAction(new_Game_Action);
    }

        	
    	
		
		new_Game_Entity.addComponent(mainEvents);
		new_Game_Entity.addComponent(n_pressed);

    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, new_Game_Entity);


    	/* Beenden-Entitaet */
    	Entity quit_Entity = new Entity("Beenden");
    	
    	// Setze Position und Bildkomponente
    	quit_Entity.setPosition(new Vector2f(180, 600));//180,500
    	quit_Entity.setScale(0.28f);
    	if(!BubbleTrouble.debug) quit_Entity.addComponent(new ImageRenderComponent(new Image("assets/12.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents_q = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action quit_Action = new QuitAction();
    	mainEvents_q.addAction(quit_Action);
    	quit_Entity.addComponent(mainEvents_q);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, quit_Entity);

    }

	private static void frameMessagePop(String message){
		JFrame frame  = new JFrame("");
		JOptionPane.showMessageDialog(frame, message,"Nachricht ",JOptionPane.PLAIN_MESSAGE);
	}

    /**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer container, StateBasedGame game, 
												Graphics g) throws SlickException {
		entityManager.renderEntities(container, game, g);
		
		int counter = 0;
		g.setColor(Color.black);
		g.drawString("Registrierung", 125, start_Position+counter*distance); counter++;
		g.drawString("Neues Spiel starten", 100, start_Position+counter*distance); counter++;
		g.drawString("Beenden", 150, start_Position+counter*distance); counter++;
	}

	@Override
	public int getID() {
		return stateID;
	}
	
}
