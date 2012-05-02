package org.copains.ludroid.games.findnumbers.views;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.copains.ludroid.R;
import org.copains.ludroid.games.findnumbers.controller.FindNumberMg;
import org.copains.ludroid.tts.controller.TextToSpeechMg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FindNumberView extends View implements OnInitListener {

	private TextToSpeech ttsEngine = null;
	private Paint paint;
	private Random rnd;
	
	public FindNumberView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FindNumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FindNumberView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		if (null == ttsEngine) {
			ttsEngine = new TextToSpeech(getContext(), this);
		}
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		rnd = new Random();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		FindNumberMg numberMg = FindNumberMg.getInstance();
		if (!numberMg.isGameStarted()) {
			numberMg.initGame(canvas.getWidth(), canvas.getHeight());
			ttsEngine.speak(getResources().getString(R.string.findNumber_question) + numberMg.getNumberToFind(), TextToSpeech.QUEUE_ADD, null);
		}
		paint.setTextSize(numberMg.getTextSize());
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		RectF rf = new RectF(numberMg.getReservedRectForHelp());
		canvas.drawRoundRect(rf, 10, 10, paint);
		paint.setTextAlign(Align.CENTER);
		FontMetricsInt fmi = paint.getFontMetricsInt();
		canvas.drawText("?", numberMg.getReservedRectForHelp().right / 2,numberMg.getReservedRectForHelp().bottom-(fmi.bottom/2), paint);
		paint.setTextAlign(Align.LEFT);
		List<Rect> numbers = numberMg.getPlacedNumbers();
		int i = 0;
		for (Rect r : numbers) {
			paint.setARGB(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
			canvas.drawText(""+i, r.left, r.bottom, paint);
			i++;
		}
	}
	
	/** 
	 * Ne vérifie le click que sur l'action UP
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getActionMasked() != MotionEvent.ACTION_DOWN) {
			return (false);
		}
		FindNumberMg numberMg = FindNumberMg.getInstance();
		if (numberMg.isHelpClicked((int)event.getX(), (int)event.getY()))
		{
			Log.i("ludroid", "HELP");
			ttsEngine.speak(
					getResources().getString(R.string.findNumber_question) + numberMg.getNumberToFind(),
	                TextToSpeech.QUEUE_FLUSH, null);
			return (true);
		}
		if (numberMg.checkClick(numberMg.getNumberToFind(), (int)event.getX(), (int)event.getY())) {
			Log.i("ludroid", "OK Gagné");
			ttsEngine.speak(
	                getResources().getString(R.string.generic_congratulationFound),
	                TextToSpeech.QUEUE_FLUSH, null);
			numberMg.setGameFinished(true);
			invalidate();
		} else {
			ttsEngine.speak(
					getResources().getString(R.string.generic_notTheGoodOne),
	                TextToSpeech.QUEUE_FLUSH, null);
			Log.i("ludroid","perdu : x=" + event.getX() + " y="+event.getY());
		}
		return (true);
	}

	@Override
	public void onInit(int status) {
		//ttsEngine.setLanguage(Locale.FRENCH);
	}

}
