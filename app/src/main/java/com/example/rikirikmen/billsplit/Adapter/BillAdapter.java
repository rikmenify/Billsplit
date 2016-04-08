package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rikirikmen.billsplit.Fragment_home;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.R;

import java.util.List;

import io.realm.RealmResults;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder>{

    private Context context;
    private RealmResults<Bill> billRealmResults;
    private BillAdapterListener listener;


    public interface BillAdapterListener {
        void onItemClick(Bill bill);
    }
    public BillAdapter(Context context, RealmResults<Bill> billRealmResults,
                       BillAdapterListener listener) {

        this.context = context;
        this.listener = listener;
        this.billRealmResults = billRealmResults;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.txtBill.setText(billRealmResults.get(position).getName());
        holder.txtPrice.setText(billRealmResults.get(position).getPrice());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(billRealmResults.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView container;
        public TextView txtBill;
        public TextView txtPrice;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            container = (CardView) itemLayoutView.findViewById(R.id.card_view);
            txtBill = (TextView) itemLayoutView.findViewById(R.id.txtBillName);
            txtPrice = (TextView) itemLayoutView.findViewById(R.id.txtPrice);
        }
    }
}
