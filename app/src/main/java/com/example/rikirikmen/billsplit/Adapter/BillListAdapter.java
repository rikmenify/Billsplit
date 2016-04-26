package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Detail_Bill;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by rikirikmen on 4/8/2016.
 */

public class BillListAdapter extends BaseAdapter{

    private RealmResults<Bill> bill;
    Context context;
    Realm realm;
    private static LayoutInflater inflater=null;
    public BillListAdapter(Context context, RealmResults<Bill> billRealmResults) {
        // TODO Auto-generated constructor stub
        bill = billRealmResults;
        realm = Realm.getDefaultInstance();
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
        Button btnDeleteListbill;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_bill, parent,false );
        holder.textViewbill=(TextView) rowView.findViewById(R.id.itemlist_namabill);
        holder.textViewprice=(TextView) rowView.findViewById(R.id.itemlist_pricebill);
        holder.btnDeleteListbill=(Button) rowView.findViewById(R.id.btnDeleteListBill);
        holder.textViewbill.setText(bill.get(position).getName());
        holder.textViewprice.setText(bill.get(position).getPrice().toString());
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailBill = new Intent(context, Detail_Bill.class);
                detailBill.putExtra("bill_ID", bill.get(position).getBill_ID());
                detailBill.putExtra("bill_Name", bill.get(position).getName());
                detailBill.putExtra("bill_Price", bill.get(position).getPrice());
                context.startActivity(detailBill);
            }
        });

        holder.btnDeleteListbill.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                bill.remove(position);
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });

        return rowView;
    }

}