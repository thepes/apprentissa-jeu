package org.copains.ludroid.games.findcolors.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import android.graphics.Rect;

public class FindColorsMg {

	private static FindColorsMg instance = null;
	private Hashtable<String,Rect> placedSquares = null;
	private int sizeX, sizeY, squareSize;
	private Random rnd;
	
	private FindColorsMg() {
		rnd = new Random();
		placedSquares = new Hashtable<String, Rect>();
	}
	
	public static FindColorsMg getInstance() {
		if (null == instance) {
			instance = new FindColorsMg();
		}
		return (instance);
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
