package os;



/**
 * 
 */
public class Timer {

	private int time;
	
	public Timer() {
		this.time = 0;
	}
	
	public void stroke() {
		this.time++;
	}
	
	public void IOStroke() {
		this.time += Constants.IO_STORKE_TIME;
	}
	
	/**
	 * if interrupt, than timer resets automatically
	 */
	public boolean isInterupt() {
		if (this.time >= Constants.TIMER_LIMIT) {
			this.time = 0;
			return true;
		}
		return false;
	}
}
