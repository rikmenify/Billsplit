package com.example.rikirikmen.billsplit;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.rikirikmen.billsplit.Adapter.PersonAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmQuery;
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
    private RealmList<DetailPerson> persons;
    private PersonAdapter adapter;
    private int bill;
    private String person_add_name;
    public FragmentPerson() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bill = getArguments().getInt("bill_ID");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPerson);
        persons = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst().getDetailperson();
        adapter = new PersonAdapter(this.getActivity(), persons);
        setupListener();
        persons.first().addChangeListener(realmPersonChangeListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_person, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add_person) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle(R.string.add_new_person);

            final EditText input = new EditText(this.getActivity());
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    person_add_name = input.getText().toString();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bill bill_update = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst();
                            DetailPerson person = new DetailPerson();
                            person.setPersonID(getNextKey());
                            person.setPersonName(person_add_name);
                            person.setPersonPrice(0);
                            bill_update.detailperson.add(person);
                        }
                    });

                dialog.dismiss();
                }

            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private  void setupListener(){
        realmPersonChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object element) {
                persons.sort("PersonID", Sort.ASCENDING);
                adapter.notifyDataSetChanged();
            }
        };

    }

    public int getNextKey()
    {
        Number max = realm.where(DetailPerson.class).max("PersonID");
        return (max != null) ? max.intValue() + 1 : 0;
    }
}
