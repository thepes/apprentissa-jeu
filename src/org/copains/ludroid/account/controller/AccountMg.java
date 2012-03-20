package org.copains.ludroid.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.copains.ludroid.account.objects.Account;
import org.copains.ludroid.data.controller.DatabaseMg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountMg {

    private final Context context;

    public AccountMg(Context context) {
        this.context = context;
    }

    public final List<Account> getAccounts() {
        // ?? should we put this in constructor ?
        DatabaseMg dbMg = new DatabaseMg(context);
        SQLiteDatabase db = dbMg.getReadableDatabase();
        Cursor cursor = db.query(Account.TBL_NAME, null, null, null, null,
                null, null);
        if (null == cursor) {
            return (null);
        }
        int rowCount = cursor.getCount();
        ArrayList<Account> ret = new ArrayList<Account>();
        for (int i = 0; i < rowCount; i++) {

        }
        return (ret);
    }

}
