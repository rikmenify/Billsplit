package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.BillDetailListAdapter;
import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Adapter.PersonAdapter;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailBill extends Fragment {

    Realm realm;
    private RealmResults<DetailMenu> menuRealmResults;
    private RecyclerView recyclerView;
    private RealmChangeListener realmMenuChangeListener;
    private BillDetailListAdapter adapter;
    private String bill;

    public FragmentDetailBill() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail_bill, container, false);
        realm = Realm.getDefaultInstance();

        Bundle bundle = getArguments();
        if (bundle != null) {
            bill = getArguments().getString("bill_ID");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDetailBill);
        menuRealmResults = realm.where(DetailMenu.class).equalTo("BillID", bill).findAllAsync(); // sementara find all
        adapter = new BillDetailListAdapter(this.getActivity(), menuRealmResults);
        setupListener();
        menuRealmResults.addChangeListener(realmMenuChangeListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        return view;

    }


    private  void setupListener(){
        realmMenuChangeListener = new RealmChangeListener() {
            @Override public void onChange() {
                menuRealmResults.sort("MenuID", Sort.DESCENDING);
                adapter.notifyDataSetChanged();
            }
        };

    }


}
