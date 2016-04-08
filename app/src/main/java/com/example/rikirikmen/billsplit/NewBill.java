package com.example.rikirikmen.billsplit;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Model.Bill;

import io.realm.Realm;

public class NewBill extends AppCompatActivity {

    private EditText textfieldBill;
    private EditText textfieldPeople;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textfieldBill = (EditText) findViewById(R.id.textfieldBill);
        textfieldPeople = (EditText) findViewById(R.id.textfieldPeople);
        realm = Realm.getDefaultInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_oke) {
            addnewBill(String.valueOf(textfieldBill.getText()), 102312);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_bill, menu);
        return true;
    }

    private void addnewBill(String Name, Integer Price){
        realm.beginTransaction();
        Bill bill = realm.createObject(Bill.class);
        bill.setBill_ID("Testing" + Price.toString());
        bill.setName(Name);
        bill.setPrice(Price);
        realm.commitTransaction();
        Toast.makeText(this, "Sukses",Toast.LENGTH_LONG);
    }

}
