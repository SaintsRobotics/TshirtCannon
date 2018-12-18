package com.saintsrobotics.TshirtCannon.input;

import com.saintsrobotics.TshirtCannon.util.AveragePIDSources;
import com.saintsrobotics.TshirtCannon.util.ForwardConfiguration;
import com.saintsrobotics.TshirtCannon.util.TurnConfiguration;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public abstract class Sensors {
  public DigitalInput intake;
  public DigitalInput lifterUp;
  public DigitalInput lifterDown;

  public ADXRS450_Gyro gyro;

  public Encoder rightEncoder;
  public Encoder leftEncoder;
  public Encoder liftEncoder;
  public AveragePIDSources average;

  public DigitalInput wingsLeftIn;
  public DigitalInput wingsLeftOut;
  public DigitalInput wingsRightOut;
  public DigitalInput wingsRightIn;
  
  public ForwardConfiguration forwardConfig;
  public TurnConfiguration turnConfig;
  
  public abstract void init();
}