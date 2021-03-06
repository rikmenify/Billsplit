package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.Model.PersonInMenu;
import com.example.rikirikmen.billsplit.Model.PersonMenuObj;
import com.example.rikirikmen.billsplit.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Riki Rikmen on 6/1/2016.
 */
public class MenuAdapter extends ArrayAdapter<DetailPerson>{
    private RealmList<DetailPerson> persons;
    private Context context;
    Realm realm;
    List<PersonMenuObj> personMenuObjList;
    private LayoutInflater inflater;

    public MenuAdapter(Context contexts, int resource, RealmList<DetailPerson> objects) {
        super(contexts, resource, objects);
        persons = objects;
        realm = Realm.getDefaultInstance();
        personMenuObjList = new ArrayList<>();
        context =contexts;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        populatePim();

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    private class ViewHolder {
        TextView menuItem;
        CheckBox checkBoxMenu;
    }

    public void populatePim(){
        for(int i = 0; i<getCount(); i++){
            PersonMenuObj personMenuObj = new PersonMenuObj(persons.get(i).getPersonID(), false);
            Log.i("personID",""+ persons.get(i).getPersonID());
            personMenuObjList.add(personMenuObj);
        }
    };

    public List<PersonMenuObj> getPersonMenuObjList() {
        return personMenuObjList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_menu, null);

            holder = new ViewHolder();
            holder.menuItem = (TextView) convertView.findViewById(R.id.textViewMenuItem);
            holder.checkBoxMenu = (CheckBox) convertView.findViewById(R.id.checkBoxMenu);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.menuItem.setText(" (" +  persons.get(position).getPersonID() + ")");
        holder.checkBoxMenu.setText(persons.get(position).getPersonName());

        holder.checkBoxMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.i("onCHeck", ""+ position);
                    personMenuObjList.get(position).setStatus(true);

                }
                else {
                    personMenuObjList.get(position).setStatus(false);
                }
            }
        });

        return convertView;
    }




}
