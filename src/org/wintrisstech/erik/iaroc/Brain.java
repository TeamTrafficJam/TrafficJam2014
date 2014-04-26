
package org.wintrisstech.erik.iaroc;
/***********************************************
 *  * team TrafficJam
 * version 140426A 
 * setting up custom API
 ***********************************************/

import ioio.lib.api.exception.ConnectionLostException;
import android.os.SystemClock;

public class Brain extends IRobotCreateAdapter
{
	public Brain(IRobotCreateInterface delegate)
	{
		super(delegate);
	}

	public static void main(String[] args)
	{

	}

	public void stop() throws ConnectionLostException
	{
		driveDirect(0, 0);
		
	}
	public void sleep(int sleeptime) 
	{
		
		SystemClock.sleep(sleeptime);
	}

	public void go(int leftWheelSpeed, int rightWheelSpeed) throws ConnectionLostException
	{
		driveDirect(rightWheelSpeed, leftWheelSpeed);
	}

	public void turn(int degreesToTurn) throws ConnectionLostException
	{
		go(0, 100);
		SystemClock.sleep(100 * degreesToTurn);
		stop();
	}

	public void goForward(int speed) throws ConnectionLostException
	{
		driveDirect(speed, speed);
	}

	public void goForward(int speed, int distance) throws ConnectionLostException
	{
		int currentdistance = 0;
		while (currentdistance < distance)
		{
			driveDirect(speed, speed);
			readSensors(SENSORS_GROUP_ID6);
			currentdistance += getDistance();
		}
	}

	public void hugWall() throws ConnectionLostException
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