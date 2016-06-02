package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.example.rikirikmen.billsplit.Adapter.BillDetailListAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailBill extends Fragment {

    Realm realm;
    private RealmList<DetailMenu> menu;
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
        menu = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst().getDetailmenu();
        if (menu.isEmpty()){

        }
        else {
            adapter = new BillDetailListAdapter(this.getActivity(), menu);
            setupListener();
            menu.first().addChangeListener(realmMenuChangeListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            recyclerView.setAdapter(adapter);
        }

        return view;

    }

    private  void setupListener(){

        realmMenuChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                menu.sort("MenuID", Sort.ASCENDING);
                adapter.notifyDataSetChanged();
            }
        };
    }


}
