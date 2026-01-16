package frc.robot;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_RobotContainer;

  PS5Controller driverJoystick = new PS5Controller(0);

  public Robot() {
    m_RobotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_RobotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    if (driverJoystick.getRawButton(1)) {
      if (RobotContainer.hood.getsensor()) {
        RobotContainer.hood.setHoodAngle(50);
      } else if (driverJoystick.getRawButton(2)) {
        if (RobotContainer.hood.getsensor()) {
          RobotContainer.hood.setHoodAngle(80);
        }
      } else {
        RobotContainer.hood.setHoodAngle(30);
      }
    }

  }
}
