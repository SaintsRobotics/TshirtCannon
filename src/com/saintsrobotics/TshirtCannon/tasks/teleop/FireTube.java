package com.saintsrobotics.TshirtCannon.tasks.teleop;

import java.util.function.BooleanSupplier;
import com.github.dozer.coroutine.helpers.RunEachFrameTask;
import edu.wpi.first.wpilibj.Relay;


public class FireTube extends RunEachFrameTask {
  Relay relay;
  private BooleanSupplier controllerButtonPressed;
  public FireTube (Relay relay, BooleanSupplier controllerButtonPressed) {
    this.relay = relay;
    this.controllerButtonPressed = controllerButtonPressed;
  }

  
  @Override
  protected void runEachFrame() {
      wait.until(this.controllerButtonPressed);
      
      //DriverStation.reportWarning("a pressed", false);
      relay.set(Relay.Value.kForward);
      wait.forSeconds(0.5);
      relay.set(Relay.Value.kOff);
      wait.forSeconds(1);
      relay.set(Relay.Value.kReverse);
      wait.forSeconds(0.5);
      relay.set(Relay.Value.kOff);
  }

}
