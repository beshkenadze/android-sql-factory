package net.beshkenze.androidsqlfactory.library.helper;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 09.03.14.
 */
public class Where {
    public static final String EQUAL = "=";
    public static final String NOT_EQUAL = "!" + EQUAL;
    public static final String LIKE = "LIKE";
    public static final String NOT_LIKE = "NOT " + LIKE;
    public static final String GREATER_THAN = ">";
    public static final String GREATER_THAN_EQUAL = ">" + EQUAL;
    public static final String LESS_THAN = "<";
    public static final String LESS_THAN_EQUAL = "<" + EQUAL;
    public static final String CONTAINS = "contains";
    public static final String NOT_CONTAINS = "not_contains";
    public static final String IN = "IN";
    public static final String NOT_IN = "NOT " + IN;


    private ArrayList<String> mConditions = new ArrayList<>();

    public ArrayList<String> getConditions() {
        return mConditions;
    }

    public void setConditions(ArrayList<String> conditions) {
        mConditions = conditions;
    }

    public void addCondition(String condition) {
        mConditions.add(condition);
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder("WHERE");
        sql.append(" ");
        int count = 0;
        for (String condition : mConditions) {
            count++;
            sql.append(condition);

            if (count < mConditions.size()) {
                sql.append(" AND ");
            }
        }
        sql.append(" ");
        return sql.toString();
    }
}
