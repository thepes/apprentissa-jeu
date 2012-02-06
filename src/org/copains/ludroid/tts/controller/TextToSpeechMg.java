package org.copains.ludroid.tts.controller;

import android.content.Context;

public class TextToSpeechMg {
	
	private static TextToSpeechMg instance = null;
	
	/**
	 * private const for singleton
	 */
	private TextToSpeechMg() {
		
	}
	
	private TextToSpeechMg(Context context) {
		
	}

	/**
	 * returns the singleton.
	 * @param context the BaseContext
	 * @return the instance
	 */
	public static TextToSpeechMg getInstance(Context context) {
		return (null);
	}
	
}
