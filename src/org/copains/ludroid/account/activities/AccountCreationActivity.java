package org.copains.ludroid.account.activities;

import org.copains.ludroid.R;
import org.copains.ludroid.account.objects.Account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AccountCreationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_creation);
	}

	public void onCreateAccountClick(View v) {
			// creation du profil utilisateur
			Account acc = new Account();
			EditText firstName = (EditText) findViewById(R.id.playerFirstnameInput);
			acc.setFirstName(firstName.getText().toString());
			DatePicker dt = (DatePicker) findViewById(R.id.playerBirthdateInput);
			String birthdate = dt.getDayOfMonth() + "/" + dt.getMonth() + "/" + dt.getYear(); 
			acc.setBirthDate(birthdate);
			RadioButton girlBtn = (RadioButton) findViewById(R.id.playerGirl);
			if (girlBtn.isChecked()) {
				
			}
	}
	
}
