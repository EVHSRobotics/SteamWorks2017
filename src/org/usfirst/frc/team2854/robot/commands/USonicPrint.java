package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

public class USonicPrint extends Command{
	public static Ultrasonic uLeft;
	public static Ultrasonic uRight;
	public static DriveTrain driveSystem;
	private static double dD = 1;
	private static double power;//between -1 and 1;
	private static double pScale = 1;//for now
	public USonicPrint(DriveTrain driveSystem, int leftTrig, int leftEcho, int rightTrig, int rightEcho){
		this.driveSystem = driveSystem;
		uLeft = new Ultrasonic(leftTrig, leftEcho);
		uRight = new Ultrasonic(rightTrig, rightEcho);
		AnalogInput.setGlobalSampleRate(62500);
	}
	@Override
	protected void initialize(){
		requires(driveSystem);
	}

	@Override
	protected void execute(){
		power = pFunction((uLeft.getRangeInches() - uRight.getRangeInches()));
//		driveSystem.tankDrive(power*pScale, -power*pScale);
		System.out.println("uLeft Distance:"+uLeft.getRangeInches());
		System.out.println("uRIght Distance0" + uRight.getRangeInches());
	}
	
	@Override
	protected boolean isFinished() {
		return (uLeft.getRangeInches() - uRight.getRangeInches()) > dD;
	}
	
	private double pFunction(double dD){
		double constant = 1;
		return constant * Math.pow(Math.E, dD);
		
	}

}
