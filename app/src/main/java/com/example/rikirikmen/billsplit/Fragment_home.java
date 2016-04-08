package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rikirikmen.billsplit.Adapter.BillAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home extends Fragment implements BillAdapter.BillAdapterListener {


    private Realm realm;
    private RealmResults<Bill> billRealmResults;
    private RecyclerView recyclerView;
    private BillAdapter adapter;
    private TextView txtTest;

    public Fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View home_view = inflater.inflate(R.layout.fragment_home, container, false);
        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) home_view.findViewById(R.id.recyclerView);
        txtTest = (TextView) home_view.findViewById(R.id.textViewTest);
        setupRecylerView();
        return home_view;


    }

    private void setupRecylerView() {
        billRealmResults = realm.where(Bill.class).findAll();
        billRealmResults.sort("Bill_ID", Sort.DESCENDING);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new BillAdapter(this.getActivity(), billRealmResults, this);
        recyclerView.setAdapter(adapter);
    }






    @Override
    public void onItemClick(Bill bill) {

    }
}