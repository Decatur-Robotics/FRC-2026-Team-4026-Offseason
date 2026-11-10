package first.robot.subsystems.shooter;

import org.wpilib.command3.Command;
import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Mechanism;

public class Shooter implements Mechanism{
    private ShooterIO io;
    private ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();

    public Shooter(ShooterIO io){
        this.io = io;
    }

    public double getVelocity(){
        return this.inputs.data.velocity();
    }

    public double getVoltage(){
        return inputs.data.voltage();
    }

    // public Command setVoltageCommand(double voltage){
    //     return run(coroutine -> {
    //         while(true){
    //             io.setVoltage(voltage);
    //             coroutine.yield();
    //         }
    //     })
    // }
}
