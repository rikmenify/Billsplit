package com.example.rikirikmen.billsplit.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Database1.db";
    public static final String BILL_TABLE_NAME = "bill_table";
    public static final String BILL_COLUMN_ID_Bill = "id_bill";
    public static final String BILL_COLUMN_Nama_Bill = "name";
    public static final String BILL_COLUMN_Total_Bill = "total";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE `bill_table` (" +
                        "'id_bill'	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "'name' Text, " +
                        "'total' INTEGER );"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bill_table");
        onCreate(db);
    }


    public Cursor getData(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bill_table where id_bill="+id+"", null );
        return res;
    }

    public Integer deleteBill (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("bill_table)",
                "id_bill = ? ",
                new String[] { Integer.toString(id) });
    }


    public boolean insertBill (String name, int total)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("total", total);
        db.insert("bill_table", null, contentValues);
        return true;
    }

    public ArrayList<String> getAllBill() {
        ArrayList<String> array_list = null;
        try {

            array_list = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from bill_table", null);
            if (res != null) {
                do {
                    array_list.add(res.getString(res.getColumnIndex(BILL_COLUMN_Nama_Bill)));

                } while (res.moveToNext());
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
        ;

        return array_list;
    }
}