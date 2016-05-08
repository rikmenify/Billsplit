package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.rikirikmen.billsplit.Model.DetailMenu;
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

public class DetailMenuRealmProxy extends DetailMenu
    implements RealmObjectProxy {

    static final class DetailMenuColumnInfo extends ColumnInfo {

        public final long MenuIDIndex;
        public final long QuantityIndex;
        public final long BillIDIndex;
        public final long MenuNameIndex;
        public final long MenuPriceIndex;

        DetailMenuColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.MenuIDIndex = getValidColumnIndex(path, table, "DetailMenu", "MenuID");
            indicesMap.put("MenuID", this.MenuIDIndex);

            this.QuantityIndex = getValidColumnIndex(path, table, "DetailMenu", "Quantity");
            indicesMap.put("Quantity", this.QuantityIndex);

            this.BillIDIndex = getValidColumnIndex(path, table, "DetailMenu", "BillID");
            indicesMap.put("BillID", this.BillIDIndex);

            this.MenuNameIndex = getValidColumnIndex(path, table, "DetailMenu", "MenuName");
            indicesMap.put("MenuName", this.MenuNameIndex);

            this.MenuPriceIndex = getValidColumnIndex(path, table, "DetailMenu", "MenuPrice");
            indicesMap.put("MenuPrice", this.MenuPriceIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DetailMenuColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("MenuID");
        fieldNames.add("Quantity");
        fieldNames.add("BillID");
        fieldNames.add("MenuName");
        fieldNames.add("MenuPrice");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DetailMenuRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DetailMenuColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public int getMenuID() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.MenuIDIndex);
    }

    @Override
    public void setMenuID(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.MenuIDIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getQuantity() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.QuantityIndex);
    }

    @Override
    public void setQuantity(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.QuantityIndex, value);
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
    public String getMenuName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.MenuNameIndex);
    }

    @Override
    public void setMenuName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.MenuNameIndex);
            return;
        }
        row.setString(columnInfo.MenuNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getMenuPrice() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.MenuPriceIndex);
    }

    @Override
    public void setMenuPrice(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.MenuPriceIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_DetailMenu")) {
            Table table = transaction.getTable("class_DetailMenu");
            table.addColumn(RealmFieldType.INTEGER, "MenuID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "Quantity", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "BillID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "MenuName", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "MenuPrice", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("MenuID"));
            table.setPrimaryKey("MenuID");
            return table;
        }
        return transaction.getTable("class_DetailMenu");
    }

    public static DetailMenuColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_DetailMenu")) {
            Table table = transaction.getTable("class_DetailMenu");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DetailMenuColumnInfo columnInfo = new DetailMenuColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("MenuID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'MenuID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("MenuID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'MenuID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.MenuIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'MenuID' does support null values in the existing Realm file. Use corresponding boxed type for field 'MenuID' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("MenuID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'MenuID' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("MenuID"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'MenuID' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("Quantity")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'Quantity' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("Quantity") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'Quantity' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.QuantityIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'Quantity' does support null values in the existing Realm file. Use corresponding boxed type for field 'Quantity' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
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
            if (!columnTypes.containsKey("MenuName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'MenuName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("MenuName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'MenuName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.MenuNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'MenuName' is required. Either set @Required to field 'MenuName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("MenuPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'MenuPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("MenuPrice") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'MenuPrice' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.MenuPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'MenuPrice' does support null values in the existing Realm file. Use corresponding boxed type for field 'MenuPrice' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The DetailMenu class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_DetailMenu";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static DetailMenu createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        DetailMenu obj = null;
        if (update) {
            Table table = realm.getTable(DetailMenu.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("MenuID")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("MenuID"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new DetailMenuRealmProxy(realm.schema.getColumnInfo(DetailMenu.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("MenuID")) {
                if (json.isNull("MenuID")) {
                    obj = realm.createObject(DetailMenu.class, null);
                } else {
                    obj = realm.createObject(DetailMenu.class, json.getInt("MenuID"));
                }
            } else {
                obj = realm.createObject(DetailMenu.class);
            }
        }
        if (json.has("MenuID")) {
            if (json.isNull("MenuID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field MenuID to null.");
            } else {
                obj.setMenuID((int) json.getInt("MenuID"));
            }
        }
        if (json.has("Quantity")) {
            if (json.isNull("Quantity")) {
                throw new IllegalArgumentException("Trying to set non-nullable field Quantity to null.");
            } else {
                obj.setQuantity((int) json.getInt("Quantity"));
            }
        }
        if (json.has("BillID")) {
            if (json.isNull("BillID")) {
                obj.setBillID(null);
            } else {
                obj.setBillID((String) json.getString("BillID"));
            }
        }
        if (json.has("MenuName")) {
            if (json.isNull("MenuName")) {
                obj.setMenuName(null);
            } else {
                obj.setMenuName((String) json.getString("MenuName"));
            }
        }
        if (json.has("MenuPrice")) {
            if (json.isNull("MenuPrice")) {
                throw new IllegalArgumentException("Trying to set non-nullable field MenuPrice to null.");
            } else {
                obj.setMenuPrice((int) json.getInt("MenuPrice"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static DetailMenu createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        DetailMenu obj = realm.createObject(DetailMenu.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("MenuID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field MenuID to null.");
                } else {
                    obj.setMenuID((int) reader.nextInt());
                }
            } else if (name.equals("Quantity")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field Quantity to null.");
                } else {
                    obj.setQuantity((int) reader.nextInt());
                }
            } else if (name.equals("BillID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setBillID(null);
                } else {
                    obj.setBillID((String) reader.nextString());
                }
            } else if (name.equals("MenuName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setMenuName(null);
                } else {
                    obj.setMenuName((String) reader.nextString());
                }
            } else if (name.equals("MenuPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field MenuPrice to null.");
                } else {
                    obj.setMenuPrice((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static DetailMenu copyOrUpdate(Realm realm, DetailMenu object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        DetailMenu realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(DetailMenu.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getMenuID());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new DetailMenuRealmProxy(realm.schema.getColumnInfo(DetailMenu.class));
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

    public static DetailMenu copy(Realm realm, DetailMenu newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        DetailMenu realmObject = realm.createObject(DetailMenu.class, newObject.getMenuID());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setMenuID(newObject.getMenuID());
        realmObject.setQuantity(newObject.getQuantity());
        realmObject.setBillID(newObject.getBillID());
        realmObject.setMenuName(newObject.getMenuName());
        realmObject.setMenuPrice(newObject.getMenuPrice());
        return realmObject;
    }

    public static DetailMenu createDetachedCopy(DetailMenu realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<DetailMenu> cachedObject = (CacheData) cache.get(realmObject);
        DetailMenu standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new DetailMenu();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setMenuID(realmObject.getMenuID());
        standaloneObject.setQuantity(realmObject.getQuantity());
        standaloneObject.setBillID(realmObject.getBillID());
        standaloneObject.setMenuName(realmObject.getMenuName());
        standaloneObject.setMenuPrice(realmObject.getMenuPrice());
        return standaloneObject;
    }

    static DetailMenu update(Realm realm, DetailMenu realmObject, DetailMenu newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setQuantity(newObject.getQuantity());
        realmObject.setBillID(newObject.getBillID());
        realmObject.setMenuName(newObject.getMenuName());
        realmObject.setMenuPrice(newObject.getMenuPrice());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DetailMenu = [");
        stringBuilder.append("{MenuID:");
        stringBuilder.append(getMenuID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{Quantity:");
        stringBuilder.append(getQuantity());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{BillID:");
        stringBuilder.append(getBillID() != null ? getBillID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{MenuName:");
        stringBuilder.append(getMenuName() != null ? getMenuName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{MenuPrice:");
        stringBuilder.append(getMenuPrice());
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
        DetailMenuRealmProxy aDetailMenu = (DetailMenuRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aDetailMenu.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aDetailMenu.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aDetailMenu.row.getIndex()) return false;

        return true;
    }

}
