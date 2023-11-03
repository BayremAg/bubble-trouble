package de.tud.gdi1.bubbleTrouble.model.highScore;


public class HighScoreList {
	String[] nameOfPlayers;
	String[] scoreOfPlayers;
	private static final HighScoreList highscoreList = new HighScoreList(new String[0], new String[0]);
	
	
	
	public HighScoreList(String[] nameOfPlayers, String[] scoreOfPlayers) {
		this.nameOfPlayers = nameOfPlayers;
		this.scoreOfPlayers = scoreOfPlayers;
	}
	
	public  String[][] sortTwoParallelArrays(String[] nameOfPlayer, String[] scoreOfPlayer){
	    boolean isSorted=false;
	   String[][] arrays = new String[2][10];
	    while (!isSorted){
	      isSorted=true;
	      for(int i=0; i<scoreOfPlayer.length-1;i++){
	        if(Integer.parseInt(scoreOfPlayer[i])>=Integer.parseInt(scoreOfPlayer[i+1]))
	          continue;
	        isSorted=false;
	        int k=Integer.parseInt(scoreOfPlayer[i]);
	        String st=nameOfPlayer[i];
	        scoreOfPlayer[i]=scoreOfPlayer[i+1];
	        nameOfPlayer[i]=nameOfPlayer[i+1];
	        scoreOfPlayer[i+1]=k + "";
	        nameOfPlayer[i+1]=st;
	      }
	      
	    }
	    if(nameOfPlayer.length>9) {
	    	 for(int i = 0; i<10; i++) {
	 	    	arrays[0][i] = nameOfPlayer[i];
	 	    	arrays[1][i] = scoreOfPlayer[i];
	 	    }
	    return arrays;
	    }
	    else {
	    	String[][] newArrays = new String[2][];
	    	newArrays[0] = nameOfPlayer;
 	    	newArrays[1] = scoreOfPlayer;
 	    	 return newArrays;
	    }

	}
	
	public String[][] addHighScore(String player, String score){
		String[][] arrays = new String[2][];
		String[] newName = new String[nameOfPlayers.length+1];
		String[] newScore = new String[scoreOfPlayers.length+1];
		
		for(int i = 0; i< nameOfPlayers.length; i++) {
			newName[i] = this.nameOfPlayers[i];
			newScore[i] = this.scoreOfPlayers[i];
		}
		newName[nameOfPlayers.length]= player;
		newScore[scoreOfPlayers.length] = score;
		
		this.nameOfPlayers = newName;
		this.scoreOfPlayers = newScore;
		arrays[0] = newName;
		arrays[1] = newScore;
				
		return arrays;
	}
	
	public String[][] resetHighscore() {
		 String[][] reset=new String[2][];
		 nameOfPlayers =new String[0];
		 scoreOfPlayers =new String[0];
		 
		 reset[0] = nameOfPlayers;
		 reset[1] = scoreOfPlayers;
		return reset;
	}
	
	public static HighScoreList getInstance() {
		return highscoreList;
	}
	
	public int getHighscoreCount() {
		String[][] arr = sortTwoParallelArrays(nameOfPlayers, scoreOfPlayers);
		return arr[1].length;
	}
	
	public String[] getHighscoresNames(){
		return nameOfPlayers;

	}
	public String[] getHighscoresScores(){
		return scoreOfPlayers;
	}

	public String[] getSortedHighscoresNames(){
		String[][] arr = sortTwoParallelArrays(nameOfPlayers, scoreOfPlayers);
		return arr[0];
	}
	public String[] getSortedHighscoresScores(){
		String[][] arr = sortTwoParallelArrays(nameOfPlayers, scoreOfPlayers);
		return arr[1];
	}
	
}
