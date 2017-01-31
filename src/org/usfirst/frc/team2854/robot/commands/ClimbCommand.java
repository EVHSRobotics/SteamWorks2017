package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.oi.Axis;
import org.usfirst.frc.team2854.robot.subsystems.Climb;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rishi
 */
public class ClimbCommand extends Command{
	private Climb climb;
	private Axis axis1;
	//axis a2 refers to axis(2) of joystick 0
    public ClimbCommand(Climb climbsub, Axis a2){
    	climb = climbsub;
			axis1 = a2;
    }

    protected void initialize(){
    	requires(climb);
    }

    protected void execute(){
    	climb.run(axis1.deadbandGet());
    }

    protected boolean isFinished(){
        return false;
    }
    	protected void end(){
    	climb.stop();
    }

    protected void interrupted(){
    	climb.stop();
    }
}
