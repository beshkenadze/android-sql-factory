package net.beshkenze.androidsqlfactory.library.helper;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class From {
    private String mTable;

    public String getTable() {
        return mTable;
    }

    public void setTable(String table) {
        mTable = table;
    }

    public String toSql() {
        return "FROM " + getTable() + " ";
    }
}
