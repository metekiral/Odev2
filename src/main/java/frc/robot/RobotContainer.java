package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hoodSubsystem;

public class RobotContainer {
  public static hoodSubsystem hood = new hoodSubsystem();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}

