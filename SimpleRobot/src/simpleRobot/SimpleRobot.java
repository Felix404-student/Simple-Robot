package simpleRobot;
/**
 * @author Justin Brown
 * This class represents Simple Robots that runs and updates on a separate threads.
 */
import java.lang.Thread;
import java.math.*;
import java.awt.*;

//Extends (inheritance) the functionality of Thread in order to update the Robot's position
public class SimpleRobot extends Thread {
	private int x,y, speedX, speedY, period, amp, Yinit;	//Location of the Robot
	private Color rColor = Color.DARK_GRAY;		//Default Color 
	public static final int ROBOT_SIZE = 15;	//Robot's are 15 pixel circles
	public static final int TIME_DELAY = 30;	//Update is called every 30 milliseconds.
	
	//Constructor for SIMPLE robots, do not move
	public SimpleRobot(int aX, int aY, Color aColor) {
		this.setX(aX);
		this.setY(aY);
		this.setYinit(aY);
		this.setrColor(aColor);
		this.setSpeedX(0);
		this.setSpeedY(0);
		this.setPeriod(0);
		this.setAmp(0);
	}
	
	//Constructor for Left/Right robot, do not move in Y, only in X
	public SimpleRobot(int aX, int aY, int aSpeed) {
		this.setX(aX);
		this.setY(aY);
		this.setYinit(aY);
		this.setrColor(Color.blue);
		this.setSpeedX(aSpeed);
		this.setSpeedY(0);
		this.setPeriod(0);
		this.setAmp(0);
	}
	
	//Constructor for Diagonal robots, move in X and Y
	public SimpleRobot(int aX, int aY, int xSpeed, int ySpeed) {
		this.setX(aX);
		this.setY(aY);
		this.setYinit(aY);
		this.setrColor(Color.orange);
		this.setSpeedX(xSpeed);
		this.setSpeedY(ySpeed);
		this.setPeriod(0);
		this.setAmp(0);
	}
	
	//Constructor for Wavy robot, moves in X and Y to trace SIN wave
	public SimpleRobot(int aX, int aY, int xSpeed, int aPeriod, int anAmp) {
		this.setX(aX);
		this.setY(aY);
		this.setYinit(aY);
		this.setrColor(Color.pink);
		this.setSpeedX(xSpeed);
		this.setSpeedY(0);
		this.setPeriod(aPeriod);
		this.setAmp(anAmp);
	}
	
	// Getters and Setters for all private variables for robots
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int x) {
		this.speedX = x;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int y) {
		this.speedY = y;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int p) {
		this.period = p;
	}
	
	public int getAmp() {
		return amp;
	}

	public void setAmp(int a) {
		this.amp = a;
	}
	
	public int getYinit() {
		return Yinit;
	}

	// should only be used by constructor
	public void setYinit(int y) {
		this.Yinit = y;
	}

	public Color getrColor() {
		return rColor;
	}

	public void setrColor(Color rColor) {
		this.rColor = rColor;
	}
	
	/**
	 * Overrides the method run in Thread. This calls the method update() and sleeps the thread for 30 milliseconds before calling update again.
	 */
	public void run() {
		while(true) {
			//Calls update on each robot
			this.update();
	
			//Sleeps this thread for 30 milliseconds before updating again
			try {
				Thread.sleep(TIME_DELAY);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// Updates the position of the robot based on the robot's type.
	// Moves each simple/left-right/diagonal robot based on speedX and speedY
	// Moves Wavy robots according to speedX, amp, and period
	public void update() {
		
		if (this.getX() + this.getSpeedX() >= RobotThreadSimulator.FRAME_DIM-15 || this.getX() + this.getSpeedX() <= 0) {
			this.setSpeedX(-1 * this.getSpeedX());
		}
		this.setX(this.getX() + this.getSpeedX());
		
		if (this.amp == 0) {
			if (this.getY() + this.getSpeedY() >= RobotThreadSimulator.FRAME_DIM-40 || this.getY() + this.getSpeedY() <= 0) {
				this.setSpeedY(-1 * this.getSpeedY());
			}
			this.setY(this.getY() + this.getSpeedY());
		} else {
			this.setY(this.getYinit() + (int)Math.round(Math.sin(this.getX() * this.getPeriod()) * this.getAmp()));
		}
	}
}
