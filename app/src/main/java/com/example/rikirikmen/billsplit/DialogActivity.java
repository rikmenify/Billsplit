package com.example.rikirikmen.billsplit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Adapter.MenuAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DialogActivity extends AppCompatActivity {
    Realm realm;
    private RealmList<DetailPerson> person;
    private ListView listView;
    private MenuAdapter adapter;
    private String bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_menu);
        bill = getIntent().getStringExtra("bill_ID");
        realm = Realm.getDefaultInstance();
        listView = (ListView) findViewById(R.id.listViewMenu);
        person = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst().getDetailperson();
        adapter = new MenuAdapter(this,R.layout.row_menu, person);
        listView.setAdapter(adapter);



    }
}
