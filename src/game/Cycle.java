package game;

public class Cycle {
	
	private int tick, cycleTicks, ticksPerStep;
	private int steps, stepNumber;
	
	public Cycle(int steps, int ticksPerStep)
	{
		this.steps = steps;
		this.ticksPerStep = ticksPerStep;
		cycleTicks = steps * ticksPerStep;
		tick = 0;
	}
	

	/**
	 * switches stepNumber after fiite number of ticks
	 */
	public void tick() {
		
		tick++;
		stepNumber = tick % cycleTicks / ticksPerStep;
		
	}


	public int getStepNumber() {
		return stepNumber;
	}
	

}
