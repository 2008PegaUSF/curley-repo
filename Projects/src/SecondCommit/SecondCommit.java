/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondCommit;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.net.*;
import java.awt.image.*;
import javax.imageio.*;

/**
 *
 * @author superlappy7
 */
public class SecondCommit extends JPanel {

	public static Random rand = new Random(getTime());

	boolean running = false;
	private final Toolkit tk = Toolkit.getDefaultToolkit();

	static JFrame jf;
	
	public static BufferedImage buffer;
	private Graphics2D g2d;

	private BufferedImage capture;
	private BufferedImage capturegrey;

	public SecondCommit() throws AWTException {
		System.out.println("constructing");
		buffer = new BufferedImage(tk.getScreenSize().width, tk.getScreenSize().height, BufferedImage.TYPE_INT_ARGB);
		Rectangle r = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		capture = new Robot().createScreenCapture(r);
		capturegrey = new Robot().createScreenCapture(r);
		capturegrey = makeGray(capturegrey);
		g2d = buffer.createGraphics();
		// this.start();
		this.init();
		Thread mainThread = new Thread(new looper());
		mainThread.setDaemon(true);

		mainThread.start();
	}

	boolean initDone = false;

	public void init() {
		System.out.println("init");

		start();
	}

	private BufferedImage makeGray(BufferedImage img) {
		/*
		 * for (int x = 0; x < img.getWidth(); x++) { for (int y = 0; y <
		 * img.getHeight(); y++) { int rgb = img.getRGB(x, y); int r = (rgb >> 16) &
		 * 0xff; int g = (rgb >> 8) & 0xff; int b = (rgb & 0xff);
		 * 
		 * int highest = ( r + g + b ) / 3; // if (r > highest) highest = r; // if (b >
		 * highest) highest = b; // if (g > highest) highest = g;
		 * 
		 * double rr = Math.pow(r / 255, 2.2); double gg = Math.pow(g / 255, 2.2);
		 * double bb = Math.pow(b / 255, 2.2);
		 * 
		 * double lum = 0.4126 * rr + 0.8152 * gg + 0.722 * bb;
		 * 
		 * int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2)); int gray =
		 * (grayLevel << 16) + (grayLevel << 8) + grayLevel; img.setRGB(x, y, gray); } }
		 */

		BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return op.filter(img, null);
	}

	protected class looper implements Runnable {
		public void run() {
			while (running) {
				try {
					updateAll();
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}

		}
	}

	private float alpha = 1.0f;
	private float windowAlpha = 1.0f;
	private int fontAlpha = 0;
	private int colorStep = 0;

	private boolean drawBox = true;

	private void updateAll() {
		AffineTransform aff = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(aff,true,true);
		fontX = (int)font.getStringBounds("YOU DIED", frc).getWidth()/2;
		fontY = capture.getHeight() / 2;//(int)font.getStringBounds("YOU DIED", frc).getHeight();
		//fontSize += 0.1f;
		if (alpha > 0.01f) {
			drawBox = true;
		} else {
			drawBox = false;

		}
		if (drawBox) {
			fontAlpha += 10;
			boxAlpha += 0.07f;
			if (boxAlpha > 0.9f)
				boxAlpha = 0.9f;
			if(fontAlpha > 255) {
				fontAlpha = 255;
			}

		} else {
			drawBox = false;
			boxAlpha -= 0.07f;
			fontAlpha -= 40;
			if(fontAlpha < 0) {
				fontAlpha = 0;
			}
			if (boxAlpha < 0f)
				boxAlpha = 0f;
		}

		if (alpha > 0.01f) {
			alpha -= 0.008f;
			return;
		}

		//this.getRootPane().setOpaque(false);

		
		colorStep += 2;
		if (colorStep + 2 > 255) {
			colorStep = 255;
			windowAlpha -= 0.02f;
			fontAlpha = 0;
			jf.setOpacity(windowAlpha);
			if(windowAlpha < 0.01f)
				System.exit(0);
		}

	}

	private Font font = new Font("Times New Roman",Font.PLAIN,144);
	private int fontX = 0;
	private int fontY = 0;
	private float fontSize = 169.0f;
	
	private float boxAlpha = 0.01f;

	public static long getTime() {
		return System.currentTimeMillis();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(capturegrey, 0, 0, this);

		if (alpha > 0.02f) {
			g.drawImage(capture, 0, 0, this);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g.drawImage(capturegrey, 0, 0, this);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.drawImage(capture, 0, 0, this);
		}
		
		g.setFont(font);
		g.setColor(new Color(0, 0, 0, colorStep));
		g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

		if (boxAlpha > 0.0f) {
			g.setColor(Color.BLACK);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, boxAlpha));
			g.fillRect(0, (int) (buffer.getHeight() / 3.25), buffer.getWidth(), (int) (buffer.getHeight() / 3.25));
			g.setColor(new Color(255,0,0,fontAlpha));
			g.drawString("   YOU DIED", fontX, fontY);
			font = new Font("Times New Roman", Font.PLAIN, (int)fontSize);

		}
		g.setColor(new Color(255,0,0,fontAlpha));
		g.drawString("   YOU DIED", fontX, fontY);
		font = new Font("Times New Roman", Font.PLAIN, (int)fontSize);

		g.dispose();

	}

	public void start() {
		initDone = true;
		this.setVisible(true);
		running = true;
		// mainThread.start();
	}

	public void stop() {
		// mainThread=null;
		System.exit(0);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO code application logic here

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException classNotFoundException) {
				} catch (InstantiationException instantiationException) {
				} catch (IllegalAccessException illegalAccessException) {
				} catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
				}

				JFrame frame = new JFrame("YOU DIED");
				frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
				try {
					frame.add(new SecondCommit());
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setUndecorated(true);
				frame.setIgnoreRepaint(true);
				jf=frame;
				frame.setVisible(true);
			}
		});
	}

}