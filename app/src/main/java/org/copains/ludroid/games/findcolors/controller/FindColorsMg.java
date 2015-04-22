package org.copains.ludroid.games.findcolors.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.copains.ludroid.R;
import org.copains.ludroid.games.tools.InGameHelpMg;

import android.graphics.Color;
import android.graphics.Rect;

public class FindColorsMg {

	private static FindColorsMg instance = null;
	private Hashtable<Integer,Rect> placedSquares = null;
	private int sizeX, sizeY, squareSize;
	private Random rnd;
	private List<Integer> colors;
	private Hashtable<Integer, Integer> colorCodes;
	private boolean gameStarted = false;
	private boolean gameFinished;
	private int colorToFind;
	
	private FindColorsMg() {
		rnd = new Random();
		placedSquares = new Hashtable<Integer, Rect>();
		colors = new ArrayList<Integer>();
		colorCodes = new Hashtable<Integer, Integer>();
		colors.add(R.string.findColors_blue);
		colorCodes.put(R.string.findColors_blue,Color.rgb(0, 0, 255));
		colors.add(R.string.findColors_green);
		colorCodes.put(R.string.findColors_green,Color.rgb(0, 255, 0));
		colors.add(R.string.findColors_red);
		colorCodes.put(R.string.findColors_red,Color.rgb(255, 0, 0));
		colors.add(R.string.findColors_orange);
		colorCodes.put(R.string.findColors_orange,Color.rgb(255, 180, 48));
		colors.add(R.string.findColors_pink);
		colorCodes.put(R.string.findColors_pink,Color.rgb(253, 162, 255));
		colors.add(R.string.findColors_yellow);
		colorCodes.put(R.string.findColors_yellow,Color.rgb(252, 255, 19));
	}
	
	public static FindColorsMg getInstance() {
		if (null == instance) {
			instance = new FindColorsMg();
		}
		return (instance);
	}
	
	public void initGame(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		squareSize = Math.min(sizeX, sizeY) / 4;
		placedSquares = new Hashtable<Integer, Rect>();
		List<Integer> colors = Collections.list(colorCodes.keys());
		for (Integer col : colors) {
			placedSquares.put(col, getUnusedRandomPosition());
		}
		gameStarted = true;
		gameFinished = false;
		colorToFind = rnd.nextInt(colorCodes.size());
	}

	public boolean checkClick(int x, int y) {
		Rect r = placedSquares.get(colors.get(colorToFind));
		return (r.contains(x, y));
	}
	
	private Rect getUnusedRandomPosition() {
		int x = rnd.nextInt(sizeX - squareSize);
		int y = rnd.nextInt(sizeY - squareSize);
		Rect result = new Rect(x, y, x+squareSize, y+squareSize);
		boolean intersectsOther = false;
		if (Rect.intersects(result, InGameHelpMg.getInstance().getReservedRectForHelp()))
			return getUnusedRandomPosition();
		for (Rect r : placedSquares.values()) {
			if (Rect.intersects(result, r)) {
				return getUnusedRandomPosition();
			}
		}
		return (result);
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

	/**
	 * @return the colors
	 */
	public List<Integer> getColors() {
		return colors;
	}

	/**
	 * @param colors the colors to set
	 */
	public void setColors(List<Integer> colors) {
		this.colors = colors;
	}

	/**
	 * @return the colorToFind
	 */
	public int getColorToFind() {
		return colorToFind;
	}
	
	/**
	 * returns the string resource ID of the color we have to find
	 * @return
	 */
	public int getColorNameToFind() {
		return colors.get(colorToFind);
	}

	/**
	 * @param colorToFind the colorToFind to set
	 */
	public void setColorToFind(int colorToFind) {
		this.colorToFind = colorToFind;
	}

	/**
	 * @return the gameStarted
	 */
	public boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * @param gameStarted the gameStarted to set
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	/**
	 * @return the gameFinished
	 */
	public boolean isGameFinished() {
		return gameFinished;
	}

	/**
	 * @param gameFinished the gameFinished to set
	 */
	public void setGameFinished(boolean gameFinished) {
		if (gameFinished) {
			gameStarted = false;
		}
		this.gameFinished = gameFinished;
	}

	/**
	 * @return the colorCodes
	 */
	public Hashtable<Integer, Integer> getColorCodes() {
		return colorCodes;
	}

	/**
	 * @param colorCodes the colorCodes to set
	 */
	public void setColorCodes(Hashtable<Integer, Integer> colorCodes) {
		this.colorCodes = colorCodes;
	}
	
}
