package org.copains.ludroid.games.findshape.views;

import java.util.Random;

import org.copains.ludroid.games.findshape.shapes.TriangleShape;
import org.copains.ludroid.games.tools.InGameHelpMg;
import org.copains.tools.games.SquarePlacementMg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.AttributeSet;
import android.view.View;

public class FindShapeView extends View implements OnInitListener {

	private TextToSpeech ttsEngine = null;
	private Paint paint;
	private Random rnd;
	private InGameHelpMg inGameHelp;
	private SquarePlacementMg squarePlacementMg;
	private ShapeDrawable triangleDrawable;
	private boolean gameStarted = false;
	
	public FindShapeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FindShapeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FindShapeView(Context context) {
		super(context);
		init();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setARGB(255, 255, 0, 0);
		triangleDrawable.draw(canvas);
	}
	
	private void init() {
		if (null == ttsEngine) {
			ttsEngine = new TextToSpeech(getContext(), this);
		}
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		rnd = new Random();
		inGameHelp = InGameHelpMg.getInstance();
		squarePlacementMg = SquarePlacementMg.getInstance();
		triangleDrawable = new ShapeDrawable(new TriangleShape());
		triangleDrawable.setBounds(100,100,200,200);
		triangleDrawable.getPaint().setARGB(255, 255, 0, 0);
	}

	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
		
	}


}
