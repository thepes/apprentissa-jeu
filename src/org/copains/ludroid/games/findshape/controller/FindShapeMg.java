package org.copains.ludroid.games.findshape.controller;

import java.util.ArrayList;
import java.util.List;

import org.copains.ludroid.games.findshape.shapes.TriangleShape;
import org.copains.tools.games.SquarePlacementMg;

import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;

public class FindShapeMg {
	
	private static FindShapeMg instance = null;
	private SquarePlacementMg squarePlacementMg;
	private List<ShapeDrawable> shapesDrawables;

	public void initGame(int sizeX, int sizeY) {
		squarePlacementMg = SquarePlacementMg.getInstance();
		squarePlacementMg.initGame(sizeX, sizeY);
		shapesDrawables = new ArrayList<ShapeDrawable>();
		
		// triangle
		Rect rnd = squarePlacementMg.getUnusedRandomPosition();
		ShapeDrawable sDrawable = new ShapeDrawable(new TriangleShape());
		sDrawable.setBounds(rnd);
		sDrawable.getPaint().setARGB(255, 255, 0, 0);
		shapesDrawables.add(sDrawable);
		// rectangle
		sDrawable = new ShapeDrawable(new RectShape());
		rnd = squarePlacementMg.getUnusedRandomPosition();
		sDrawable.setBounds(rnd);
		sDrawable.getPaint().setARGB(255,255, 0, 0);
		shapesDrawables.add(sDrawable);
		// carré
		// rond
		sDrawable = new ShapeDrawable(new OvalShape());
		rnd = squarePlacementMg.getUnusedRandomPosition();
		sDrawable.setBounds(rnd);
		sDrawable.getPaint().setARGB(255, 255, 0, 0);
		shapesDrawables.add(sDrawable);
		// trapèze ?
		// ovale 
		
		
	}

	public List<ShapeDrawable> getShapesDrawables() {
		return shapesDrawables;
	}

	public void setShapesDrawables(List<ShapeDrawable> shapesDrawables) {
		this.shapesDrawables = shapesDrawables;
	}

	public static FindShapeMg getInstance() {
		if (null == instance) {
			instance = new FindShapeMg();
		}
		return instance;
	}
	
	private FindShapeMg() {
	
	}

}