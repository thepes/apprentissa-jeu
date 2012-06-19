package org.copains.ludroid.games.maze.views;

import org.copains.ludroid.R;
import org.copains.ludroid.games.maze.controller.MazeMg;
import org.copains.tools.games.Maze;
import org.copains.tools.geometry.Coordinates;
import org.copains.tools.geometry.CoordinatesConverter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MazeView extends View implements OnInitListener {
	
	private TextToSpeech ttsEngine;
	private Picture drawnMaze;
	private int cellsX = 15, cellsY = 15;
	private GestureDetector gestureDetector;
	private int stepX, stepY;
	private boolean crossedWall = false;

	public MazeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MazeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MazeView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		if (null == ttsEngine) {
			ttsEngine = new TextToSpeech(getContext(), this);
		}
		gestureDetector = new GestureDetector(getContext(), gestureListener);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		MazeMg mg = MazeMg.getInstance();
		if (!mg.isGameStarted()) {
			mg.initGame(cellsX, cellsY);
			//initMazeBackground(canvas);
		}
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
		p.setARGB(255, 100, 100, 100);
		cells = maze.getVisited();
		for (int j = 0 ; j < cellsY ; j++)
			for (int i = 0 ; i < cellsX ; i++) {
				if (cells[i][j] == 1) {
					Rect r = new Rect(i*stepX, j*stepY, (i+1)*stepX, (j+1)*stepY);
					canvas.drawRect(r, p);
				}
			}
		p.setARGB(255, 255, 0, 0);
		int x,y;
		x = maze.getEndPoint().getX();
		y = maze.getEndPoint().getY();
		Rect r = new Rect(x*stepX, y*stepY, (x+1)*stepX, (y+1)*stepY);
		canvas.drawRect(r, p);
		p.setARGB(255, 0, 255, 0);
		x = maze.getStartPoint().getX();
		y = maze.getStartPoint().getY();
		r = new Rect(x*stepX, y*stepY, (x+1)*stepX, (y+1)*stepY);
		canvas.drawRect(r, p);
		// drawing current player position 
		// TODO: add picture with small character (prince or princess depending on the sex ?)
		p.setARGB(255, 255, 0, 255);
		x = mg.getCurrentPlayerPosition().getX();
		y = mg.getCurrentPlayerPosition().getY();
		r = new Rect(x*stepX, y*stepY, (x+1)*stepX, (y+1)*stepY);
		canvas.drawRect(r, p);
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
		p.setARGB(255, 255, 255, 255);
		p.setStyle(Style.FILL);
		byte[][] cells = maze.getMaze();
		for (int j = 0 ; j < cellsY ; j++)
			for (int i = 0 ; i < cellsX ; i++) {
				if (cells[i][j] == 1) {
					Rect r = new Rect(i*stepX, j*stepY, (i+1)*stepX, (j+1)*stepY);
					c.drawRect(r, p);
				}
			}
		drawnMaze.endRecording();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		if ((event.getActionMasked() & MotionEvent.ACTION_UP) == MotionEvent.ACTION_UP) {
			Log.i("ludroid","action UP");
			MazeMg mg = MazeMg.getInstance();
			mg.reinitPreviousPosition();
			crossedWall = false;
		}
		return true;
	}
	
	private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
		
		@Override
		public boolean onScroll(android.view.MotionEvent sourceEvt, android.view.MotionEvent destEvt, float distanceX, float distanceY) {
			MazeMg mg = MazeMg.getInstance();
			
			//Log.i("ludroid", "source pos : " + sourceEvt.getX() + " / " + sourceEvt.getY());
			//Log.i("ludroid", "dest pos : " + destEvt.getX() + " / " + destEvt.getY());
			//Log.i("ludroid","distanceY = " + distanceY);
			if (crossedWall)
				return false;
			Coordinates sourceCell = new Coordinates((int)sourceEvt.getX(), (int)sourceEvt.getY());
			Coordinates convSrc = CoordinatesConverter.convert(sourceCell, stepX, stepY);
			if (mg.getCurrentPlayerPosition().equals(convSrc)
					|| mg.getPreviousPlayerPosition().equals(convSrc)) {
				Log.i("ludroid", "Ok pour le départ");
				Coordinates destCell = CoordinatesConverter.convert(new Coordinates((int)destEvt.getX(), (int)destEvt.getY()), stepX, stepY);
				if (!destCell.equals(sourceCell)) {
					Log.i("ludroid","Changement de case");
					if (mg.isWall(destCell)) {
						Log.i("ludroid"," droit dans le mur");
						mg.reinitPreviousPosition();
						crossedWall = true;
						return true;
					} else {
						Log.i("ludroid","c'est bon !");
						mg.handleMove(destCell);
						invalidate();
						if (destCell.equals(mg.getMaze().getEndPoint())) {
							Log.i("ludroid","Gagné");
							ttsEngine.speak(getResources().getString(R.string.generic_congratulationFound), TextToSpeech.QUEUE_FLUSH, null);
						}
						return false;
					}
				}
			}
			return false;
		};
		
		
		
		public boolean onSingleTapConfirmed(MotionEvent event) {
			
			return (false);
		};
	};

	@Override
	public void onInit(int status) {
		
	}

}
