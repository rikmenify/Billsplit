package com.example.rikirikmen.billsplit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Model.Menu;
import com.example.rikirikmen.billsplit.R;

import java.util.ArrayList;

/**
 * Created by Riki Rikmen on 5/26/2016.
 */

public class ChecklistAdapter extends ArrayAdapter<Menu> {

    private ArrayList<Menu> MenuList;
    Context context;

    public ChecklistAdapter(Context context, int textViewResourceId,
                            ArrayList<Menu> MenuList) {
        super(context, textViewResourceId, MenuList);
        this.MenuList = new ArrayList<Menu>();
        this.MenuList.addAll(MenuList);
    }

    private class ViewHolder {
        TextView MenuID;
        CheckBox PersonID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
//            convertView = vi.inflate(R.layout.country_info, null);

            holder = new ViewHolder();
            //           holder.MenuID = (TextView) convertView.findViewById(R.id.code);
//           holder.PersonID = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(holder);

            holder.PersonID.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Menu menuList = (Menu) cb.getTag();
                    Toast.makeText(context.getApplicationContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    menuList.setSelected(cb.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu menu = MenuList.get(position);
        holder.MenuID.setText(" (" +  menu.getMenuID() + ")");
        holder.PersonID.setText(menu.getPersonID());
        holder.PersonID.setChecked(menu.isSelected());
        holder.PersonID.setTag(menu);

        return convertView;

    }

}

//    private void checkButtonClick() {
//
//
//        Button myButton = (Button) findViewById(R.id.findSelected);
//        myButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                StringBuffer responseText = new StringBuffer();
//                responseText.append("The following were selected...\n");
//
//                ArrayList<Country> countryList = dataAdapter.countryList;
//                for(int i=0;i<countryList.size();i++){
//                    Country country = countryList.get(i);
//                    if(country.isSelected()){
//                        responseText.append("\n" + country.getName());
//                    }
//                }
//
//                Toast.makeText(getApplicationContext(),
//                        responseText, Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }

//}
