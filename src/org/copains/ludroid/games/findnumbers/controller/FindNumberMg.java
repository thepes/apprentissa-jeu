package org.copains.ludroid.games.findnumbers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Rect;

public class FindNumberMg {
	
	private static FindNumberMg instance = null;
	
	private boolean gameStarted = false;
	private int sizeX, sizeY, textSize;
	private List<Rect> placedNumbers;
	
	private FindNumberMg() {
		
	}
	
	public static final FindNumberMg getInstance() {
		if (null == instance) {
			instance = new FindNumberMg();
		}
		return (instance);
	}
	
	public void initGame(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		textSize = Math.min(sizeX, sizeY) / 6;
		placedNumbers = new ArrayList<Rect>();
		for (int i = 0 ; i < 10 ; i++) {
			placedNumbers.add(getUnusedRandomPosition());
		}
		gameStarted = true;
	}
	
	public boolean checkClick(int number, int x, int y) {
		Rect r = placedNumbers.get(number);
		return (r.contains(x, y));
	}
	
	private Rect getUnusedRandomPosition() {
		Random rnd = new Random();
		int x = rnd.nextInt(sizeX - textSize);
		int y = rnd.nextInt(sizeY - textSize);
		Rect result = new Rect(x, y, x+textSize, y+textSize);
		boolean intersectsOther = false;
		for (Rect r : placedNumbers) {
			if (Rect.intersects(result, r)) {
				intersectsOther = true;
			}
		}
		if (intersectsOther) {
			return getUnusedRandomPosition();
		}
		return (result);
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public List<Rect> getPlacedNumbers() {
		return placedNumbers;
	}

	public void setPlacedNumbers(List<Rect> placedNumbers) {
		this.placedNumbers = placedNumbers;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

}
