package net.beshkenze.androidsqlfactory.library.helper;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Join {
    public static final String LEFT = "LEFT";
    public static final String INNER = "INNER";

    private ArrayList<RowJoin> mRowJoin = new ArrayList<>();

    public void add(String type, String tableName, String tableField, String targetField) {
        mRowJoin.add(new RowJoin(type, tableName, tableField, targetField));
    }

    public void add(String type, String[] tableName, String tableField, String targetField) {
        mRowJoin.add(new RowJoin(type, tableName, tableField, targetField));
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder();
        for (RowJoin join : mRowJoin) {
            sql.append(join.toSql());
        }
        return sql.toString();
    }

    @Override
    public String toString() {
        return toSql();
    }

    private class RowJoin {
        private final String mType;
        private final String mTableName;
        private final String mTableField;
        private final String mTargetField;

        public RowJoin(String type, String tableName, String tableField, String targetField) {
            mType = type;
            mTableName = tableName;
            mTableField = tableField;
            mTargetField = targetField;
        }

        public RowJoin(String type, String tableName[], String tableField, String targetField) {
            mType = type;
            if (tableName.length > 1) {
                mTableName = TextUtils.join(" as ", tableName);
            } else if (tableName.length == 1) {
                mTableName = tableName[0];
            } else {
                mTableName = null;
            }
            mTableField = tableField;
            mTargetField = targetField;
        }

        public String toSql() {
            if (TextUtils.isEmpty(mType) || TextUtils.isEmpty(mTableName)
                    || TextUtils.isEmpty(mTableField) || TextUtils.isEmpty(mTargetField)) {
                return "";
            }
            return mType + " " + "JOIN" + " " + mTableName + " " + "ON" + " " + "( " + mTableField + " = " + mTargetField + " )" + " ";
        }
    }
}
