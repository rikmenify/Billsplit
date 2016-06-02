package com.example.rikirikmen.billsplit;

import android.app.Application;
import android.util.Log;

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
                .schemaVersion(1)
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(config);


    }

    public class Migration implements RealmMigration {
        @Override public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();


        }

    }

}
