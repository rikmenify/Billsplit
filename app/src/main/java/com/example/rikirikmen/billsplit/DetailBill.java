package com.example.rikirikmen.billsplit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import java.text.NumberFormat;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class DetailBill extends AppCompatActivity {

    private TextView txtGrandTotal;
    private int grandTotal;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        setTitle(getIntent().getStringExtra("bill_Name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        txtGrandTotal = (TextView) findViewById(R.id.txtGrandtotal);
        getBillGrandTotal();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_detail);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_detail);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public void getBillGrandTotal(){
        grandTotal = realm.where(Bill.class).equalTo("Bill_ID", getIntent().getIntExtra("bill_ID",-1)).findFirst().getPrice();
        txtGrandTotal.setText(String.valueOf(grandTotal));
    }

    @Override
    protected void onResume() {
        getBillGrandTotal();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    FragmentDetailBill fragmentDetailBill = new FragmentDetailBill();
                    Bundle args = new Bundle();
                    args.putInt("bill_ID", getIntent().getIntExtra("bill_ID",-1));
                    fragmentDetailBill.setArguments(args);
                    return fragmentDetailBill;
                case 1:
                    FragmentPerson fragmentPerson = new FragmentPerson();
                    Bundle args_person = new Bundle();
                    args_person.putInt("bill_ID", getIntent().getIntExtra("bill_ID",-1));
                    fragmentPerson.setArguments(args_person);
                    return fragmentPerson;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "List bill";
                case 1:
                    return "People";
            }
            return null;
        }
    }


}
