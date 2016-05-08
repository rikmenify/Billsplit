package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.rikirikmen.billsplit.Model.DetailPerson;
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

public class DetailPersonRealmProxy extends DetailPerson
    implements RealmObjectProxy {

    static final class DetailPersonColumnInfo extends ColumnInfo {

        public final long PersonIDIndex;
        public final long BillIDIndex;
        public final long PersonQtyIndex;
        public final long PersonNameIndex;
        public final long PersonPriceIndex;

        DetailPersonColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.PersonIDIndex = getValidColumnIndex(path, table, "DetailPerson", "PersonID");
            indicesMap.put("PersonID", this.PersonIDIndex);

            this.BillIDIndex = getValidColumnIndex(path, table, "DetailPerson", "BillID");
            indicesMap.put("BillID", this.BillIDIndex);

            this.PersonQtyIndex = getValidColumnIndex(path, table, "DetailPerson", "PersonQty");
            indicesMap.put("PersonQty", this.PersonQtyIndex);

            this.PersonNameIndex = getValidColumnIndex(path, table, "DetailPerson", "PersonName");
            indicesMap.put("PersonName", this.PersonNameIndex);

            this.PersonPriceIndex = getValidColumnIndex(path, table, "DetailPerson", "PersonPrice");
            indicesMap.put("PersonPrice", this.PersonPriceIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DetailPersonColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("PersonID");
        fieldNames.add("BillID");
        fieldNames.add("PersonQty");
        fieldNames.add("PersonName");
        fieldNames.add("PersonPrice");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DetailPersonRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DetailPersonColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public int getPersonID() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.PersonIDIndex);
    }

    @Override
    public void setPersonID(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.PersonIDIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getBillID() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.BillIDIndex);
    }

    @Override
    public void setBillID(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.BillIDIndex);
            return;
        }
        row.setString(columnInfo.BillIDIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getPersonQty() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.PersonQtyIndex);
    }

    @Override
    public void setPersonQty(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.PersonQtyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPersonName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.PersonNameIndex);
    }

    @Override
    public void setPersonName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.PersonNameIndex);
            return;
        }
        row.setString(columnInfo.PersonNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getPersonPrice() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.PersonPriceIndex);
    }

    @Override
    public void setPersonPrice(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.PersonPriceIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_DetailPerson")) {
            Table table = transaction.getTable("class_DetailPerson");
            table.addColumn(RealmFieldType.INTEGER, "PersonID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "BillID", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "PersonQty", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "PersonName", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "PersonPrice", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("PersonID"));
            table.setPrimaryKey("PersonID");
            return table;
        }
        return transaction.getTable("class_DetailPerson");
    }

    public static DetailPersonColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_DetailPerson")) {
            Table table = transaction.getTable("class_DetailPerson");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DetailPersonColumnInfo columnInfo = new DetailPersonColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("PersonID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'PersonID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("PersonID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'PersonID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.PersonIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'PersonID' does support null values in the existing Realm file. Use corresponding boxed type for field 'PersonID' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("PersonID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'PersonID' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("PersonID"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'PersonID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("BillID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'BillID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("BillID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'BillID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.BillIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'BillID' is required. Either set @Required to field 'BillID' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("PersonQty")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'PersonQty' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("PersonQty") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'PersonQty' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.PersonQtyIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'PersonQty' does support null values in the existing Realm file. Use corresponding boxed type for field 'PersonQty' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("PersonName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'PersonName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("PersonName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'PersonName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.PersonNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'PersonName' is required. Either set @Required to field 'PersonName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("PersonPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'PersonPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("PersonPrice") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'PersonPrice' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.PersonPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'PersonPrice' does support null values in the existing Realm file. Use corresponding boxed type for field 'PersonPrice' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The DetailPerson class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_DetailPerson";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static DetailPerson createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        DetailPerson obj = null;
        if (update) {
            Table table = realm.getTable(DetailPerson.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("PersonID")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("PersonID"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new DetailPersonRealmProxy(realm.schema.getColumnInfo(DetailPerson.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("PersonID")) {
                if (json.isNull("PersonID")) {
                    obj = realm.createObject(DetailPerson.class, null);
                } else {
                    obj = realm.createObject(DetailPerson.class, json.getInt("PersonID"));
                }
            } else {
                obj = realm.createObject(DetailPerson.class);
            }
        }
        if (json.has("PersonID")) {
            if (json.isNull("PersonID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field PersonID to null.");
            } else {
                obj.setPersonID((int) json.getInt("PersonID"));
            }
        }
        if (json.has("BillID")) {
            if (json.isNull("BillID")) {
                obj.setBillID(null);
            } else {
                obj.setBillID((String) json.getString("BillID"));
            }
        }
        if (json.has("PersonQty")) {
            if (json.isNull("PersonQty")) {
                throw new IllegalArgumentException("Trying to set non-nullable field PersonQty to null.");
            } else {
                obj.setPersonQty((int) json.getInt("PersonQty"));
            }
        }
        if (json.has("PersonName")) {
            if (json.isNull("PersonName")) {
                obj.setPersonName(null);
            } else {
                obj.setPersonName((String) json.getString("PersonName"));
            }
        }
        if (json.has("PersonPrice")) {
            if (json.isNull("PersonPrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field PersonPrice to null.");
            } else {
                obj.setPersonPrice((int) json.getInt("PersonPrice"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static DetailPerson createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        DetailPerson obj = realm.createObject(DetailPerson.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("PersonID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field PersonID to null.");
                } else {
                    obj.setPersonID((int) reader.nextInt());
                }
            } else if (name.equals("BillID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setBillID(null);
                } else {
                    obj.setBillID((String) reader.nextString());
                }
            } else if (name.equals("PersonQty")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field PersonQty to null.");
                } else {
                    obj.setPersonQty((int) reader.nextInt());
                }
            } else if (name.equals("PersonName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPersonName(null);
                } else {
                    obj.setPersonName((String) reader.nextString());
                }
            } else if (name.equals("PersonPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field PersonPrice to null.");
                } else {
                    obj.setPersonPrice((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static DetailPerson copyOrUpdate(Realm realm, DetailPerson object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        DetailPerson realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(DetailPerson.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getPersonID());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new DetailPersonRealmProxy(realm.schema.getColumnInfo(DetailPerson.class));
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

    public static DetailPerson copy(Realm realm, DetailPerson newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        DetailPerson realmObject = realm.createObject(DetailPerson.class, newObject.getPersonID());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setPersonID(newObject.getPersonID());
        realmObject.setBillID(newObject.getBillID());
        realmObject.setPersonQty(newObject.getPersonQty());
        realmObject.setPersonName(newObject.getPersonName());
        realmObject.setPersonPrice(newObject.getPersonPrice());
        return realmObject;
    }

    public static DetailPerson createDetachedCopy(DetailPerson realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<DetailPerson> cachedObject = (CacheData) cache.get(realmObject);
        DetailPerson standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new DetailPerson();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setPersonID(realmObject.getPersonID());
        standaloneObject.setBillID(realmObject.getBillID());
        standaloneObject.setPersonQty(realmObject.getPersonQty());
        standaloneObject.setPersonName(realmObject.getPersonName());
        standaloneObject.setPersonPrice(realmObject.getPersonPrice());
        return standaloneObject;
    }

    static DetailPerson update(Realm realm, DetailPerson realmObject, DetailPerson newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setBillID(newObject.getBillID());
        realmObject.setPersonQty(newObject.getPersonQty());
        realmObject.setPersonName(newObject.getPersonName());
        realmObject.setPersonPrice(newObject.getPersonPrice());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DetailPerson = [");
        stringBuilder.append("{PersonID:");
        stringBuilder.append(getPersonID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{BillID:");
        stringBuilder.append(getBillID() != null ? getBillID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{PersonQty:");
        stringBuilder.append(getPersonQty());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{PersonName:");
        stringBuilder.append(getPersonName() != null ? getPersonName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{PersonPrice:");
        stringBuilder.append(getPersonPrice());
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
        DetailPersonRealmProxy aDetailPerson = (DetailPersonRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aDetailPerson.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aDetailPerson.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aDetailPerson.row.getIndex()) return false;

        return true;
    }

}
