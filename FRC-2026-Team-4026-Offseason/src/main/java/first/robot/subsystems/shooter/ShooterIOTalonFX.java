
package first.robot.subsystems.shooter;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.controls.Follower;

import java.util.Map;

import org.littletonrobotics.junction.Logger;
import org.wpilib.hardware.bus.CANPort;
import org.wpilib.units.measure.Voltage;



public class ShooterIOTalonFX implements ShooterIO {
    public TalonFX motor;
    public TalonFX followerMotor;
    private TalonFXConfiguration config;
    private TalonFXConfiguration config1;

    private StatusSignal<Voltage> voltage;

    private VoltageOut voltageRequest;

    private VelocityVoltage velocityRequest;

    private double velocity;

    private double kP;
    private double kD;

    private CANBus mainBus;

    public ShooterIOTalonFX() {

        mainBus = new CANBus(CANPort.CAN_D0);
        motor = new TalonFX(4, mainBus);
        followerMotor = new TalonFX(5, mainBus);
        followerMotor.setControl(new Follower(4, MotorAlignmentValue.Opposed));
        config = new TalonFXConfiguration().withSlot0(ShooterConstants.SLOT_0_CONFIGS);

        motor.getConfigurator().apply(config);
        voltage = motor.getMotorVoltage();
        velocity = motor.getVelocity().getValueAsDouble();
        voltageRequest = new VoltageOut(voltage.getValueAsDouble());
        velocityRequest = new VelocityVoltage(velocity);
    }

    @Override
    public void setVelocity(double velocity) {

        this.velocity = velocity;
        motor.setControl(velocityRequest.withVelocity(velocity));
        Logger.recordOutput("Target Velocity", velocity);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void periodic() {
        if (motor.hasResetOccurred()) {
            motor.optimizeBusUtilization();
            motor.getVelocity().setUpdateFrequency(40);
        }

        if (followerMotor.hasResetOccurred()) {
            followerMotor.optimizeBusUtilization();
            followerMotor.getVelocity().setUpdateFrequency(40);
        }

    }

    @Override
    public void updateInputs(ShooterIOInputs inputs) {
        inputs.data = new ShooterIOData(
                motor.isConnected(),
                followerMotor.isConnected(),
                motor.getVelocity().getValueAsDouble(),
                motor.getMotorVoltage().getValueAsDouble(),
                motor.getAcceleration().getValueAsDouble(),
                motor.getSupplyCurrent().getValueAsDouble(),
                motor.getStatorCurrent().getValueAsDouble(),
                motor.getDeviceTemp().getValueAsDouble(),
                followerMotor.getVelocity().getValueAsDouble(),
                followerMotor.getMotorVoltage().getValueAsDouble(),
                followerMotor.getSupplyCurrent().getValueAsDouble(),
                followerMotor.getStatorCurrent().getValueAsDouble(),
                followerMotor.getDeviceTemp().getValueAsDouble());
    }

}