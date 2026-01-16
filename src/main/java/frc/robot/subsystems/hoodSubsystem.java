package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class hoodSubsystem extends SubsystemBase {
  TalonFX hoodMotor = new TalonFX(0);
  TalonFX hood2Motor = new TalonFX(1);
  MotionMagicVoltage request = new MotionMagicVoltage(0);
  DigitalInput m_input = new DigitalInput(0);

  final MotionMagicConfigs MotionMagicConfig = new MotionMagicConfigs()
      .withMotionMagicCruiseVelocity(80)
      .withMotionMagicAcceleration(160)
      .withMotionMagicJerk(1600);

  final Slot0Configs PIDConfig = new Slot0Configs()
      .withKP(4.8)
      .withKI(0)
      .withKD(0.1);

  public hoodSubsystem() {
    TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration()
        .withMotorOutput(
            new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive)
                .withNeutralMode(NeutralModeValue.Brake))
        .withCurrentLimits(
            new CurrentLimitsConfigs()
                .withStatorCurrentLimit(Amps.of(120))
                .withStatorCurrentLimitEnable(true))
        .withFeedback(new FeedbackConfigs()
            .withSensorToMechanismRatio(20.0))
        .withMotionMagic(MotionMagicConfig)
        .withSlot0(PIDConfig);

    hoodMotor.getConfigurator().apply(talonFXConfigs);
    hood2Motor.getConfigurator().apply(talonFXConfigs);

    hood2Motor.setControl(new Follower(hoodMotor.getDeviceID(), false));
  }

  public boolean getsensor() {
    return m_input.get();
  }

  public void setHoodAngle(double angle) {
    double finalAngle = MathUtil.clamp(angle, 0, 80);

    hoodMotor.setControl(request.withPosition(finalAngle));
    SmartDashboard.putNumber("Hood Position", hoodMotor.getPosition().getValueAsDouble());
  }

  @Override
  public void periodic() {
  }
}