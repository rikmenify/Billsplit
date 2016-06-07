package com.example.rikirikmen.billsplit.Model;

/**
 * Created by Riki Rikmen on 6/5/2016.
 */

public class PersonMenuObj {
    private int PersonID;
    private boolean Status;

    public PersonMenuObj() {
        super();
    }

    public PersonMenuObj(int personID, boolean status) {

        PersonID = personID;
        Status = status;
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
