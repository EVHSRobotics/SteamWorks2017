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
	private Axis leftTrigger,rightTrigger;
	private Button bt;
	private boolean back;
    public Drive(DriveTrain pDriveTrain,Axis LT,Axis RT,Button pbt){
    	driveTrain=pDriveTrain;
    	leftTrigger=LT;
    	rightTrigger=RT;
    	bt=pbt;
    	back=false;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	System.out.println("Initialized drive command");
    	requires(driveTrain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	driveTrain.tankDrive(leftTrigger.deadbandGet(),rightTrigger.deadbandGet());
    	if(bt.get())
    		back=!back;
    	if(back==true)
    		driveTrain.tankDrive(-leftTrigger.deadbandGet(),-rightTrigger.deadbandGet());
    	else
    		driveTrain.tankDrive(leftTrigger.deadbandGet(),rightTrigger.deadbandGet());
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