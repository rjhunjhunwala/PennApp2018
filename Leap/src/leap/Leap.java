package leap;

/**
 * ****************************************************************************\
 * Copyright (C) 2012-2016 Leap Motion, Inc. All rights reserved. * Leap Motion
 * proprietaray and confidential. Not for distribution. * Use subject to the
 * terms of the Leap Motion SDK Agreement available at *
 * https://developer.leapmotion.com/sdk_agreement, or another agreement *
 * between Leap Motion and you, your company or other organization. *
 * \*****************************************************************************
 */
import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Leap {

	public static final int SKIP_Y = 678;
	public static final int SKIP_BACK_X = 629;
	public static final int SKIP_FORWARD_X = 735;
	
	public static final int BarY = 699;
	public static final int BarXStart = 1074;
	public static final int BarXEnd = 1300;
	public static final int LENGTH = BarXEnd - BarXStart;

	public static final int SpeakX = 1211;
	public static final int SpeakY = 748;

	public static Robot r = null;

	static {
		try {
			r = new Robot();
		} catch (Exception ex) {
			//who cares? It's past midnight. 
		}
	}

	public static final int N_AXIS = 1;

	public static final int minVolHeight = 70;
	public static final int maxVolHeight = 400;

	public static double mapZero = (minVolHeight * 3 + maxVolHeight) / 4;
	public static final int DH = maxVolHeight - minVolHeight;

	/**
	 * map to a value between min and max volume height
	 *
	 * @return
	 */
	public static double normalize(double in) {
		return mapZero = (((con.frame().hands().get(0).fingers().extended().count() == 0) && !con.frame().hands().isEmpty()) ? minVolHeight - 25 : in == 0 ? mapZero : in < minVolHeight ? minVolHeight : in > maxVolHeight ? maxVolHeight : in);
	}

	public static Point getMouseLocation() {
		return MouseInfo.getPointerInfo().getLocation();
	}

	public static void moveMouse(int x, int y) {
		r.mouseMove(x, y);
	}

	public static void click(int x, int y) {
		moveMouse(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public static final Controller con = new Controller();

	
	public static void click(double x, double y) {
		click((int) x, (int) y);
	}
	static boolean temp = false;
// arbitrary "speed" epsilon
	public static final double E = 70;
	
		static AtomicBoolean shouldSkip = new AtomicBoolean(false);
	public static void createWaitingThread(){
		new Thread(
			new Runnable(){
			public void run(){
				try{
					Thread.sleep(3000);
					shouldSkip.set(true);
				}catch(Exception ex){
					//we're using an inner class, good practice? NO!
				}
			}	
			}
		).start();
	}
	public static void main(String[] args) throws Exception {

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(35 * 1000);
				} catch (Exception ex) {
					//all of this code is exceptionally poor
				}

				System.exit(0);
			}
		}).start();
	
		createWaitingThread();
		double pastX = 0;
		click(SpeakX, SpeakY);
		Thread.sleep(5000);
		boolean shouldClick = true;
		createWaitingThread();
		for (;;) {
double newX = con.frame().hands().get(0).palmPosition().getX();
			if(pastX!=-4200){
	double dX = newX - pastX;
	if(Math.abs(dX)>10){
shouldSkip.set(false);
createWaitingThread();
		if(dX>0){
			click(SKIP_FORWARD_X,SKIP_Y);
		}else{
			click(SKIP_BACK_X,SKIP_Y);
		}
		Thread.sleep(1000);
		click(SpeakX,SpeakY);
		Thread.sleep(1000);
	}
}
			pastX = newX;
			if (mapZero > minVolHeight || con.frame().hands().get(0).fingers().extended().count() != 0)click(BarXStart + (normalize(con.frame().hands().get(0).palmPosition().getY()) - minVolHeight) / (DH / 1.0) * (LENGTH / 1.0), BarY);

		}
	}

}
