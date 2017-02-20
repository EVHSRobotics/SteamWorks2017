package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.oi.Axis;
import org.usfirst.frc.team2854.robot.oi.Button;
import org.usfirst.frc.team2854.robot.subsystems.Climb;
import org.usfirst.frc.team2854.robot.subsystems.Gears;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rishi Parikh
 */
public class GearCommand extends Command{
	private Gears gear;
	private Button button1;
	Boolean isPressed;
	//axis a2 refers to axis(2) of joystick 0
    public GearCommand(Gears gears, Button a){
    	gear = gears;
    	button1 = a;
    }

    protected void initialize(){
    	requires(gear);
    }

    protected void execute(){
    	if(button1.getHold()){
    		isPressed = true;
    	}
    	else{
    		isPressed = false;
    	}
    	gear.switchState(isPressed);
    }

    protected boolean isFinished(){
        return false;
    }
    	protected void end(){
    	gear.stop();
    }

    protected void interrupted(){
    	gear.stop();
    }
}
