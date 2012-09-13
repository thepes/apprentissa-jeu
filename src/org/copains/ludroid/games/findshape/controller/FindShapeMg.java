package org.copains.ludroid.games.findshape.controller;

import org.copains.tools.games.SquarePlacementMg;

import android.graphics.Rect;

public class FindShapeMg {
	
	private SquarePlacementMg squarePlacementMg;

	public void initGame(int sizeX, int sizeY) {
		squarePlacementMg = SquarePlacementMg.getInstance();
		squarePlacementMg.initGame(sizeX, sizeY);
		
		// triangle
		Rect rnd = squarePlacementMg.getUnusedRandomPosition();
		
		// rectangle
		// carré
		// rond
		// trapèze ?
		// ovale 
		
		
	}
	
}
