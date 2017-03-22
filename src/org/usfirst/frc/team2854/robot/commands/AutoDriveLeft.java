package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.oi.Button;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Richard Huang
 */
public class AutoDriveLeft extends Command{
	private DriveTrain driveTrain;
	private Encoder left;
	final int end=300;
	long temp;

	public AutoDriveLeft(DriveTrain pDriveTrain,Encoder a){
		// Leftaxis = LX; // left trigger
		driveTrain=pDriveTrain;
		left=a;
	}

	// Called just before this Command runs the first time
	protected void initialize(){
		left.reset();
		temp=System.nanoTime();
		System.out.println("Initialized drive command");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute(){
		// drive curved
		driveTrain.tankDrive(.31,.5);
		System.out.println("Autonomous Time: "+(System.nanoTime()-temp)/1000000000.0);
		System.out.println("Encoder (eft): "+left.get());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished(){
		if (left.get()>end||Math.abs(System.nanoTime()-temp)>13*Math.pow(10,9)){
			System.out.println("Auto finished");
			return true;
		}else
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