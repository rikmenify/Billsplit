package com.example.rikirikmen.billsplit;

import android.app.Application;

import com.example.rikirikmen.billsplit.Model.Bill;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.internal.Table;
import io.realm.internal.TableSpec;

/**
 * Created by rikirikmen on 4/6/2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .schemaVersion(4)
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public class Migration implements RealmMigration {
        @Override public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();

            if (oldVersion == 3) {
//                RealmObjectSchema newSchema = schema.get("Bill");
//                newSchema.addField("Menu", int.class);
//                newSchema.addField("Person", int.class);
//
//                RealmObjectSchema DetailMenu = schema.create("DetailMenu");
//                DetailMenu.addField("MenuID", int.class, FieldAttribute.PRIMARY_KEY);
//                DetailMenu.addField("Quantity", int.class);
//                DetailMenu.addField("BillID", int.class);
//                DetailMenu.addField("MenuName", String.class);
//                DetailMenu.addField("MenuPrice", int.class);
//
//                RealmObjectSchema DetailPerson = schema.create("DetailPerson");
//                DetailPerson.addField("PersonID", int.class, FieldAttribute.PRIMARY_KEY);
//                DetailPerson.addField("PersonQty", int.class);
//                DetailPerson.addField("PersonName", String.class);
//                DetailPerson.addField("PersonPrice", int.class);

                oldVersion++;


            }
            if (oldVersion == 4) {
                oldVersion++;
            }

        }

    }

}
