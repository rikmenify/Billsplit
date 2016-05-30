package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.rikirikmen.billsplit.Model.Bill;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
import com.example.rikirikmen.billsplit.Model.DetailPerson;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmObject>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmObject>> modelClasses = new HashSet<Class<? extends RealmObject>>();
        modelClasses.add(DetailMenu.class);
        modelClasses.add(DetailPerson.class);
        modelClasses.add(Bill.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return DetailMenuRealmProxy.initTable(transaction);
        } else if (clazz.equals(DetailPerson.class)) {
            return DetailPersonRealmProxy.initTable(transaction);
        } else if (clazz.equals(Bill.class)) {
            return BillRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return DetailMenuRealmProxy.validateTable(transaction);
        } else if (clazz.equals(DetailPerson.class)) {
            return DetailPersonRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Bill.class)) {
            return BillRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return DetailMenuRealmProxy.getFieldNames();
        } else if (clazz.equals(DetailPerson.class)) {
            return DetailPersonRealmProxy.getFieldNames();
        } else if (clazz.equals(Bill.class)) {
            return BillRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return DetailMenuRealmProxy.getTableName();
        } else if (clazz.equals(DetailPerson.class)) {
            return DetailPersonRealmProxy.getTableName();
        } else if (clazz.equals(Bill.class)) {
            return BillRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return clazz.cast(new DetailMenuRealmProxy(columnInfo));
        } else if (clazz.equals(DetailPerson.class)) {
            return clazz.cast(new DetailPersonRealmProxy(columnInfo));
        } else if (clazz.equals(Bill.class)) {
            return clazz.cast(new BillRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmObject>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmObject> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmObject, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(DetailMenu.class)) {
            return clazz.cast(DetailMenuRealmProxy.copyOrUpdate(realm, (DetailMenu) obj, update, cache));
        } else if (clazz.equals(DetailPerson.class)) {
            return clazz.cast(DetailPersonRealmProxy.copyOrUpdate(realm, (DetailPerson) obj, update, cache));
        } else if (clazz.equals(Bill.class)) {
            return clazz.cast(BillRealmProxy.copyOrUpdate(realm, (Bill) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return clazz.cast(DetailMenuRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(DetailPerson.class)) {
            return clazz.cast(DetailPersonRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Bill.class)) {
            return clazz.cast(BillRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(DetailMenu.class)) {
            return clazz.cast(DetailMenuRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(DetailPerson.class)) {
            return clazz.cast(DetailPersonRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Bill.class)) {
            return clazz.cast(BillRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmObject, RealmObjectProxy.CacheData<RealmObject>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(DetailMenu.class)) {
            return clazz.cast(DetailMenuRealmProxy.createDetachedCopy((DetailMenu) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(DetailPerson.class)) {
            return clazz.cast(DetailPersonRealmProxy.createDetachedCopy((DetailPerson) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Bill.class)) {
            return clazz.cast(BillRealmProxy.createDetachedCopy((Bill) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
