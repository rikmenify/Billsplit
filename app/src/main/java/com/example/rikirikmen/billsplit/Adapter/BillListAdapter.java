package com.example.rikirikmen.billsplit.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.Holder>{

    private RealmResults<Bill> bill;
    private Context context;
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
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.list_bill_card, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder,final int position) {
        holder.textViewbill.setText(bill.get(position).getName());
//        holder.textViewprice.setText(bill.get(position).getPrice());

        holder.containerCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailBill = new Intent(context, Detail_Bill.class);
                detailBill.putExtra("bill_ID", bill.get(position).getBill_ID());
                detailBill.putExtra("bill_Name", bill.get(position).getName());
//                detailBill.putExtra("bill_Price", bill.get(position).getPrice());
                context.startActivity(detailBill);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bill.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textViewbill;
        TextView textViewprice;
        Button btnDeleteListbill;
        CardView containerCard;
        public Holder(View itemview){

            super(itemview);
            containerCard=(CardView) itemview.findViewById(R.id.container_card);
            textViewbill=(TextView) itemview.findViewById(R.id.itemlist_namabill);
            textViewprice=(TextView) itemview.findViewById(R.id.itemlist_pricebill);
            btnDeleteListbill=(Button) itemview.findViewById(R.id.btnDeleteListBill);
        }
    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        Holder holder=new Holder();
//        View rowView;
//        rowView = inflater.inflate(R.layout.list_bill, parent,false );
//
//
//        holder.textViewbill=(TextView) rowView.findViewById(R.id.itemlist_namabill);
//        holder.textViewprice=(TextView) rowView.findViewById(R.id.itemlist_pricebill);
//        holder.btnDeleteListbill=(Button) rowView.findViewById(R.id.btnDeleteListBill);
//
//        holder.textViewbill.setText(bill.get(position).getName());
//        holder.textViewprice.setText(bill.get(position).getPrice());
//
//
//
//        //Lempar Parameter ke detail bill
//        rowView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent detailBill = new Intent(context, Detail_Bill.class);
//                detailBill.putExtra("bill_ID", bill.get(position).getBill_ID());
////                detailBill.putExtra("bill_Name", bill.get(position).getName());
////                detailBill.putExtra("bill_Price", bill.get(position).getPrice());
//                context.startActivity(detailBill);
//            }
//        });
//
//
//        //Delete record bill
//        holder.btnDeleteListbill.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(
//                        context);
//                alert.setTitle("Delete");
//                alert.setMessage("Are you sure want to delete the bill?");
//                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        realm.beginTransaction();
//                        bill.remove(position);
//                        realm.commitTransaction();
//                        notifyDataSetChanged();
//                        dialog.dismiss();
//                    }
//                });
//
//
//                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    }
//                });
//
//
//                alert.show();
//
//            }
//        });
//
//        return rowView;
//    }

}