package com.example.rikirikmen.billsplit.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Riki Rikmen on 6/5/2016.
 */

public class PersonInMenu extends RealmObject {

    @PrimaryKey
    private int PersonID;
    private boolean Status;

    public PersonInMenu() {
        super();
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int personID) {
        PersonID = personID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
