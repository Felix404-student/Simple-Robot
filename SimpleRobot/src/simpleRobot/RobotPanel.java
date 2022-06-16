package simpleRobot;
/**
 * @author Justin Brown
 * This class creates a new JPanel that will specifically draw the robots.
 */
import java.awt.*;
import javax.swing.*;

//Extends (inheritance) the functionality of the JPanel Component in order to draw Robots.
public class RobotPanel extends JPanel {
	public static final int DEF_R_SIZE = 8;		//Default number of Robots
	private SimpleRobot[] robots = new SimpleRobot[DEF_R_SIZE];		//Creates the array of robots to be updated and drawn
	
	/**
	 * Initializes elements of the JPanel. This method is to be called before added to a JFrame.
	 * Creates all of the robots and adds them to the robots[] array so they can be iterated over.
	 */
	public void init() {
		//Sets the JPanel's Preferred Size to be the same as the Frame.
		super.setPreferredSize(new Dimension(RobotThreadSimulator.FRAME_DIM,RobotThreadSimulator.FRAME_DIM));

		//2 Simple (static) Robots
		SimpleRobot simple1 = new SimpleRobot(20, 20, Color.green);
		robots[0] = simple1;
		SimpleRobot simple2 = new SimpleRobot(40, 20, Color.red);
		robots[1] = simple2;
		
		//2 Left-Right Robots
		SimpleRobot left = new SimpleRobot(0, 60, 2);
		robots[2] = left;
		SimpleRobot right = new SimpleRobot(RobotThreadSimulator.FRAME_DIM-15, 100, -4);
		robots[3] = right;
		
		//2 Diagonal Robots
		SimpleRobot diag1 = new SimpleRobot(0, 140, 4, 6);
		robots[4] = diag1;
		SimpleRobot diag2 = new SimpleRobot(RobotThreadSimulator.FRAME_DIM-15, 180, -6, 8);
		robots[5] = diag2;
		
		//2 Wavy (SIN tracing) Robots
		SimpleRobot sin1 = new SimpleRobot(0, 270, 4, 135, 32);
		robots[6] = sin1;
		SimpleRobot sin2 = new SimpleRobot(RobotThreadSimulator.FRAME_DIM-15, 360, -4, 256, 80);
		robots[7] = sin2;
		
		// Creates and starts each robot's thread
		for(SimpleRobot r : robots) {
			if(r == null)
				continue;
			r.start();
		}
		
	}
	
	// Overrides JPanel's paintComponent method in order to draw each robot.
	public void paintComponent(Graphics g) {
		// Calling super class' paintComponents first
		super.paintComponent(g);
		// For each robot in the array of robots
		for(SimpleRobot r : robots) {
			if(r == null)
				continue;
			//Sets the drawing color
			g.setColor(r.getrColor());
			//Draws the oval to the JPanel
			g.fillOval(r.getX(),r.getY(),SimpleRobot.ROBOT_SIZE,SimpleRobot.ROBOT_SIZE);
		}
	}
}