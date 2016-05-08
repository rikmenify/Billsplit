package com.example.rikirikmen.billsplit.Model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by rikirikmen on 4/24/2016.
 */
public class DetailMenu extends RealmObject {

    @PrimaryKey private int MenuID;
    private int Quantity;
    private String BillID;
    private String MenuName;
    private int MenuPrice;

    public int getMenuPrice() {
        return MenuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        MenuPrice = menuPrice;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
