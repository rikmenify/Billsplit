package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.R;

import io.realm.Realm;
import io.realm.RealmResults;
/**
 * Created by rikirikmen on 4/8/2016.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.Holder>{

    private RealmResults<DetailPerson> Person;
    private Context context;
    Realm realm;
    private static LayoutInflater inflater=null;


    public PersonAdapter(Context context, RealmResults<DetailPerson> billRealmResults) {
        // TODO Auto-generated constructor stub
        Person = billRealmResults;
        realm = Realm.getDefaultInstance();
        this.context= context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.list_person_card, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder,final int position) {
        holder.textViewPerson.setText(Person.get(position).getPersonName());

        holder.containerCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return Person.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textViewPerson;
        TextView textViewPrice;
        Button btnDeleteListPerson;
        CardView containerCard;
        public Holder(View itemview){

            super(itemview);
            containerCard=(CardView) itemview.findViewById(R.id.containerCardPerson);
            textViewPerson=(TextView) itemview.findViewById(R.id.itemlistNamaPerson);
            textViewPrice=(TextView) itemview.findViewById(R.id.itemlistPricePerson);
            btnDeleteListPerson=(Button) itemview.findViewById(R.id.btnDeleteListPerson);
        }
    }

}