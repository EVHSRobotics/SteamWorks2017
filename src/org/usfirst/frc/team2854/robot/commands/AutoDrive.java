package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rishi Paarikh
 */
public class AutoDrive extends Command{
	private DriveTrain driveTrain;
	private Encoder left, right;
	int leftStart, rightStart, finalLeft1, finalRight1;
    public AutoDrive(DriveTrain pDriveTrain, Encoder a, Encoder b){
    	//Leftaxis = LX; // left trigger
    	driveTrain=pDriveTrain;
    	left = a;
    	right = b;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	requires(driveTrain);
    	leftStart = left.get();
    	rightStart = right.get();
    	System.out.println("Initialized drive command");
    	finalLeft1 = 100;
    	finalRight1 = 100;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	if(left.get() - leftStart < finalLeft1){
    		driveTrain.leftDrive(.5);
    	}
    	else{
    		driveTrain.leftDrive(0);
    	}
    	if(right.get() - rightStart < finalRight1){
    		driveTrain.rightDrive(0.5);
    		driveTrain.leftDrive(0.5);
    	}
    	else{
    		driveTrain.rightDrive(0);
    		driveTrain.leftDrive(0);
    	}
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	driveTrain.leftDrive(0);
    	driveTrain.rightDrive(0);
    	driveTrain.stop();    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	driveTrain.stop();
    }
}