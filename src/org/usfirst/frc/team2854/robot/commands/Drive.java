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
	private Axis leftTrigger,rightTrigger,rightAxis;
    public Drive(DriveTrain pDriveTrain,Axis LT,Axis RT, Axis RX){
    	rightAxis = RX; // left trigger
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
    	double total = sigmoid(leftTrigger.deadbandGet()-rightTrigger.deadbandGet());
    	double lDrive=total,rDrive=total;
    	double turn = sigmoid(rightAxis.deadbandGet());
    	lDrive -= turn;
    	rDrive += turn;
    	driveTrain.tankDrive(lDrive,rDrive);
    }

    //smooth over driving with sigmoid function
    private double sigmoid(double i){
    	return 2/(1+Math.pow(Math.E,-7*Math.pow(i,3)))-1;
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