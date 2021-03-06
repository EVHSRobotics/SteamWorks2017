package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.oi.Button;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Richard Huang
 */
public class AutoDrive extends Command{
	private DriveTrain driveTrain;
	private Encoder left;
	int finalLeft1,finalRight1;
	long temp;

	public AutoDrive(DriveTrain pDriveTrain,Encoder a){
		driveTrain=pDriveTrain;
		left=a;
		finalLeft1=300;
	}

	// Called just before this Command runs the first time
	protected void initialize(){
		left.reset();
		temp=System.nanoTime();
		System.out.println("Initialized drive command");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute(){
		driveTrain.tankDrive(.3,.3);
		System.out.println((System.nanoTime()-temp)/1000000000);
		System.out.println("Encoder: "+left.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished(){
		if (left.get()>300||Math.abs(System.nanoTime()-temp)>13*Math.pow(10,9)){
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