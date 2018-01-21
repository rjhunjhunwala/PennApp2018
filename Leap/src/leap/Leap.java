package leap;


import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
  public static Frame lastFrame = null;
	
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
	public static final double E = 60;

	static AtomicBoolean shouldSkip = new AtomicBoolean(false);

	public static void createWaitingThread() {
		new Thread(
			new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					shouldSkip.set(true);
				} catch (Exception ex) {
					//we're using an inner class, good practice? NO!
				}
			}
		}
		).start();
	}

		public static int getTempo(String song){
	try{
	
		
		String query = "https://songbpm.com/";
		String firstPart = "";
		for(char c: song.toCharArray()){
			if(Character.isAlphabetic(c)||Character.isDigit(c)){
				firstPart+=Character.toLowerCase(c);
			}else if(c==' '||c=='-'||c=='\''||c=='.'){
				firstPart+='-';
			}
		}
		query+=firstPart;
	 query+="?q="+song.replaceAll(" ","%20").replaceAll(" ","%27");
	
		URL url = new URL(query);
		InputStream inputStream = null;
		DataInputStream dataInputStream;
		
		inputStream = url.openStream();
		
		dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
		
		String s;
		while((s=dataInputStream.readLine())!=null){
			//we parse HTML with regex
		if(s.matches("[ \\t]*<p class=\"title\">[\\d]+</p>")){
		String out = "";
			for(char c:s.toCharArray()){
			if(Character.isDigit(c)){
				out+=c;
		}
			
		}
			int ret = Integer.parseInt(out);
		return ret;
		
		}
	}

}catch(Exception ex){
	System.out.println(ex);
	return 120;
//If something goes horribly wrong, return 120. 
}
return 120;	
}
		public static int sum(Color c){
			return c.getGreen()+c.getRed()+c.getBlue();
		}
		public static String findSongName(){
		int xEnd = xBoxEndMax;
		String song = "Should I stay or should I go now?";
			for(;;){
				if(sum(r.getPixelColor(xEnd,yBoxSearch))>400){
					break;
				}
				xEnd--;
			}
			xEnd -=22;
			Rectangle rect = new Rectangle(0,yBoxSearch-15,xEnd,30);
			int newH, newW;
			BufferedImage b = r.createScreenCapture(rect);
			java.awt.Image img = b.getScaledInstance(newW=b.getWidth()*3,newH=b.getHeight()*3,BufferedImage.TYPE_INT_ARGB);
			b = new BufferedImage(newW, newH,BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D g = b.createGraphics();
			g.drawImage(img,0,0,null);
			g.dispose();
	
			try{
				
			ImageIO.write(b,"png",new File("temp"));
			//Runtime.getRuntime().exec(new String[]{"cmd.exe", "ocr.bat"});
//Process p = Runtime.getRuntime().exec("tesseract temp out");
			//while(true)System.out.println((char)p.getInputStream().read());
  Runtime.
   getRuntime().
   exec("cmd /c start \"\" ocr.bat");
	
			}catch(Exception ex){}
		return song;
		}
public static final int xBoxEndMax = 280;
public static final int yBoxSearch = 682;

	public static void main(String[] args) throws Exception {

	Thread.sleep(5000);

	System.out.println(findSongName());
		createWaitingThread();
		double pastX = 0;

		createWaitingThread();
		boolean wereHands = false;
		for (;;) {
			if(con.frame().hands().get(0).fingers().extended().count()!=2){
			boolean nowHands = !con.frame().hands().isEmpty();
if(lastFrame!=null){
	if(nowHands&&!wereHands){

		Thread.sleep(1000);
		click(SpeakX, SpeakY);
		Thread.sleep(1000);
	}
}
wereHands = nowHands;
			double newX = con.frame().hands().get(0).palmPosition().getX();

			double dX = newX - pastX;
			if (shouldSkip.get()) {
				if (Math.abs(dX) > E && pastX != 0) {
					shouldSkip.set(false);
					createWaitingThread();
					if (dX > 0) {
						click(SKIP_FORWARD_X, SKIP_Y);
						Thread.sleep(1000);
						click(SpeakX, SpeakY);
						Thread.sleep(1000);
					} else {
						click(SKIP_BACK_X, SKIP_Y);
						Thread.sleep(250);
						click(SKIP_BACK_X, SKIP_Y);
						Thread.sleep(1000);
						click(SpeakX, SpeakY);
						Thread.sleep(1000);
					}

				}
			}
lastFrame = con.frame();
			pastX = newX;
			
			if (!con.frame().hands().isEmpty()&&(mapZero > minVolHeight || con.frame().hands().get(0).fingers().extended().count() != 0)) {
				click(BarXStart + (normalize(con.frame().hands().get(0).palmPosition().getY()) - minVolHeight) / (DH / 1.0) * (LENGTH / 1.0), BarY);
			}

		}
	}
	}
}
