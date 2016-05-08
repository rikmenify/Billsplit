package com.example.rikirikmen.billsplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArchive extends Fragment {

//    Realm realm;
//    private RealmResults<Bill> billRealmResults;
    private ListView listView;
    public FragmentArchive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_archive, container, false);
//        realm = Realm.getDefaultInstance();
//        billRealmResults = realm.where(Bill.class).findAll();
//        billRealmResults.sort("Bill_ID", Sort.DESCENDING);
        listView=(ListView) view.findViewById(R.id.listViewBillArchive);
//        listView.setAdapter(new BillListAdapter(this.getActivity(), billRealmResults));
        return view;

    }

}
