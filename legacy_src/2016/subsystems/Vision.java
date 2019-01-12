package org.usfirst.frc.team1294.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * @author jxlewis
 */
public class Vision extends Subsystem implements LiveWindowSendable, ITableListener {
	private static final String NETWORK_TABLE_NAME = "vision";
	private static final String THRESHOLD_HIGH_L = "thresholdHighL";
	private static final String THRESHOLD_HIGH_S = "thresholdHighS";
	private static final String THRESHOLD_HIGH_H = "thresholdHighH";
	private static final String THRESHOLD_LOW_L = "thresholdLowL";
	private static final String THRESHOLD_LOW_S = "thresholdLowS";
	private static final String THRESHOLD_LOW_H = "thresholdLowH";
	private static final String QUALITY = "quality";
	private static final String FPS = "fps";
	private static final String TARGET_ACQUIRED = "targetAcquired";
	private static final String TARGET_X = "targetX";
	private static final String TARGET_Y = "targetY";
	private static final String LAST_UPDATED = "lastUpdated";
	
	private static final int DEFAULT_THRESHOLD_HIGH_L = 255;
	private static final int DEFAULT_THRESHOLD_HIGH_S = 255;
	private static final int DEFAULT_THRESHOLD_HIGH_H = 104;
	private static final int DEFAULT_THRESHOLD_LOW_L = 32;
	private static final int DEFAULT_THRESHOLD_LOW_S = 122;
	private static final int DEFAULT_THRESHOLD_LOW_H = 32;
	private static final int DEFAULT_QUALITY = 10;
	private static final int DEFAULT_FPS = 5;
	private static final boolean DEFAULT_TARGET_ACQUIRED = false;
	private static final int DEFAULT_TARGET_X = 0;
	private static final int DEFAULT_TARGET_Y = 0;
	private static final long DEFAULT_LAST_UPDATED = -1;
	
	private final NetworkTable nt;
	private long lastUpdate = Long.MIN_VALUE;
	private ITable m_table = null;
	private ITableListener m_table_listener = null;

	public Vision() {
		this.nt = NetworkTable.getTable(NETWORK_TABLE_NAME);

		// persist these values between reboots
		nt.setPersistent(THRESHOLD_HIGH_H);
		nt.setPersistent(THRESHOLD_HIGH_L);
		nt.setPersistent(THRESHOLD_HIGH_S);
		nt.setPersistent(THRESHOLD_LOW_H);
		nt.setPersistent(THRESHOLD_LOW_L);
		nt.setPersistent(THRESHOLD_LOW_S);
		nt.setPersistent(QUALITY);
		nt.setPersistent(FPS);

		// listen for changes from the raspberry pi
		nt.addTableListener(this);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTable() {
		// adapted from code found in CANSpeedController
		ITable table = getTable();
	      if(table != null) {
	          table.putString("Type", getClass().getSimpleName()); // "CANTalon", "CANJaguar"
	          table.putNumber(THRESHOLD_HIGH_H, getThresholdHighH());
	          table.putNumber(THRESHOLD_HIGH_L, getThresholdHighL());
	          table.putNumber(THRESHOLD_HIGH_S, getThresholdHighS());
	          table.putNumber(THRESHOLD_LOW_H, getThresholdLowH());
	          table.putNumber(THRESHOLD_LOW_L, getThresholdLowL());
	          table.putNumber(THRESHOLD_LOW_S, getThresholdLowS());
	          table.putNumber(FPS, getFPS());
	          table.putNumber(QUALITY, getQuality());
	      }
	}

	public ITableListener createTableListener() {
	      return (table, key, value, isNew) -> {
			switch (key) {
			case THRESHOLD_LOW_H:
				setThresholdLowH((Double) value);
				break;
			case THRESHOLD_LOW_S:
				setThresholdLowS((Double) value);
				break;
			case THRESHOLD_LOW_L:
				setThresholdLowL((Double) value);
				break;
			case THRESHOLD_HIGH_H:
				setThresholdHighH((Double) value);
				break;
			case THRESHOLD_HIGH_S:
				setThresholdHighS((Double) value);
				break;
			case THRESHOLD_HIGH_L:
				setThresholdHighL((Double) value);
				break;
			case QUALITY:
				setQuality((int)value);
				break;
			case FPS:
				setFPS((int)value);
				break;
			}

	      };
	  }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode() {
		m_table_listener = createTableListener();
		m_table.addTableListener(m_table_listener, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode() {
		m_table.removeTableListener(m_table_listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITable getTable() {
		return m_table;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}
	  
	
	public int getThresholdLowH() {
		return (int)nt.getNumber(THRESHOLD_LOW_H, DEFAULT_THRESHOLD_LOW_H);
	}
	
	public void setThresholdLowH(double value) {
		nt.putNumber(THRESHOLD_LOW_H, value);
	}
	
	public int getThresholdLowS() {
		return (int)nt.getNumber(THRESHOLD_LOW_S, DEFAULT_THRESHOLD_LOW_S);
	}
	
	public void setThresholdLowS(double value) {
		nt.putNumber(THRESHOLD_LOW_S, value);
	}
	
	public int getThresholdLowL() {
		return (int)nt.getNumber(THRESHOLD_LOW_L, DEFAULT_THRESHOLD_LOW_L);
	}
	
	public void setThresholdLowL(double value) {
		nt.putNumber(THRESHOLD_LOW_L, value);
	}
	
	public int getThresholdHighH() {
		return (int)nt.getNumber(THRESHOLD_HIGH_H, DEFAULT_THRESHOLD_HIGH_H);
	}
	
	public void setThresholdHighH(double value) {
		nt.putNumber(THRESHOLD_HIGH_H, value);
	}
	
	public int getThresholdHighS() {
		return (int)nt.getNumber(THRESHOLD_HIGH_S, DEFAULT_THRESHOLD_HIGH_S);
	}
	
	public void setThresholdHighS(double value) {
		nt.putNumber(THRESHOLD_HIGH_S, value);
	}
	
	public int getThresholdHighL() {
		return (int)nt.getNumber(THRESHOLD_HIGH_L, DEFAULT_THRESHOLD_HIGH_L);
	}
	
	public void setThresholdHighL(double value) {
		nt.putNumber(THRESHOLD_HIGH_L, value);
	}
	
	public int getQuality() {
		return (int)nt.getNumber(QUALITY, DEFAULT_QUALITY);
	}
	
	public void setQuality(int quality) {
		nt.putNumber(QUALITY, quality);
	}
	
	public int getFPS() {
		return (int)nt.getNumber(FPS, DEFAULT_FPS);
	}
	
	public void setFPS(int fps) {
		nt.putNumber(FPS, fps);
	}
	
	public boolean isTargetAcquired() {
		return nt.getBoolean(TARGET_ACQUIRED, DEFAULT_TARGET_ACQUIRED);
	}
	
	public int getTargetX() {
		return (int)nt.getNumber(TARGET_X, DEFAULT_TARGET_X);
	}
	
	public int getTargetY() {
		return (int)nt.getNumber(TARGET_Y, DEFAULT_TARGET_Y);
	}
	
	public long getLastUpdated() {
		return (long)nt.getNumber(LAST_UPDATED, DEFAULT_LAST_UPDATED);
	}

	@Override
	public void valueChanged(ITable source, String key, Object value, boolean isNew) {
		if (key == LAST_UPDATED) {
			this.lastUpdate = System.currentTimeMillis();
		}
	}

	/**
	 * @return time (in milliseconds) since the last update was received from the RaspberryPi vision
	 * system
	 */
	public long getTimeSinceLastUpdate() {
		return System.currentTimeMillis() - this.lastUpdate;
	}


}
