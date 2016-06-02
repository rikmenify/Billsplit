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

import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import io.realm.Realm;
import io.realm.RealmResults;

public class NewBill extends AppCompatActivity {

    private EditText textfieldBill;
    private EditText textfieldPeople;
    private int Qty;
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
            addnewBill(String.valueOf(textfieldBill.getText()), 0, Integer.valueOf(textfieldPeople.getText().toString()));
            finish();

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

    private void addnewBill(final String Name, final int Price, final int Qty){
//        realm.beginTransaction();
//
//        realm.commitTransaction();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Bill bill = realm.createObject(Bill.class);
                bill.setBill_ID("Testing" + Name.toString());
                bill.setName(Name);
                bill.setPrice(Price);
                for (int i = 0; i < Qty ; i++ ) {

                    if (i == 0) {
                        DetailPerson person = realm.createObject(DetailPerson.class);
                        person.setPersonID(getNextKey());
                        person.setPersonName("Me");
                        person.setPersonPrice(0);
                        bill.detailperson.add(person);

                    }
                    else
                    {
                        DetailPerson person = realm.createObject(DetailPerson.class);
                        person.setPersonID(getNextKey());
                        int a = i+1;
                        person.setPersonName("Person " + a);
                        person.setPersonPrice(0);
                        bill.detailperson.add(person);

                    }
                }
                }
        });





    }

    public int getNextKey()
    {
        return realm.where(DetailPerson.class).max("PersonID").intValue() + 1;
    }

}
