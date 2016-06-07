package com.example.rikirikmen.billsplit.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rikirikmen on 4/6/2016.
 */
public class Bill extends RealmObject {


    @PrimaryKey private int Bill_ID;
    public RealmList<DetailMenu> detailmenu;
    public RealmList<DetailPerson> detailperson;
    private String name;
    private int price;

    public RealmList<DetailPerson> getDetailperson() {
        return detailperson;
    }

    public void setDetailperson(RealmList<DetailPerson> detailperson) {
        this.detailperson = detailperson;
    }

    public RealmList<DetailMenu> getDetailmenu() {
        return detailmenu;
    }

    public void setDetailmenu(RealmList<DetailMenu> detailmenu) {
        this.detailmenu = detailmenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { this.price = price; }

    public int getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(int bill_ID) {
        Bill_ID = bill_ID;
    }

}
