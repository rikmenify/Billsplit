package com.example.rikirikmen.billsplit.Model;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rikirikmen on 4/24/2016.
 */
public class DetailPerson extends RealmObject {

    @PrimaryKey
    private int PersonID;
    private String PersonName;
    private int PersonPrice;

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public int getPersonPrice() {
        return PersonPrice;
    }

    public void setPersonPrice(int personPrice) {
        PersonPrice = personPrice;
    }


}
