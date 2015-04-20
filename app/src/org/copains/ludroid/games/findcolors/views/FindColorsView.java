package org.copains.ludroid.games.findcolors.views;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.copains.ludroid.R;
import org.copains.ludroid.games.findcolors.controller.FindColorsMg;
import org.copains.ludroid.games.tools.InGameHelpMg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FindColorsView extends View implements OnInitListener {

	private TextToSpeech ttsEngine = null;
	private Paint paint;
	private Hashtable<Integer , Rect> placedColors = null;
	private List<Integer> colors = null;
	private InGameHelpMg inGameHelp = null;
	private FindColorsMg findColorsMg;
	
	public FindColorsView(Context context) {
		super(context);
		if (null == ttsEngine) {
			init();
		}
	}
	
	public FindColorsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (null == ttsEngine) {
			init();
		}
	}

	public FindColorsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (null == ttsEngine) {
			init();
		}
	}


	
	/**
	 * initialize the needed objects for this view
	 */
	private void init() {
		findColorsMg = FindColorsMg.getInstance();
		ttsEngine = new TextToSpeech(getContext(), this);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		inGameHelp = InGameHelpMg.getInstance();
		//paint.setAlpha(255);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i("ludroid","dans onDraw findColors");
		inGameHelp.drawHelpButton(canvas, paint);
		if (!findColorsMg.isGameStarted()) {
			findColorsMg.initGame(canvas.getWidth(), canvas.getHeight());
			placedColors  = findColorsMg.getPlacedSquares();
			colors = Collections.list(placedColors.keys());
			ttsEngine.speak(getResources().getString(R.string.findColor_question) 
					+ getResources().getString(findColorsMg.getColorNameToFind()), TextToSpeech.QUEUE_ADD, null);
		}
		for (Integer color : colors) {
			Integer realColor = findColorsMg.getColorCodes().get(color);
			paint.setColor(realColor);
			paint.setAlpha(255);
			paint.setStyle(Style.FILL_AND_STROKE);
			canvas.drawRect(findColorsMg.getPlacedSquares().get(color), paint);
		}
	}
	
	/** 
	 * Ne v�rifie le click que sur l'action UP
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getActionMasked() != MotionEvent.ACTION_DOWN) {
			return (false);
		}
		if (inGameHelp.isHelpClicked((int)event.getX(), (int)event.getY())) {
			ttsEngine.speak(getResources().getString(R.string.findColor_question) 
					+ getResources().getString(findColorsMg.getColorNameToFind()), TextToSpeech.QUEUE_ADD, null);
			return true;
		}
		if (findColorsMg.checkClick((int)event.getX(), (int)event.getY())) {
			Log.i("ludroid", "OK Gagn�");
			ttsEngine.speak(
	                getResources().getString(R.string.generic_congratulationFound),
	                TextToSpeech.QUEUE_FLUSH, null);
			findColorsMg.setGameFinished(true);
			invalidate();
		} else {
			ttsEngine.speak(
					getResources().getString(R.string.generic_notTheGoodOne),
	                TextToSpeech.QUEUE_FLUSH, null);
			Log.i("ludroid","perdu : x=" + event.getX() + " y="+event.getY());
		}
		return true;
	}

	/**
	 * ttsEngine initialisation
	 */
	@Override
	public void onInit(int arg0) {
		//TODO: handle error for ttsengine initialisation
		
	}

}
