package com.example.rikirikmen.billsplit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.rikirikmen.billsplit.Adapter.BillDetailListAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
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
    private int bill;

    public FragmentDetailBill() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail_bill, container, false);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bill = getArguments().getInt("bill_ID");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_plus_detail) {
            Intent dialogAddMenu = new Intent(this.getContext(), DialogAddMenu.class);
            dialogAddMenu.putExtra("bill_ID", bill);
            startActivity(dialogAddMenu);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_bill, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
