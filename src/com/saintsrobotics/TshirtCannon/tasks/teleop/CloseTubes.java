package com.saintsrobotics.TshirtCannon.tasks.teleop;

import com.github.dozer.coroutine.helpers.RunEachFrameTask;
import com.saintsrobotics.TshirtCannon.Robot;

import edu.wpi.first.wpilibj.Relay;

public class CloseTubes extends RunEachFrameTask {
  Relay tube1;
  Relay tube2;
  Relay tube3;
  Relay tube4;
  
  public CloseTubes (Relay tube1, Relay tube2, Relay tube3, Relay tube4) {
    this.tube1 = tube1;
    this.tube2 = tube2;
    this.tube3 = tube3;
    this.tube4 = tube4;
  }
  @Override
  protected void runEachFrame() {
    wait.until(() ->  Robot.instance.oi.xboxInput.LB());
    tube1.set(Relay.Value.kReverse);
    tube2.set(Relay.Value.kReverse);
    tube3.set(Relay.Value.kReverse);
    tube4.set(Relay.Value.kReverse);
    wait.forSeconds(0.5);
    tube1.set(Relay.Value.kOff);
    tube2.set(Relay.Value.kOff);
    tube3.set(Relay.Value.kOff);
    tube4.set(Relay.Value.kOff);
    
  }
  
}
