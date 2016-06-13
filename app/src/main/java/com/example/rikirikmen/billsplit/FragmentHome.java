package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {


    Realm realm;
    private RealmResults<Bill> billRealmResults;
    private RecyclerView recyclerView;
    private RealmChangeListener realmBillChangeListener;
    private BillListAdapter adapter;
    public FragmentHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBill);
        billRealmResults = realm.where(Bill.class).findAllAsync();
        adapter = new BillListAdapter(this.getActivity(), billRealmResults);
        setupListener();
        billRealmResults.addChangeListener(realmBillChangeListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return view;



    }

    @Override
    public void onResume() {
        super.onResume();
        setupListener();
    }
    private  void setupListener(){
        realmBillChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                billRealmResults.sort("Bill_ID", Sort.ASCENDING);
                adapter.notifyDataSetChanged();
            }
        };


    }



}