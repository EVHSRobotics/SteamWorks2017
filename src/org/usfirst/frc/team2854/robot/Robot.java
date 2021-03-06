
package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot{

	public static OI oi;
	public static DriveTrain driveSystem;
	public static Climb climbSys;
	public static Gears gearsys;
	CameraServer server;

	Command autonomousCommand;
	Command drive;
	Command climbcommand;
	SendableChooser<Command> chooser=new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit(){
		oi=new OI();
		RMap rmap=new RMap();
		driveSystem=new DriveTrain(rmap.CANTALON_0,rmap.CANTALON_1,rmap.CANTALON_2,rmap.CANTALON_3);
		climbSys=new Climb(rmap.CLIMBTALON_6,rmap.CLIMBTALON_5);
		gearsys=new Gears(rmap.Servo2,rmap.Servo3);
		server=CameraServer.getInstance();
		server.startAutomaticCapture();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode",chooser);
		System.out.println("Initialized robot");

		autonomousCommand=new AutoDrive(driveSystem,rmap.ENCODER_01);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit(){

	}

	@Override
	public void disabledPeriodic(){
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit(){
		System.out.println("Initiation autonomous");
		RMap.Servo2.setAngle(45);
		RMap.Servo3.setAngle(90);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand!=null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit(){
		System.out.println("Initiation teleop");
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand!=null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new Drive(driveSystem,oi.controller0.art,oi.controller0.alt,oi.controller0.alx));
		Scheduler.getInstance().add(new ClimbCommand(climbSys,oi.controller1.arx));
		Scheduler.getInstance().add(new GearCommand(gearsys,oi.controller1.bb));
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic(){
		LiveWindow.run();
	}
}
