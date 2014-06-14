package org.wintrisstech.erik.iaroc;

/***********************************************
 *  * team TrafficJam
 * version 140426A 
 * setting up custom API
 ***********************************************/

import ioio.lib.api.exception.ConnectionLostException;
import android.os.SystemClock;

public class Brain extends IRobotCreateAdapter {
	public Dashboard dashboard = null;
	public Brain(IRobotCreateInterface delegate,Dashboard dashboard) {
		super(delegate);
		this.dashboard = dashboard;
	}

	public static void main(String[] args) {

	}

	public void stop() throws ConnectionLostException {
		driveDirect(0, 0);

	}

	public void sleep(int sleeptime) {

		SystemClock.sleep(sleeptime);
	}

	public void go(int leftWheelSpeed, int rightWheelSpeed)
			throws ConnectionLostException {
		driveDirect(rightWheelSpeed, leftWheelSpeed);
	}

	public void turn(int degreesToTurn) throws ConnectionLostException {
		go(0, 100);
		SystemClock.sleep(100 * degreesToTurn);
		stop();
	}

	public void goForward(int speed) throws ConnectionLostException {
		driveDirect(speed, speed);
	}

	public void goForward(int speed, int distance)
			throws ConnectionLostException {
		int currentdistance = 0;
		while (currentdistance < distance) {
			driveDirect(speed, speed);
			readSensors(SENSORS_GROUP_ID6);
			currentdistance += getDistance();
		}
	}

	public void hugWall() throws ConnectionLostException {
		if (isBumpRight()) {

			driveDirect(101, 350);
			SystemClock.sleep(3000);
		} else {
			driveDirect(500, 150);

		}
	}

	public void spinRight(int degrees) throws ConnectionLostException {
		driveDirect(-300, 300);
		SystemClock.sleep(degrees * 8);
	}

	public void spinLeft(int degrees) throws ConnectionLostException {
		driveDirect(300, -300);
		SystemClock.sleep(degrees * 8);
		
		

	}
	void goStraight(int azimuth) throws ConnectionLostException {
		//dashboard.log((int) dashboard.getAzimuth() + "");
		if (dashboard.getAzimuth() < (azimuth)) {
			driveDirect(350, 500);// turn right
			//dashboard.log("turn right");
		}
		if (dashboard.getAzimuth() == (azimuth)) {
			driveDirect(500, 500);
		}
		if (dashboard.getAzimuth() > (azimuth)) {
			driveDirect(500, 350);// turn left
			//dashboard.log("turn left");
		}
	}
	
}

