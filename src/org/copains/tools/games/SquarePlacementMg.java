package org.copains.tools.games;

import java.util.Hashtable;
import java.util.Random;

import org.copains.ludroid.games.tools.InGameHelpMg;

import android.graphics.Rect;

public class SquarePlacementMg {
	
	private static SquarePlacementMg instance = null;
	private Hashtable<Integer,Rect> placedSquares = null;
	private int sizeX, sizeY, squareSize;
	private Random rnd;

	private SquarePlacementMg() {
		rnd = new Random();
		placedSquares = new Hashtable<Integer, Rect>();		
	}
	
	public void initGame(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		squareSize = Math.min(sizeX, sizeY) / 4;
		placedSquares = new Hashtable<Integer, Rect>();
	}

	
	public Rect getUnusedRandomPosition() {
		int x = rnd.nextInt(sizeX - squareSize);
		int y = rnd.nextInt(sizeY - squareSize);
		Rect result = new Rect(x, y, x+squareSize, y+squareSize);
		if (Rect.intersects(result, InGameHelpMg.getInstance().getReservedRectForHelp()))
			return getUnusedRandomPosition();
		for (Rect r : placedSquares.values()) {
			if (Rect.intersects(result, r)) {
				return getUnusedRandomPosition();
			}
		}
		return (result);
	}

	public void addPlacedSquare(Integer pos, Rect square) {
		placedSquares.put(pos, square);
	}
	
	/**
	 * @return the placedSquares
	 */
	public Hashtable<Integer, Rect> getPlacedSquares() {
		return placedSquares;
	}

	/**
	 * @param placedSquares the placedSquares to set
	 */
	public void setPlacedSquares(Hashtable<Integer, Rect> placedSquares) {
		this.placedSquares = placedSquares;
	}

	
	public static SquarePlacementMg getInstance() {
		if (null == instance) {
			instance = new SquarePlacementMg();
		}
		return instance;
	}

}
