package com.example.rikirikmen.billsplit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.BillListAdapter;
import com.example.rikirikmen.billsplit.Adapter.MenuAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.Model.PersonInMenu;
import com.example.rikirikmen.billsplit.Model.PersonMenuObj;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.internal.operators.OperatorTakeLast;

public class DialogActivity extends AppCompatActivity {
    Realm realm;
    private RealmList<DetailPerson> person;
    private RealmList<PersonInMenu> pim;
    private ListView listView;
    private MenuAdapter adapter;
    private TextView menuName;
    private TextView menuPrice;
    private TextView menuQty;
    private String bill;
    private int MenuID;
    private Button btnOke;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_menu);
        realm = Realm.getDefaultInstance();
        bill = getIntent().getStringExtra("bill_ID");
        btnOke = (Button) findViewById(R.id.btnOkMenu);
        btnCancel = (Button) findViewById(R.id.btnCancelMenu);
        menuName = (TextView) findViewById(R.id.txtMenuName);
        menuPrice = (TextView) findViewById(R.id.txtMenuPrice);
        menuQty = (TextView) findViewById(R.id.txtMenuQuantity);
        listView = (ListView) findViewById(R.id.listViewMenu);

        menuName.setHint(R.string.txt_menu_name_hint);
        menuQty.setHint(R.string.txt_menu_qty_hint);
        menuPrice.setHint(R.string.txt_menu_price_hint);
        MenuID = getNextKey();


        person = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst().getDetailperson();

            if (person.isEmpty()){


            }
            else{
                adapter = new MenuAdapter(this,R.layout.row_menu, person,bill);
                listView.setAdapter(adapter);
            }

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menuName.getText().length()==0 || menuPrice.getText().length()==0|| menuQty.getText().length()==0){
                    Toast.makeText(DialogActivity.this, "Please fill the menu detail", Toast.LENGTH_SHORT).show();

                }
                else {
                    CharSequence priceText = menuPrice.getText();
                    int Price = Integer.parseInt(priceText.toString());

                    CharSequence qtyText = menuQty.getText();
                    int Qty = Integer.parseInt(qtyText.toString());

                    realm.beginTransaction();
                    Bill updateBill = realm.createObject(Bill.class, bill);
                    DetailMenu menu = realm.createObject(DetailMenu.class);
                    menu.setMenuID(MenuID);
                    menu.setMenuName(String.valueOf(menuName.getText()));
                    menu.setMenuPrice(Price);
                    menu.setQuantity(Qty);
                        for (int i = 0; i < adapter.getPersonMenuObjList().size(); i++) {
                            PersonInMenu pim = realm.createObject(PersonInMenu.class);
                            pim.setPersonID(adapter.getPersonMenuObjList().get(i).getPersonID());
                            pim.setStatus(adapter.getPersonMenuObjList().get(i).isStatus());
                            menu.personInMenus.add(pim);
                        }

                    updateBill.detailmenu.add(menu);
                    realm.copyToRealmOrUpdate(updateBill);
                    realm.commitTransaction();
                    realm.close();


                }
            }
        });



    }
    public int getNextKey()
    {
        Number max = realm.where(DetailMenu.class).max("MenuID");
        return (max != null) ? max.intValue() + 1 : 0;
    }
}
