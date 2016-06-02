package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.R;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by rikirikmen on 4/24/2016.
 */
public class BillDetailListAdapter extends RecyclerView.Adapter<BillDetailListAdapter.Holder>{

    private RealmList<DetailMenu> Menu;
    private Context context;
    Realm realm;
    private static LayoutInflater inflater=null;


    public BillDetailListAdapter(Context context, RealmList<DetailMenu> menuRealmResults) {
        // TODO Auto-generated constructor stub
        Menu = menuRealmResults;
        realm = Realm.getDefaultInstance();
        this.context= context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.list_menu_card, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder,final int position) {

        if (Menu.size() ==0){
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();

        }
        else if (Menu.size() >1){
            holder.textViewMenu.setText(Menu.get(position).getMenuName());


        }
    }

    @Override
    public int getItemCount() {
        return Menu.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textViewMenu;
        CardView containerCard;
        Button btnDeleteListMenu;
        Button btnEditListMenu;
        public Holder(View itemview){

            super(itemview);
            containerCard=(CardView) itemview.findViewById(R.id.containerCardMenu);
            textViewMenu=(TextView) itemview.findViewById(R.id.itemlistMenuName);
            btnDeleteListMenu=(Button) itemview.findViewById(R.id.btnDeleteListMenu);
            btnEditListMenu=(Button) itemview.findViewById(R.id.btnEditListMenu);
        }
    }

}
