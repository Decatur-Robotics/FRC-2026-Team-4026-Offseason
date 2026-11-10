package first.robot.subsystems.shooter;

import static org.wpilib.units.Units.Inches;
import static org.wpilib.units.Units.Radians;

import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.math.geometry.Transform3d;
import org.wpilib.units.measure.Angle;
import org.wpilib.units.measure.Distance;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;


public class ShooterConstants {

  public static final double FUEL_REST_VELOCITY = 0.0;
  public static final double kP = 0.62;
  public static final double kI = 0.0;
  public static final double kD = 0.0;// 0.0035
  public static final double kS = 0.15;
  public static final double kV = 0.12;
  public static final double kA = 10.0;

  public static final double SHOOTER_CURRENT_LIMIT = 60;

  public static final Slot0Configs SLOT_0_CONFIGS = new Slot0Configs()
      .withKP(kP) // 0.5
      .withKI(kI)
      .withKD(kD)
      .withKS(kS) // 0.19
      .withKV(kV) // 0.13
      .withKA(kA);

  public static final double LOOK_AHEAD_SECONDS = 0.25;
  public static final Transform3d ROBOT_TO_TURRET = new Transform3d(0, 0, 0, new Rotation3d());
  public static final Angle OPTIMAL_PITCH = Radians.of(0);
  public static final Distance hubFunnelClearance = Inches.of(0);
}