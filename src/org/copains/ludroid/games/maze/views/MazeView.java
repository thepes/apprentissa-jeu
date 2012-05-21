package org.copains.ludroid.games.maze.views;

import org.copains.ludroid.games.maze.controller.MazeMg;
import org.copains.tools.games.Maze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MazeView extends View {
	
	private Picture drawnMaze;
	private int cellsX = 15, cellsY = 15;

	public MazeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MazeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MazeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		MazeMg mg = MazeMg.getInstance();
		if (!mg.isGameStarted()) {
			mg.initGame(cellsX, cellsY);
			initMazeBackground(canvas);
		}
		int stepX, stepY;
		stepX = canvas.getWidth()/cellsX;
		stepY = canvas.getHeight()/cellsY;
		Maze maze = mg.getMaze();
		Paint p = new Paint();
		p.setARGB(255, 255, 255, 255);
		p.setStyle(Style.FILL);
		byte[][] cells = maze.getMaze();
		for (int j = 0 ; j < cellsY ; j++)
			for (int i = 0 ; i < cellsX ; i++) {
				if (cells[i][j] == 1) {
					Rect r = new Rect(i*stepX, j*stepY, (i+1)*stepX, (j+1)*stepY);
					canvas.drawRect(r, p);
				}
			}
		//drawnMaze.draw(canvas);
	}

	private void initMazeBackground(Canvas canvas) {
		drawnMaze = new Picture();
		Canvas c = drawnMaze.beginRecording(canvas.getWidth(), canvas.getHeight());
		int stepX, stepY;
		stepX = canvas.getWidth()/cellsX;
		stepY = canvas.getHeight()/cellsY;
		MazeMg mg = MazeMg.getInstance();
		Maze maze = mg.getMaze();
		Paint p = new Paint();
		p.setARGB(255, 255, 0, 255);
		p.setStyle(Style.FILL);
		byte[][] cells = maze.getMaze();
		for (int j = 0 ; j < cellsY ; j++)
			for (int i = 0 ; i < cellsX ; i++) {
				Rect r = new Rect(i*stepX, j*stepY, (i+1)*stepX, (j+1)*stepY);
				c.drawRect(r, p);
			}
		drawnMaze.endRecording();
	}

}
