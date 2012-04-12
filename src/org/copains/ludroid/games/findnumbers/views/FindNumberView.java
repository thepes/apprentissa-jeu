package org.copains.ludroid.games.findnumbers.views;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.copains.ludroid.games.findnumbers.controller.FindNumberMg;
import org.copains.ludroid.tts.controller.TextToSpeechMg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FindNumberView extends View implements OnInitListener {

	private TextToSpeech ttsEngine = null;
	
	public FindNumberView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}

	public FindNumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public FindNumberView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		if (null == ttsEngine) {
			ttsEngine = new TextToSpeech(getContext(), this);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int sizeX, sizeY;
		
		sizeX = canvas.getWidth();
		sizeY = canvas.getHeight();
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Random rnd = new Random();
		
		FindNumberMg numberMg = FindNumberMg.getInstance();
		if (!numberMg.isGameStarted()) {
			numberMg.initGame(canvas.getWidth(), canvas.getHeight());
		}
		paint.setTextSize(numberMg.getTextSize());
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
		if (numberMg.checkClick(2, (int)event.getX(), (int)event.getY())) {
			Log.i("ludroid", "OK Gagné");
			ttsEngine.speak(
	                "Bravo tu as trouvé !",
	                TextToSpeech.QUEUE_FLUSH, null);
		} else {
			Log.i("ludroid","perdu : x=" + event.getX() + " y="+event.getY());
		}
		return (true);
	}

	@Override
	public void onInit(int status) {
		ttsEngine.setLanguage(Locale.FRENCH);
	}

}
