package jura.hostic;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.event.InputEvent;

public class Scan {
	private static Robot robot;
	static int posx;
	static int posy;
	static int w;
	static int h;
	public Scan (int posx, int posy, int w, int h) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.posx = posx;
		this.posy = posy;
		this.w = w;
		this.h = h;
	}
	
	public int getDimensions () {		
		if (isWhite (700, 92)) return 4; 
		if (isWhite (717, 63)) return 6;
		if (isWhite (835, 60)) return 8;
		if (isWhite (872, 95)) return 10;
		if (isWhite (870, 105)) return 12;
		return 0;
	}
	
	private boolean isWhite (int x, int y) {
		Color color = robot.getPixelColor(x, y);
		//robot.mouseMove(x,y);
		if (color.getBlue() > 200 && color.getRed() > 200 && color.getGreen() > 200) return true;
		return false;
	}
	
	public boolean getPositions (int dim, Board board) {
		int type = 0, x, y;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				x = posx + w / dim * j;
				y = posy + h / dim * i;
				Color color = robot.getPixelColor(x, y);
				//robot.mouseMove(x,y);
				type = getType (color);
				System.out.printf("%d ", type);
				if (type == -1) return false;
				board.setSquare(i, j, type);
			}
			System.out.print("\n");
		}
		return true;
	}
	
	public boolean click (int dim, Board board) {
		int x, y;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				x = posx + w / dim * j;
				y = posy + h / dim * i;
				robot.mouseMove(x,y);
				for (int k = 0; k < board.getClicks(i, j);k++) {
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				}
			}
			System.out.print("\n");
		}
		return true;
	}
	
	private static int getType (Color color) {
		if (color.getBlue() > 175 && color.getRed() < 75) return 2;
		if (color.getRed() > 175 && color.getBlue() < 75) return 1;
		if (color.getRed() > 10 && color.getRed() < 60 && color.getBlue() > 10 && color.getBlue() < 60 && color.getGreen() > 10 && color.getGreen () < 60) return 0;
		return -1;
	}
	
}
