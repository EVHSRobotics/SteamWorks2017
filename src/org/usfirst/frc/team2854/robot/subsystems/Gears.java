package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.oi.Button;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * @author Rishi Parikh
 */
public class Gears extends Subsystem{
	private Servo servo1, servo2;
	public Gears(Servo S1,Servo S2){
		servo1 = S1;
		servo2 = S2;
		System.out.println("Gears initialized");
	}
	public void stop(){
		servo1.setAngle(45);
		servo2.setAngle(45);
	}
	public void stateOff(){
		servo1.setAngle(135);
		servo2.setAngle(0);
	}
	public void stateOn(){
		servo1.setAngle(45);
		servo2.setAngle(90);
	}
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

