package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Adapter.PersonAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerson extends Fragment {

    Realm realm;
    private RealmResults<DetailPerson> personRealmResults;
    private RecyclerView recyclerView;
    private RealmChangeListener realmPersonChangeListener;
    private PersonAdapter adapter;
    private String bill;
    public FragmentPerson() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        realm = Realm.getDefaultInstance();

        Bundle bundle = getArguments();
        if (bundle != null) {
            bill = getArguments().getString("bill_ID");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPerson);
        personRealmResults = realm.where(DetailPerson.class).equalTo("BillID", bill).findAllAsync();
        adapter = new PersonAdapter(this.getActivity(), personRealmResults);
        setupListener();
        personRealmResults.addChangeListener(realmPersonChangeListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        return view;

    }


    private  void setupListener(){
        realmPersonChangeListener = new RealmChangeListener() {
            @Override public void onChange() {
                personRealmResults.sort("PersonName", Sort.ASCENDING);
                adapter.notifyDataSetChanged();
            }
        };

    }


}
