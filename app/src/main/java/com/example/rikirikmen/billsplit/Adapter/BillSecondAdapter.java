package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.R;

import io.realm.RealmResults;

/**
 * Created by rikirikmen on 4/8/2016.
 */

public class BillSecondAdapter extends BaseAdapter{

    private RealmResults<Bill> bill;
    Context context;
    private static LayoutInflater inflater=null;
    public BillSecondAdapter(Context context, RealmResults<Bill> billRealmResults) {
        // TODO Auto-generated constructor stub
        bill = billRealmResults;
        this.context= context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return bill.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textViewbill;
        TextView textViewprice;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listbill, parent,false );
        holder.textViewbill=(TextView) rowView.findViewById(R.id.itemlist_namabill);
        holder.textViewprice=(TextView) rowView.findViewById(R.id.itemlist_pricebill);
        holder.textViewbill.setText(bill.get(position).getName());
        holder.textViewprice.setText(bill.get(position).getPrice().toString());
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+bill.get(position), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}