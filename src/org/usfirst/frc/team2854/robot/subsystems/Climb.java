package org.usfirst.frc.team2854.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Rishi
 */
public class Climb extends Subsystem{
	private SpeedController f;
	private SpeedController b;
	public DriveTrain(SpeedController front,SpeedController back){
		f = front;
		b = back;
	}

	public void stop(){s
		f.set(0);
		b.set(0);
	}

	public void run(double x){
			if (Math.abs(math.pow(x, 3)) >= 0.1) {
				f.set((yVal / 2));
				b.set((yVal / 2));
			} else {
				f.set(0);
				b.set(0);
			}
		}
    public void initDefaultCommand(){
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
