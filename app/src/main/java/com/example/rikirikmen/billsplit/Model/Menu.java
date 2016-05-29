package com.example.rikirikmen.billsplit.Model;

/**
 * Created by Riki Rikmen on 5/26/2016.
 */

public class Menu {

    boolean selected = false;
    private String MenuID;
    private String PersonID;

    public Menu(String MenuID, String PersonID, boolean selected) {
        super();
        this.MenuID = MenuID;
        this.PersonID = PersonID;
        this.selected = selected;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }
}