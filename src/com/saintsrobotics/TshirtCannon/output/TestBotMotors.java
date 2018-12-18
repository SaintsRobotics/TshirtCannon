package com.saintsrobotics.TshirtCannon.output;

import com.github.dozer.output.Motor;
import com.github.dozer.output.MotorGroup;
import com.github.dozer.output.MotorSimple;
import edu.wpi.first.wpilibj.Talon;

public class TestBotMotors extends RobotMotors {
  
  @Override
  public void init() {
    this.leftBack = new MotorRamping(new Talon(0), false);
    this.leftFront = new MotorRamping(new Talon(1), false);
    this.rightBack = new MotorRamping(new Talon(2), true);
    this.rightFront = new MotorRamping(new Talon(3), true);
    
    this.leftDrive = new MotorGroup(this.leftBack, this.leftFront);
    this.rightDrive = new MotorGroup(this.rightBack, this.rightFront);
    this.intakeLeft = new MotorSimple(new Talon(5), false);
    this.intakeRight = new MotorSimple(new Talon(8), false);
    this.intake = new MotorGroup(this.intakeRight, this.intakeLeft);
    this.lifter = new MotorSimple(new Talon(4), false);
    
    
    //this.rightWing = new MotorSimple(new Talon(8), false);
    //this.leftWing = new MotorSimple(new Talon(1), false);
    this.allMotors = new Motor[] {
        this.leftDrive,
        this.rightDrive,
        this.intake,
        this.intakeRight,
        this.intakeLeft,
        this.lifter,
        //this.leftWing,
        //this.rightWing,
        this.leftBack,
        this.leftFront,
        this.rightBack,
        this.rightFront
   };
   this.rampedMotors = new MotorRamping[] {
       (MotorRamping) this.leftBack,
       (MotorRamping) this.rightBack,
       (MotorRamping) this.leftFront,
       (MotorRamping) this.rightFront
   };
  }
}
