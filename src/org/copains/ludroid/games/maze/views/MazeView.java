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
	private Rect newGameBtn;
	private Paint p = new Paint();

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
		int measuredHeight =  this.getMeasuredHeight();
		int measuredWidth = this.getMeasuredWidth();
		if (mg.isGameWon()) {
			int btnSize;
			btnSize = Math.min(measuredWidth, measuredHeight) / 3;
			int top = (measuredHeight / 2) - (btnSize / 2);
			int left = (measuredWidth / 2) - (btnSize / 2);
			newGameBtn = new Rect(left,top,left+btnSize,top+btnSize);
			p.setARGB(255, 0, 0, 200);
			p.setStyle(Style.FILL);
			canvas.drawRect(newGameBtn, p);
			ttsEngine.speak(getResources().getString(R.string.maze_pressBlueBtn), TextToSpeech.QUEUE_ADD, null);
			return;
		}
		if (!mg.isGameStarted()) {
			mg.initGame(cellsX, cellsY);
			ttsEngine.speak(getResources().getString(R.string.maze_startExplanation), TextToSpeech.QUEUE_ADD, null);
		}
		stepX = measuredWidth/cellsX;
		stepY = measuredHeight/cellsY;
		Maze maze = mg.getMaze();		
		p.setARGB(255, 255, 255, 255);
		p.setStyle(Style.FILL);
		byte[][] cells = maze.getMaze();
		for (int j = 0 ; j < cellsY ; j++)
			for (int i = 0 ; i < cellsX ; i++) {
				if (cells[i][j] == 0) {
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
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		
		// when the user release the screen, we reinit all scroll stuff
		if ((event.getActionMasked() & MotionEvent.ACTION_UP) == MotionEvent.ACTION_UP) {
			MazeMg mg = MazeMg.getInstance();
			mg.reinitPreviousPosition();
			crossedWall = false;
		}
		return true;
	}
	
	private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
		
		float totalX = 0, totalY = 0;
		
		@Override
		public boolean onScroll(android.view.MotionEvent sourceEvt, android.view.MotionEvent destEvt, float distanceX, float distanceY) {
			MazeMg mg = MazeMg.getInstance();
			
			if (crossedWall)
				return false;
			Coordinates sourceCell = new Coordinates((int)sourceEvt.getX(), (int)sourceEvt.getY());
			Coordinates convSrc = CoordinatesConverter.convert(sourceCell, stepX, stepY);
			//Log.d("ludroid","distances(X/Y) = " + distanceX +"/" + distanceY);
			totalX += distanceX;
			totalY += distanceY;
			Log.d("ludroid","total(X/Y) = " + totalX +"/" + totalY);
			boolean shouldMove = false;
			Coordinates currentPos = mg.getCurrentPlayerPosition();
			Coordinates destcell = null;
			if (Math.abs(totalX) > stepX) {
				Log.i("ludroid","deplacement horizontal");
				if (totalX > 0) {
					destcell = new Coordinates(currentPos.getX()-1, currentPos.getY());
				} else {
					destcell = new Coordinates(currentPos.getX()+1, currentPos.getY());					
				}
				shouldMove = true;
			}
			if (Math.abs(totalY) > stepY) {
				Log.i("ludroid","deplacement vertical");
				if (totalY > 0) {
					destcell = new Coordinates(currentPos.getX(), currentPos.getY()-1);
				} else {
					destcell = new Coordinates(currentPos.getX(), currentPos.getY()+1);					
				}
				shouldMove = true;
			}
			if (shouldMove) {
				totalX = 0;
				totalY = 0;
				if (mg.isWall(destcell)) {
					mg.reinitPreviousPosition();
					//crossedWall = true;
					return false;
				} else {
					mg.handleMove(destcell);
					invalidate();
					if (destcell.equals(mg.getMaze().getEndPoint())) {
						Log.i("ludroid","Gagné");
						mg.setGameWon(true);
						ttsEngine.speak(getResources().getString(R.string.generic_congratulationFound), TextToSpeech.QUEUE_FLUSH, null);
						invalidate();
					}
					return false;
				}
			}
			/*if (mg.getCurrentPlayerPosition().equals(convSrc)
					|| mg.getPreviousPlayerPosition().equals(convSrc)) {
				Coordinates destCell = CoordinatesConverter.convert(new Coordinates((int)destEvt.getX(), (int)destEvt.getY()), stepX, stepY);
				if (!destCell.equals(sourceCell)) {
					if (mg.isWall(destCell)) {
						mg.reinitPreviousPosition();
						crossedWall = true;
						return true;
					} else {
						mg.handleMove(destCell);
						invalidate();
						if (destCell.equals(mg.getMaze().getEndPoint())) {
							Log.i("ludroid","Gagné");
							mg.setGameWon(true);
							ttsEngine.speak(getResources().getString(R.string.generic_congratulationFound), TextToSpeech.QUEUE_FLUSH, null);
							invalidate();
						}
						return false;
					}
				}
			}*/
			return false;
		};
		
		
		
		public boolean onSingleTapConfirmed(MotionEvent event) {
			MazeMg mg = MazeMg.getInstance();
			Log.i("ludroid", "dans singletap confirmed");
			if (mg.isGameWon()) {
				if (newGameBtn.contains((int)event.getX(), (int)event.getY())) {
					mg.setGameWon(false);
					invalidate();
				}
			}
			return (false);
		};
	};

	@Override
	public void onInit(int status) {
		
	}

}
