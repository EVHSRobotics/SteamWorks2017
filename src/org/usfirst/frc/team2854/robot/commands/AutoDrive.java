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
	int finalLeft1, finalRight1;
    public AutoDrive(DriveTrain pDriveTrain, Encoder a, Encoder b){
    	//Leftaxis = LX; // left trigger
    	driveTrain=pDriveTrain;
    	left = a;
    	right = b;
    	finalLeft1=500;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	left.reset();
    	right.reset();
    	System.out.println("Initialized drive command");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	while(Math.abs(left.get())< finalLeft1){
    		driveTrain.tankDrive(.2,.2);
    	}
    		driveTrain.stop();
    	System.out.println("Encoder: "+left.get());
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