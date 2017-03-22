package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.oi.Axis;
import org.usfirst.frc.team2854.robot.oi.Button;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Richard Huang
 */
public class Drive extends Command{
	private DriveTrain driveTrain;
	private Axis leftTrigger,rightTrigger,leftAxis;

	public Drive(DriveTrain pDriveTrain,Axis LT,Axis RT,Axis RX){
		leftAxis=RX; // left trigger
		driveTrain=pDriveTrain;
		leftTrigger=LT;
		rightTrigger=RT;
	}

	// Called just before this Command runs the first time
	protected void initialize(){
		System.out.println("Initialized drive command");
		requires(driveTrain);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute(){
		double total=sigmoid(rightTrigger.deadbandGet()-leftTrigger.deadbandGet());
		double lDrive=total,rDrive=total;
		double turn=sigmoid(leftAxis.deadbandGet()/1.5);
		lDrive-=turn;
		rDrive+=turn;
		driveTrain.tankDrive(ensure(lDrive),ensure(rDrive));
	}

	// smooth over driving with sigmoid function
	private double sigmoid(double i){
		return 2/(1+Math.pow(Math.E,-7*Math.pow(i,3)))-1;// 1/(1+e^(-7x^3))
	}

	private double ensure(double value){
		return Math.min(Math.max(value,-1),1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished(){
		return false;
	}

	// Called once after isFinished returns true
	protected void end(){
		driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted(){
		driveTrain.stop();
	}
}