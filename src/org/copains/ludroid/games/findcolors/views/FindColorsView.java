package org.copains.ludroid.games.findcolors.views;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.copains.ludroid.R;
import org.copains.ludroid.games.findcolors.controller.FindColorsMg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FindColorsView extends View implements OnInitListener {

		private TextToSpeech ttsEngine = null;
	private Paint paint;
	private Hashtable<Integer , Rect> placedColors = null;
	private List<Integer> colors = null;
	
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
		FindColorsMg findColorsMg = FindColorsMg.getInstance();
		ttsEngine = new TextToSpeech(getContext(), this);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		//paint.setAlpha(255);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		FindColorsMg findColorsMg = FindColorsMg.getInstance();
		Log.i("ludroid","dans onDraw findColors");
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
	 * ttsEngine initialisation
	 */
	@Override
	public void onInit(int arg0) {
		//TODO: handle error for ttsengine initialisation
		
	}

}
