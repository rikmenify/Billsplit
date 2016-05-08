package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.rikirikmen.billsplit.Model.Bill;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillRealmProxy extends Bill
    implements RealmObjectProxy {

    static final class BillColumnInfo extends ColumnInfo {

        public final long Bill_IDIndex;
        public final long nameIndex;
        public final long priceIndex;

        BillColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.Bill_IDIndex = getValidColumnIndex(path, table, "Bill", "Bill_ID");
            indicesMap.put("Bill_ID", this.Bill_IDIndex);

            this.nameIndex = getValidColumnIndex(path, table, "Bill", "name");
            indicesMap.put("name", this.nameIndex);

            this.priceIndex = getValidColumnIndex(path, table, "Bill", "price");
            indicesMap.put("price", this.priceIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final BillColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("Bill_ID");
        fieldNames.add("name");
        fieldNames.add("price");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    BillRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (BillColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public String getBill_ID() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.Bill_IDIndex);
    }

    @Override
    public void setBill_ID(String value) {
        realm.checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field Bill_ID to null.");
        }
        row.setString(columnInfo.Bill_IDIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.nameIndex);
    }

    @Override
    public void setName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.nameIndex);
            return;
        }
        row.setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getPrice() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.priceIndex);
    }

    @Override
    public void setPrice(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.priceIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Bill")) {
            Table table = transaction.getTable("class_Bill");
            table.addColumn(RealmFieldType.STRING, "Bill_ID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "price", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("Bill_ID"));
            table.setPrimaryKey("Bill_ID");
            return table;
        }
        return transaction.getTable("class_Bill");
    }

    public static BillColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Bill")) {
            Table table = transaction.getTable("class_Bill");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final BillColumnInfo columnInfo = new BillColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("Bill_ID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'Bill_ID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("Bill_ID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'Bill_ID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.Bill_IDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'Bill_ID' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'Bill_ID' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("Bill_ID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'Bill_ID' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("Bill_ID"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'Bill_ID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("price")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'price' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("price") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'price' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.priceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'price' does support null values in the existing Realm file. Use corresponding boxed type for field 'price' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Bill class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Bill";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Bill createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Bill obj = null;
        if (update) {
            Table table = realm.getTable(Bill.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("Bill_ID")) {
                long rowIndex = table.findFirstString(pkColumnIndex, json.getString("Bill_ID"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new BillRealmProxy(realm.schema.getColumnInfo(Bill.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("Bill_ID")) {
                if (json.isNull("Bill_ID")) {
                    obj = realm.createObject(Bill.class, null);
                } else {
                    obj = realm.createObject(Bill.class, json.getString("Bill_ID"));
                }
            } else {
                obj = realm.createObject(Bill.class);
            }
        }
        if (json.has("Bill_ID")) {
            if (json.isNull("Bill_ID")) {
                obj.setBill_ID(null);
            } else {
                obj.setBill_ID((String) json.getString("Bill_ID"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                obj.setName(null);
            } else {
                obj.setName((String) json.getString("name"));
            }
        }
        if (json.has("price")) {
            if (json.isNull("price")) {
                throw new IllegalArgumentException("Trying to set non-nullable field price to null.");
            } else {
                obj.setPrice((int) json.getInt("price"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Bill createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Bill obj = realm.createObject(Bill.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("Bill_ID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setBill_ID(null);
                } else {
                    obj.setBill_ID((String) reader.nextString());
                }
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setName(null);
                } else {
                    obj.setName((String) reader.nextString());
                }
            } else if (name.equals("price")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field price to null.");
                } else {
                    obj.setPrice((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Bill copyOrUpdate(Realm realm, Bill object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        Bill realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(Bill.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (object.getBill_ID() == null) {
                throw new IllegalArgumentException("Primary key value must not be null.");
            }
            long rowIndex = table.findFirstString(pkColumnIndex, object.getBill_ID());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new BillRealmProxy(realm.schema.getColumnInfo(Bill.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static Bill copy(Realm realm, Bill newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        Bill realmObject = realm.createObject(Bill.class, newObject.getBill_ID());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setBill_ID(newObject.getBill_ID());
        realmObject.setName(newObject.getName());
        realmObject.setPrice(newObject.getPrice());
        return realmObject;
    }

    public static Bill createDetachedCopy(Bill realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<Bill> cachedObject = (CacheData) cache.get(realmObject);
        Bill standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Bill();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setBill_ID(realmObject.getBill_ID());
        standaloneObject.setName(realmObject.getName());
        standaloneObject.setPrice(realmObject.getPrice());
        return standaloneObject;
    }

    static Bill update(Realm realm, Bill realmObject, Bill newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setName(newObject.getName());
        realmObject.setPrice(newObject.getPrice());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Bill = [");
        stringBuilder.append("{Bill_ID:");
        stringBuilder.append(getBill_ID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(getName() != null ? getName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{price:");
        stringBuilder.append(getPrice());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillRealmProxy aBill = (BillRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aBill.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aBill.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aBill.row.getIndex()) return false;

        return true;
    }

}
