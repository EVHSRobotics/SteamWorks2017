package org.usfirst.frc.team2854.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Rishi
 */
public class Climb extends Subsystem{
	private SpeedController f;
	private SpeedController b;
	public Climb(SpeedController front,SpeedController back){
		f = front;
		b = back;
	}
	public void stop(){
		f.set(0);
		b.set(0);
	}
	public void run(double x){
		double yVal = Math.pow(x, 3);
		f.set(yVal);
		b.set(yVal);
	}
    public void initDefaultCommand(){
    	
    }
}
