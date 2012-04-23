package org.copains.ludroid.games.findcolors.views;

import android.content.Context;
import android.graphics.Canvas;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;

public class FindColorsView extends View implements OnInitListener {

	private TextToSpeech ttsEngine;
	
	public FindColorsView(Context context) {
		super(context);
		
	}
	
	/**
	 * initialize the needed objects for this view
	 */
	private void init() {
		ttsEngine = new TextToSpeech(getContext(), this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
	}

	/**
	 * ttsEngine initialisation
	 */
	@Override
	public void onInit(int arg0) {
		//TODO: handle error for ttsengine initialisation
		
	}

}
