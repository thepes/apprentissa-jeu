package org.copains.ludroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class LudroidMainActivity extends Activity {
	
	private static final int CHECK_TTS_DATA	= 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.main);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == CHECK_TTS_DATA) {
    		Log.i("Ludroid","resultCode = " + resultCode);
    		switch (resultCode) {
    			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
    				Intent installTts = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
    				startActivity(installTts);
    				break;
				default:
					Log.i("ludroid","unhandled code : " + requestCode);
    		}
    	}
    }
    
    /**
     * initialize all needed stuff for the application
     */
    private void init() {
    	// TTS initialization
    	Intent checkIntent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
    	startActivityForResult(checkIntent, CHECK_TTS_DATA);
    }
    
}