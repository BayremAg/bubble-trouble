package de.tud.gdi1.bubbleTrouble.model;

public class GameplayLog {
	
	private static GameplayLog log = new GameplayLog();
	private long timeLimit;
	private StopWatch timer;
	
	public GameplayLog(){
		this.timer = new StopWatch();
		this.setTimeLimit(0);
	}

	
	public StopWatch getTimer(){
		return this.timer;
	}
	
	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public static GameplayLog getInstance(){
		return log;
	}
}
