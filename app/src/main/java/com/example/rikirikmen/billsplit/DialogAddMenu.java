package com.example.rikirikmen.billsplit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikirikmen.billsplit.Adapter.MenuAdapter;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
import com.example.rikirikmen.billsplit.Model.PersonInMenu;

import io.realm.Realm;
import io.realm.RealmList;

public class DialogAddMenu extends AppCompatActivity {
    Realm realm;
    private RealmList<DetailPerson> person;
    private RealmList<PersonInMenu> pim;
    private ListView listView;
    private MenuAdapter adapter;
    private TextView menuName;
    private TextView menuPrice;
    private TextView menuQty;
    private int bill;
    private int MenuID;
    private Button btnOke;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_menu);
        realm = Realm.getDefaultInstance();
        bill = getIntent().getIntExtra("bill_ID",-1);
        btnOke = (Button) findViewById(R.id.btnOkMenu);
        btnCancel = (Button) findViewById(R.id.btnCancelMenu);
        menuName = (TextView) findViewById(R.id.txtMenuName);
        menuPrice = (TextView) findViewById(R.id.txtMenuPrice);
        menuQty = (TextView) findViewById(R.id.txtMenuQuantity);
        listView = (ListView) findViewById(R.id.listViewMenu);

        setTitle(R.string.add_new_menu);
        menuName.setHint(R.string.txt_menu_name_hint);
        menuQty.setHint(R.string.txt_menu_qty_hint);
        menuPrice.setHint(R.string.txt_menu_price_hint);
        MenuID = getNextKey();


        person = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst().getDetailperson();

            if (person.isEmpty()){


            }
            else{

                adapter = new MenuAdapter(this,R.layout.row_menu, person);
                listView.setAdapter(adapter);
            }

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Jumlah", ""+adapter.getPersonMenuObjList().size());
                for (int i = 0; i < adapter.getPersonMenuObjList().size(); i++) {

                    Log.i("Object ", adapter.getPersonMenuObjList().get(i).getPersonID()+" "+adapter.getPersonMenuObjList().get(i).isStatus() );

                }
                if (menuName.getText().length()==0 || menuPrice.getText().length()==0|| menuQty.getText().length()==0){
                    Toast.makeText(DialogAddMenu.this, "Please fill the menu detail", Toast.LENGTH_SHORT).show();

                }
                else {
                    CharSequence priceText = menuPrice.getText();
                    final int Price = Integer.parseInt(priceText.toString());

                    CharSequence qtyText = menuQty.getText();
                    final int Qty = Integer.parseInt(qtyText.toString());

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bill updateBill = realm.where(Bill.class).equalTo("Bill_ID", bill).findFirst();
                            DetailMenu menu = new DetailMenu();
                            RealmList<PersonInMenu> personInMenus = new RealmList<>();
                            menu.personInMenus = personInMenus;
                            menu.setMenuID(MenuID);
                            menu.setMenuName(String.valueOf(menuName.getText()));
                            menu.setMenuPrice(Price);
                            menu.setQuantity(Qty);
                            int currentTotal = Price * Qty;
                            int grandTotal = updateBill.getPrice();
                            grandTotal = grandTotal + currentTotal;
                            updateBill.setPrice(grandTotal);
                            for (int i = 0; i < adapter.getPersonMenuObjList().size(); i++) {
                                PersonInMenu pim = new PersonInMenu();
                                pim.setPersonID(adapter.getPersonMenuObjList().get(i).getPersonID());
                                pim.setStatus(adapter.getPersonMenuObjList().get(i).isStatus());
                                menu.personInMenus.add(pim);

                            }

                            updateBill.detailmenu.add(menu);
                            realm.copyToRealmOrUpdate(updateBill);
                        }
                    });


                finish();

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
