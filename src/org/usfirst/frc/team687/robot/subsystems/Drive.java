package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Drive extends Subsystem {

    private static TalonSRX m_leftMaster;
    private static TalonSRX m_leftSlave_1;
    private static TalonSRX m_leftSlave_2;
    private static TalonSRX m_rightMaster;
    private static TalonSRX m_rightSlave_1;
    private static TalonSRX m_rightSlave_2;
    
    private static AHRS m_gyro;
    
    public Drive() {
    	
    	m_leftMaster = new TalonSRX(RobotMap.kLeftMasterID);
    	m_leftSlave_1 = new TalonSRX(RobotMap.kLeftSlave_1ID);
    	m_leftSlave_2 = new TalonSRX(RobotMap.kLeftSlave_2ID);
    	m_rightMaster = new TalonSRX(RobotMap.kRightMasterID);
    	m_rightSlave_1 = new TalonSRX(RobotMap.kRightSlave_1ID);
    	m_rightSlave_2 = new TalonSRX(RobotMap.kRightSlave_2ID);
    	
    	
    	m_leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 0);
    	m_rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0 , 0);
    	
    	m_rightMaster.setSensorPhase(false);
    	m_leftMaster.setSensorPhase(false);
    	
    	m_leftMaster.setInverted(true);
    	m_leftSlave_1.setInverted(true);
    	m_leftSlave_2.setInverted(true);
    	m_rightMaster.setInverted(false);
    	m_rightSlave_1.setInverted(false);
    	m_rightSlave_2.setInverted(false);
   
    }
    
    public void setPower( double leftPower , double rightPower ) {
    	
    	m_leftMaster.set(ControlMode.PercentOutput, leftPower);
    	m_leftSlave_1.set(ControlMode.PercentOutput, leftPower);
    	m_leftSlave_2.set(ControlMode.PercentOutput, leftPower);
    	m_rightMaster.set(ControlMode.PercentOutput, rightPower);
    	m_rightSlave_1.set(ControlMode.PercentOutput, rightPower);
    	m_rightSlave_2.set(ControlMode.PercentOutput, rightPower);
    	
    }
    public double getRightPosition() {
    	
    	return m_rightMaster.getSelectedSensorPosition(0);
    	
    	
    }
    public double getLeftPosition() {
    	
    	return m_leftMaster.getSelectedSensorPosition(0);
    	
    }
    public double getAverage() {
    	
    	return ((getRightPosition() + getLeftPosition()) / 2);
    	
    }
    public void resetEncoders() {
        
    	m_rightMaster.setSelectedSensorPosition(0, 0, 0);
    	m_leftMaster.setSelectedSensorPosition(0, 0, 0);
    	
    }
    public void initDefaultCommand() {
        
    	setDefaultCommand( new TankDrive());
    	
    }
    
    public void reportToSmartDashboard() {
    	
    	SmartDashboard.putNumber("rightPosition", getRightPosition());
    	SmartDashboard.putNumber("leftPosition", getLeftPosition());
    	SmartDashboard.putNumber("averagePosition", getAverage());
    	
    }
    
}

