/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team687.robot.commands.DriveAtDistance;
import org.usfirst.frc.team687.robot.commands.DriveTime;
import org.usfirst.frc.team687.robot.commands.ResetEncoders;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */



public class OI {

	public Joystick leftjoy;
	public Joystick rightjoy;
	public Joystick articJoy; 
	public JoystickButton DriveTime;
	public JoystickButton ResetEncoders;
	
	
	public OI() {
		
		leftjoy = new Joystick(0);
		rightjoy = new Joystick(1);
		articJoy = new Joystick(2);
		DriveTime = new JoystickButton( rightjoy , 8 );
		DriveTime.whenPressed(new DriveAtDistance(75000));
		ResetEncoders = new JoystickButton( rightjoy , 7 );
		ResetEncoders.whenPressed(new ResetEncoders());
	}
	
	public double getLeftJoyY() {
		
		return -Robot.oi.leftjoy.getY();
		
	}
	public double getRightJoyY() {
		
		return -Robot.oi.rightjoy.getY();
		
	}

}
