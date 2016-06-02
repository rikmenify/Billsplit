package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.R;

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
    private LayoutInflater inflater;

    public MenuAdapter(Context contexts, int resource, RealmList<DetailPerson> objects) {
        super(contexts, resource, objects);
        persons = objects;
        context =contexts;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        holder.checkBoxMenu.setChecked(false);


        return convertView;
    }
}
