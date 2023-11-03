package de.tud.gdi1.bubbleTrouble.model.events;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tud.gdi1.bubbleTrouble.model.StopWatch;
import eea.engine.event.Event;

public class TimeEvent extends Event {
	
	private StopWatch stopWatch;
	private long freq;
	private boolean loop;

	public TimeEvent(long freq, boolean loop) {
		super("TimeEvent");
		this.freq = freq;
		this.loop = loop;
		stopWatch = new StopWatch();
		stopWatch.start();
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		if(stopWatch.getElapsedTime() >= freq){
			stopWatch.reset();
			if(loop) stopWatch.start();
			return true;
		} else return false;
	}

}
