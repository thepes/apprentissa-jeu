package org.copains.ludroid.games.findnumbers;

import org.copains.ludroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FindNumberActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_number);
	}
	
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i("ludroid","Dans Ludroid FindNumber On Pause");
    }


}
