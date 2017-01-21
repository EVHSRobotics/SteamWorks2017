package org.usfirst.frc.team2854.robot;

import com.ctre.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RMap {
	public static CANTalon TALON_0,TALON_1,TALON_2,TALON_3;
	public RMap(){
		TALON_0=new CANTalon(0); 
		TALON_1=new CANTalon(1);
		TALON_2=new CANTalon(2);
		TALON_3=new CANTalon(3);
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}