package de.tud.gdi1.bubbleTrouble.model;

public class NumberOfTrial {

	private static NumberOfTrial trial = new NumberOfTrial();
	private int numberOfTrial;
	
	
	public NumberOfTrial() {
		this.numberOfTrial = 3;
	}
	public static NumberOfTrial getInstance(){
		return trial;
	}
	
	public int getNumberOfTrial() {
		return numberOfTrial;
	}
	
	public void setNumberOfTrial(int numberOfTrial) {
		this.numberOfTrial = numberOfTrial;
	}
	
	public void looseLife() {
	    trial.setNumberOfTrial(trial.getNumberOfTrial() - 1);
	}
}
