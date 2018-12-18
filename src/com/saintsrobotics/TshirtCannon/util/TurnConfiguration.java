package com.saintsrobotics.TshirtCannon.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Preferences;

public class TurnConfiguration {
  public double turnHeadingKP;
  public double turnHeadingKI;
  public double turnHeadingKD;
  
  public PIDSource gyro;
  public double turnHeadingTolerance;
  
  public TurnConfiguration(PIDSource gyro) {
    Preferences prefs = Preferences.getInstance();
    this.turnHeadingKP = prefs.getDouble("turnHeadingKP", 0.077);
    this.turnHeadingKI = prefs.getDouble("turnHeadingKI", 0.000005);
    this.turnHeadingKD = prefs.getDouble("turnHeadingKD", 0.1405);
    DriverStation.reportWarning("Turn heading KP" + this.turnHeadingKP, false);

    this.turnHeadingTolerance = 3;
    this.gyro = gyro;
  }

}
