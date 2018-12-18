package com.saintsrobotics.TshirtCannon2018;

import java.util.function.Supplier;
import com.github.dozer.TaskRobot;
import com.github.dozer.coroutine.Task;
import com.github.dozer.coroutine.helpers.RunEachFrameTask;
import com.github.dozer.coroutine.helpers.RunParallelTask;
import com.github.dozer.coroutine.helpers.RunSequentialTask;
import com.github.dozer.input.OI.XboxInput;
import com.saintsrobotics.TshirtCannon.input.OI;
import com.saintsrobotics.TshirtCannon.input.Sensors;
import com.saintsrobotics.TshirtCannon.input.TestSensors;
import com.saintsrobotics.TshirtCannon.output.RobotMotors;
import com.saintsrobotics.TshirtCannon.output.TestBotMotors;
import com.saintsrobotics.TshirtCannon.tasks.teleop.ArcadeDrive;
import com.saintsrobotics.TshirtCannon.tasks.teleop.CloseTubes;
import com.saintsrobotics.TshirtCannon.tasks.teleop.FireTube1;
import com.saintsrobotics.TshirtCannon.util.ForwardConfiguration;
import com.saintsrobotics.TshirtCannon.util.TurnConfiguration;
import com.saintsrobotics.TshirtCannon.util.UpdateMotors;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the IterativeRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the manifest file in the
 * resource directory.
 */
public class Robot extends TaskRobot {
	
    private SendableChooser<Supplier<Task>> taskChooser;


  public RobotMotors motors;
  public OI oi;
  public Flags flags;
  public Sensors sensors;

  public static Robot instance;

  @Override
  public void robotInit() {
    Robot.instance = this;
    taskChooser = new SendableChooser<>();
    this.oi = new OI();
    this.motors = new TestBotMotors();
    this.motors.init();
    //this.temp = new SpeedController[8];
    //for(int i = 1; i < 9; i++) this.temp[i-1] = new Talon(i);
    this.flags = new Flags();
    this.sensors = new TestSensors();
    
    this.sensors.init();
    this.flags.forwardPidConfig = new ForwardConfiguration(this.sensors.gyro, this.sensors.average);
    this.flags.turnPidConfig = new TurnConfiguration(this.sensors.gyro);
    
    this.flags.pdp = new PowerDistributionPanel();
    
    }
    Relay tube1 = new Relay(0);
    Relay tube2 = new Relay(1);
    Relay tube3 = new Relay(2);
    Relay tube4 = new Relay(3);
    
    

  @Override
  public void autonomousInit() {
      }

  @Override     
  public void teleopInit() {
    
    //tube1.setDirection(Relay.Direction.kReverse);
    XboxInput c = Robot.instance.oi.xboxInput;
    this.teleopTasks = new Task[] {new ArcadeDrive(), new FireTube1(tube1, () -> c.A()), new FireTube1(tube1, () -> c.B()), 
        new FireTube1(tube1, () -> c.X()), new FireTube1(tube1, () -> c.Y()), new CloseTubes(tube1, tube2, tube3, tube4),
        
        new RunEachFrameTask() {

      @Override
      protected void runEachFrame() {
        
       
      }
      
      
      
    }, new UpdateMotors(this.motors)
        };
    
    /*this.teleopTasks = new Task[] {
        new Task() {
          @Override
          public void runTask() {
            DriverStation.reportWarning("Ready! Press B to begin/move on",false);
            wait.until(()->oi.xboxInput.B());
            for(int i = 0; i < 8; i++) {
              SpeedController motor = Robot.instance.temp[i];
              DriverStation.reportWarning("doing " + (i+1), false);
              motor.set(1);
              wait.forSeconds(0.5);
              motor.set(-1);
              wait.forSeconds(0.5);
              motor.set(0);
              DriverStation.reportWarning("Finished " + (i+1) + ", Ready to move on to next, which is " + (i+2), false);
              wait.forSeconds(0.5);
              wait.until(()->oi.xboxInput.B());
            }
            System.gc();
          }
        }
    };*/
    super.teleopInit();
  }
  @Override
  public void disabledInit() {
    //this.sensors.leftEncoder.reset();
    //this.sensors.rightEncoder.reset();
    this.disabledTasks = new Task[] {};
    super.disabledInit();
  }
}