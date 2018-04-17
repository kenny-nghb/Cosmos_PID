package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAtDistance extends Command {
	
	private double m_derivative;
	private double m_distance;
	private double m_currTime;
	private double m_currError;
	private double m_power;
	private double m_output;
	private double m_prevTime;
	private double m_prevError;
	private double m_proportional;

    public DriveAtDistance(double distance) {
    	
        requires(Robot.drive); 
        m_distance = distance;

        
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	m_currTime = Timer.getFPGATimestamp();
    	m_currError = m_distance - Robot.drive.getAverage();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	m_prevTime = m_currTime;
    	m_prevError = m_currError;
    	m_currTime = Timer.getFPGATimestamp();
    	m_currError = m_distance - Robot.drive.getAverage();
    	m_derivative = ((m_currError - m_prevError) / (m_currTime - m_prevTime)) * Constants.kDriveStraightD;
    	m_power = m_currError * Constants.kDriveStraightP + m_derivative;
    	Robot.drive.setPower(m_power, m_power);
    	SmartDashboard.putNumber("error", m_currError);

 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(m_currError) < 500;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
