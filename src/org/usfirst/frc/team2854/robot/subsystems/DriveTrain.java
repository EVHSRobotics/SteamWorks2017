package org.usfirst.frc.team2854.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Richard Huang
 */
public class DriveTrain extends Subsystem{
	private SpeedController fl,fr,bl,br;
	public DriveTrain(SpeedController frontLeft,SpeedController frontRight,SpeedController backLeft,SpeedController backRight){
		fl=frontLeft;
		fr=frontRight;
		bl=backLeft;
		br=backRight;
	}
	public void stop(){
		fl.set(0);
		fr.set(0);
		bl.set(0);
		br.set(0);
	}
	public void tankDrive(double y1,double y2){
		fl.set(y1);fr.set(-y2);
		bl.set(y1);fr.set(-y2);
	}
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

