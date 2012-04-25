package org.copains.ludroid.games.findcolors.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.copains.ludroid.R;

import android.graphics.Color;
import android.graphics.Rect;

public class FindColorsMg {

	private static FindColorsMg instance = null;
	private Hashtable<Integer,Rect> placedSquares = null;
	private int sizeX, sizeY, squareSize;
	private Random rnd;
	private List<Integer> colors;
	private Hashtable<Integer, Integer> colorCodes;
	private boolean gameStarted;
	private boolean gameFinished;
	private int colorToFind;
	
	private FindColorsMg() {
		rnd = new Random();
		placedSquares = new Hashtable<Integer, Rect>();
		colors = new ArrayList<Integer>();
		colorCodes = new Hashtable<Integer, Integer>();
		colors.add(R.string.findColors_blue);
		colorCodes.put(R.string.findColors_blue,Color.argb(255, 0, 0, 255));
		colors.add(R.string.findColors_green);
		colorCodes.put(R.string.findColors_green,Color.argb(255, 0, 255, 0));
		colors.add(R.string.findColors_red);
		colorCodes.put(R.string.findColors_red,Color.argb(255, 255, 0, 0));
		colors.add(R.string.findColors_orange);
		colorCodes.put(R.string.findColors_orange,Color.argb(255, 255, 180, 48));
		colors.add(R.string.findColors_pink);
		colorCodes.put(R.string.findColors_pink,Color.argb(255, 253, 162, 255));
		colors.add(R.string.findColors_yellow);
		colorCodes.put(R.string.findColors_yellow,Color.argb(255, 252, 255, 19));
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

	public boolean checkClick(String color, int x, int y) {
		Rect r = placedSquares.get(color);
		return (r.contains(x, y));
	}
	
	private Rect getUnusedRandomPosition() {
		int x = rnd.nextInt(sizeX - squareSize);
		int y = rnd.nextInt(sizeY - squareSize);
		Rect result = new Rect(x, y, x+squareSize, y+squareSize);
		boolean intersectsOther = false;
		for (Rect r : placedSquares.values()) {
			if (Rect.intersects(result, r)) {
				intersectsOther = true;
			}
		}
		if (intersectsOther) {
			return getUnusedRandomPosition();
		}
		return (result);
	}
	
}
