package com.example.rikirikmen.billsplit.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.R;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static java.lang.String.valueOf;

/**
 * Created by rikirikmen on 4/8/2016.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.Holder>{

    private RealmList<DetailPerson> Person;
    private Context context;
    Realm realm;
    private static LayoutInflater inflater=null;

    public PersonAdapter(Context context, RealmList<DetailPerson> billRealmResults) {
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
        RealmResults<DetailMenu> personSize = realm.where(DetailMenu.class).equalTo("personInMenus.PersonID", Person.get(position).getPersonID()).equalTo("personInMenus.Status", false).findAll();

        int a= personSize.size();
        holder.textViewPrice.setText(String.valueOf(a));
        // Delete
        holder.btnDeleteListPerson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        context);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure want to delete the person?");
                alert.setPositiveButton(R.string.alert_dialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.beginTransaction();
                        Person.remove(position);
                        realm.commitTransaction();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });


                alert.setNegativeButton(R.string.alert_dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                alert.show();

            }
        });

        holder.btnEditListPerson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    final View dialogView = inflater.inflate(R.layout.dialog_edit_person, null);
                    dialogBuilder.setView(dialogView);

                    final EditText txtEditPerson = (EditText) dialogView.findViewById(R.id.txtEditPerson);

                    dialogBuilder.setTitle("Edit " + Person.get(position).getPersonName());
                    dialogBuilder.setMessage(R.string.txt_update_person);
                    dialogBuilder.setPositiveButton(R.string.txt_btn_oke_menu, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            realm.beginTransaction();
                            Person.get(position).setPersonName(valueOf(txtEditPerson.getText()));
                            realm.commitTransaction();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialogBuilder.setNegativeButton(R.string.txt_btn_cancel_menu, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog b = dialogBuilder.create();
                    b.show();

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
        Button btnEditListPerson;
        CardView containerCard;
        public Holder(View itemview){

            super(itemview);
            containerCard=(CardView) itemview.findViewById(R.id.containerCardPerson);
            textViewPerson=(TextView) itemview.findViewById(R.id.itemlistNamaPerson);
            textViewPrice=(TextView) itemview.findViewById(R.id.itemlistPricePerson);
            btnDeleteListPerson=(Button) itemview.findViewById(R.id.btnDeleteListPerson);
            btnEditListPerson=(Button) itemview.findViewById(R.id.btnEditListPerson);
        }
    }

}