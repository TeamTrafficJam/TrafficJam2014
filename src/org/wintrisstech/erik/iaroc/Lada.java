package org.wintrisstech.erik.iaroc;

import ioio.lib.api.IOIO;
import ioio.lib.api.Uart.StopBits;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.sensors.UltraSonicSensors;

import android.R.integer;
import android.os.SystemClock;

/**
 * A Lada is an implementation of the IRobotCreateInterface, inspired by Vic's
 * awesome API. It is entirely event driven. Version 140404A...mods by Vic
 * 
 * @author Erik
 */
public class Lada extends IRobotCreateAdapter {

	private final Dashboard dashboard;
	public UltraSonicSensors sonar;

	/**
	 * Constructs a Lada, an amazing machine!
	 * 
	 * @param ioio
	 *            the IOIO instance that the Lada can use to communicate with
	 *            other peripherals such as sensors
	 * @param create
	 *            an implementation of an iRobot
	 * @param dashboard
	 *            the Dashboard instance that is connected to the Lada
	 * @throws ConnectionLostException
	 */
	public Lada(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
			throws ConnectionLostException {
		super(create);
		sonar = new UltraSonicSensors(ioio);
		this.dashboard = dashboard;
		// song(0, new int[]{58, 10});
	}

	public void initialize() throws ConnectionLostException {
		dashboard.log("===========Start===========");
		readSensors(SENSORS_GROUP_ID6);
		dashboard.log("iAndroid2014 version 140404A");
		dashboard.log("Battery Charge = " + getBatteryCharge()
				+ ", 3,000 = Full charge");
	}

	/**
	 * This method is called repeatedly
	 * 
	 * @throws ConnectionLostException
	 */
	public void loop() throws ConnectionLostException {
		turn(90);
		SystemClock.sleep(5000);
		stop();
		SystemClock.sleep(5000);

	}

	public void stop() throws ConnectionLostException {
		driveDirect(0, 0);
	}

	private void go(int leftWheelSpeed, int rightWheelSpeed)
			throws ConnectionLostException {
		driveDirect(rightWheelSpeed, leftWheelSpeed);

	}

	private void turn(int degreesToTurn) throws ConnectionLostException {
		go(0, 100);
		SystemClock.sleep(100 * degreesToTurn);
		stop();
	}
}
