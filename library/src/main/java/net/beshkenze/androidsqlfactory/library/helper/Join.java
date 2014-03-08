package net.beshkenze.androidsqlfactory.library.helper;

import android.text.TextUtils;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Join {
    public static final String LEFT = "LEFT";
    public static final String INNER = "INNER";
    private final String mType;
    private final String mTableName;
    private final String mTableField;
    private final String mTargetField;

    public Join(String type, String tableName, String tableField, String targetField) {
        mType = type;
        mTableName = tableName;
        mTableField = tableField;
        mTargetField = targetField;
    }

    public Join(String type, String tableName[], String tableField, String targetField) {
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
