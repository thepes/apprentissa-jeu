package org.copains.ludroid;

import java.util.Locale;

import org.copains.ludroid.account.activities.AccountCreationActivity;
import org.copains.ludroid.account.controller.AccountMg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LudroidMainActivity extends Activity implements OnInitListener {

    private static final int CHECK_TTS_DATA = 100;

    private TextToSpeech ttsEngine = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // init();
        ttsEngine = new TextToSpeech(this, this);
        Button b = (Button) findViewById(R.id.buttonNewPlayer);
        b.setOnClickListener(newPlayerClick);
        // for test only
        AccountMg accMg = new AccountMg(getBaseContext());
        accMg.getAccounts();
    }

    /**
     * listener for the new player action
     */
    private final OnClickListener newPlayerClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // v.getContext().startActivity(intent);
            Intent accoutIntent = new Intent(v.getContext(),
                    AccountCreationActivity.class);
            v.getContext().startActivity(accoutIntent);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHECK_TTS_DATA) {
            Log.i("Ludroid", "resultCode = " + resultCode);
            String[] voiceInfo = data
                    .getStringArrayExtra(TextToSpeech.Engine.EXTRA_VOICE_DATA_FILES_INFO);
            if (null != voiceInfo) {
                for (int i = 0; i < voiceInfo.length; i++) {
                    Log.d("ludroid", "Voice INFO : " + voiceInfo[i]);

                }
            }
            switch (resultCode) {
            case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
                Intent installTts = new Intent(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTts);
                break;
            case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
                Log.i("ludroid", "TTS data OK");
                ttsEngine = new TextToSpeech(this, this);
                break;
            default:
                Log.i("ludroid", "unhandled code : " + requestCode);
            }
        }
    }

    /**
     * initialize all needed stuff for the application
     */
    private void init() {
        // TTS initialization
        Intent checkIntent = new Intent(
                TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, CHECK_TTS_DATA);
    }

    @Override
    public void onInit(int arg0) {
        // TODO Auto-generated method stub
        ttsEngine.setLanguage(Locale.FRENCH);
        ttsEngine.speak(
                "Bonjour amour de ma vie ! Je t'aime de tout mon coeur !",
                TextToSpeech.QUEUE_FLUSH, null);
    }

}
