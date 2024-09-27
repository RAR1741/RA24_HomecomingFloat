package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Robot extends TimedRobot {
  private final DoubleSolenoid m_doubleSolenoid1 =
      new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  private final DoubleSolenoid m_doubleSolenoid2 =
      new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 4);

  private final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  private int counter = 0;
  private double resetSeconds = 4;
  private int resetCount = (int)(50.0 * resetSeconds);  // 50 * 20ms = 1s
  private boolean mode = false;

  @Override
  public void robotInit() {
    ShuffleboardTab tab = Shuffleboard.getTab("Pneumatics");
    tab.add("Double Solenoid 1", m_doubleSolenoid1);
    tab.add("Double Solenoid 2", m_doubleSolenoid2);


    // This calls a bunch of other methods, that aren't
    // available on the PCM.  :(
    // tab.add("Compressor", m_compressor);
  }

  @Override
  public void teleopPeriodic() {
    counter++;
    counter %= resetCount;

    if (counter == 0) {
      mode = !mode;

      if (mode) {
        System.out.println("Mode 1");
        m_doubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
        m_doubleSolenoid2.set(DoubleSolenoid.Value.kForward);
      } else {
        System.out.println("Mode 2");
        m_doubleSolenoid1.set(DoubleSolenoid.Value.kForward);
        m_doubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
      }
    }
  }
}
