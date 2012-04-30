package org.copains.ludroid.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.copains.ludroid.account.objects.Account;
import org.copains.ludroid.data.controller.DatabaseMg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountMg {

    private final Context context;

    public AccountMg(Context context) {
        this.context = context;
    }

    /**
     * returns a List of existing accounts in DB
     * 
     * @return a List of Accounts
     */
    public final List<Account> getAccounts() {
        // ?? should we put this in constructor ?
        DatabaseMg dbMg = new DatabaseMg(context);
        SQLiteDatabase db = dbMg.getReadableDatabase();
        try {
            Cursor cursor = db.query(Account.TBL_NAME, null, null, null, null,
                    null, null);
            if (null == cursor) {
                Log.i("ludroid", "Accounts cursor null");
                return (null);
            }
            int rowCount = cursor.getCount();
            Log.i("ludroid", "Accounts rowCount = " + rowCount);
            ArrayList<Account> ret = new ArrayList<Account>();
            if (rowCount > 0)
                cursor.moveToFirst();
            for (int i = 0; i < rowCount; i++) {
                Account acc = new Account(cursor.getString(0),
                        cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
                ret.add(acc);
                cursor.moveToNext();
            }
            cursor.close();
            return (ret);
        } finally {
            db.close();
        }
    }

    public final Account setAccount(Account newAccount) {
        DatabaseMg dbMg = new DatabaseMg(context);
        SQLiteDatabase db = dbMg.getWritableDatabase();
        try {
        	ContentValues cv = new ContentValues();
        	cv.put(Account.FLD_FIRSTNAME, newAccount.getFirstName());
        	cv.put(Account.FLD_BIRTHDATE, newAccount.getBirthDate());
        	cv.put(Account.FLD_CANREAD, newAccount.isCanRead());
        	cv.put(Account.FLD_SEX, newAccount.getSex());
        	long rowID = db.insert(Account.TBL_NAME, null, cv);
        	if (-1 == rowID) {
        		return (null);
        	}
        	//db.
        } finally {
            db.close();
        }
        return (null);
    }

}
