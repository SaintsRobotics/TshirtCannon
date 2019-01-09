package com.saintsrobotics.TshirtCannon2018;

import java.util.function.Supplier;
import com.github.dozer.TaskRobot;
import com.github.dozer.coroutine.Task;
import com.github.dozer.coroutine.helpers.RunEachFrameTask;
import com.github.dozer.input.OI.XboxInput;
import com.saintsrobotics.TshirtCannon.input.OI;
import com.saintsrobotics.TshirtCannon.output.RobotMotors;
import com.saintsrobotics.TshirtCannon.output.TestBotMotors;
import com.saintsrobotics.TshirtCannon.tasks.teleop.ArcadeDrive;
import com.saintsrobotics.TshirtCannon.tasks.teleop.CloseTubes;
import com.saintsrobotics.TshirtCannon.tasks.teleop.FireTube;
import com.saintsrobotics.TshirtCannon.util.UpdateMotors;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
    this.teleopTasks = new Task[] {new ArcadeDrive(), new FireTube(tube1, () -> c.A()), new FireTube(tube1, () -> c.B()), 
        new FireTube(tube1, () -> c.X()), new FireTube(tube1, () -> c.Y()), new CloseTubes(tube1, tube2, tube3, tube4),
        
        new RunEachFrameTask() {

      @Override
      protected void runEachFrame() {
        
       
      }
      
      
      
    }, new UpdateMotors(this.motors)
        };
    

    super.teleopInit();
  }
  @Override
  public void disabledInit() {
    this.disabledTasks = new Task[] {};
    super.disabledInit();
  }
}