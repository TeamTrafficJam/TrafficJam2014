package org.wintrisstech.erik.iaroc;

/*******************************************************************
 * team TrafficJam
 * version 140426A 
 * setting up custom API
 *******************************************************************/
import ioio.lib.api.IOIO;
import ioio.lib.api.Uart.StopBits;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.sensors.UltraSonicSensors;

import android.R.integer;
import android.os.SystemClock;
import android.widget.SlidingDrawer;

/**
 * A Lada is an implementation of the IRobotCreateInterface, inspired by Vic's
 * awesome API. It is entirely event driven. Version 140404A...mods by Vic
 * 
 * @author Erik
 */
public class Lada extends IRobotCreateAdapter
{

	Brain b;

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
	public Lada(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard) throws ConnectionLostException
	{
		super(create);
		sonar = new UltraSonicSensors(ioio);
		this.dashboard = dashboard;
		// song(0, new int[]{58, 10});
	}

	public void initialize() throws ConnectionLostException
	{
		b = new Brain(this);
		dashboard.log("===========Start===========");
		readSensors(SENSORS_GROUP_ID6);
		dashboard.log("iAndroid2014 version 140404A");
		dashboard.log("Battery Charge = " + getBatteryCharge() + ", 3,000 = Full charge");
	}

	/**
	 * This method is called repeatedly
	 * 
	 * @throws ConnectionLostException
	 */
	public void loop() throws ConnectionLostException
	{
		b.go(100, 100);
		b.sleep(5000);
		b.stop();
		b.sleep(5000);
	}

	public void stop() throws ConnectionLostException
	{
		driveDirect(0, 0);
	}

	private void go(int leftWheelSpeed, int rightWheelSpeed) throws ConnectionLostException
	{
		driveDirect(rightWheelSpeed, leftWheelSpeed);

	}

	private void turn(int degreesToTurn) throws ConnectionLostException
	{
		go(0, 100);
		SystemClock.sleep(100 * degreesToTurn);
		stop();
	}

	private void goForward(int speed) throws ConnectionLostException
	{
		driveDirect(speed, speed);

	}

	private void goForward(int speed, int distance) throws ConnectionLostException
	{
		int currentdistance = 0;
		while (currentdistance < distance)
		{
			driveDirect(speed, speed);
			readSensors(SENSORS_GROUP_ID6);
			currentdistance += getDistance();
		}

	}

	private void hugWall() throws ConnectionLostException
	{
		if (isBumpRight())
		{

			driveDirect(101, 350);
			SystemClock.sleep(3000);
		} else
		{
			driveDirect(500, 150);
		}
	}

}
