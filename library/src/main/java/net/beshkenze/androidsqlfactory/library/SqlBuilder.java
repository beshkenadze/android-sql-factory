package net.beshkenze.androidsqlfactory.library;

import net.beshkenze.androidsqlfactory.library.helper.From;
import net.beshkenze.androidsqlfactory.library.helper.Join;
import net.beshkenze.androidsqlfactory.library.helper.Limit;
import net.beshkenze.androidsqlfactory.library.helper.Select;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class SqlBuilder {
    private Select mSelect = new Select();
    private From mFrom = new From();
    private ArrayList<Join> mJoins = new ArrayList<Join>();
    private Limit mLimit = new Limit();

    public SqlBuilder(String tableName) {
        getFrom().setTable(tableName);
    }

    public SqlBuilder() {

    }

    public SqlBuilder select() {
        HashMap<String, String> fields = new HashMap<String, String>();
        getSelect().setFields(fields);
        return SqlBuilder.this;
    }

    public SqlBuilder select(String... fields) {
        if (fields.length == 2) {
            getSelect().addField(fields[0], fields[1]);
        } else {
            for (int i = 0; i < fields.length; i++) {
                getSelect().addField(fields[i]);
            }
        }
        return SqlBuilder.this;
    }

    public SqlBuilder from(String tableName) {
        getFrom().setTable(tableName);
        return SqlBuilder.this;
    }

    public SqlBuilder join(String type, String tableName, String tableField, String targetField) {
        addJoin(new Join(type, tableName, tableField, targetField));
        return SqlBuilder.this;
    }

    public SqlBuilder join(String type, String tableName[], String tableField, String targetField) {
        addJoin(new Join(type, tableName, tableField, targetField));
        return SqlBuilder.this;
    }

    public SqlBuilder leftJoin(String tableName, String tableField, String targetField) {
        return join(Join.LEFT, tableName, tableField, targetField);
    }

    public SqlBuilder leftJoin(String tableName[], String tableField, String targetField) {
        return join(Join.LEFT, tableName, tableField, targetField);
    }

    public SqlBuilder innerJoin(String tableName, String tableField, String targetField) {
        return join(Join.INNER, tableName, tableField, targetField);
    }

    public SqlBuilder innerJoin(String tableName[], String tableField, String targetField) {
        return join(Join.INNER, tableName, tableField, targetField);
    }

    public SqlBuilder where() {


        return null;
    }

    public void save() {

    }

    public Select getSelect() {
        return mSelect;
    }

    public void setSelect(Select select) {
        mSelect = select;
    }

    public From getFrom() {
        return mFrom;
    }

    public void setFrom(From from) {
        mFrom = from;
    }

    public ArrayList<Join> getJoins() {
        return mJoins;
    }

    public void setJoins(ArrayList<Join> joins) {
        mJoins = joins;
    }

    public void addJoin(Join join) {
        mJoins.add(join);
    }

    public String getJoin() {
        StringBuilder sql = new StringBuilder();
        for (Join join : getJoins()) {
            sql.append(join.toSql());
        }
        return sql.toString();
    }

    public Limit getLimit() {
        return mLimit;
    }

    public void setLimit(Limit limit) {
        mLimit = limit;
    }

    public void find() {
        Timber.tag("SQL");
        Timber.i("%s", toSql());
    }

    public void one() {
        setLimit(new Limit("1"));
        Timber.tag("SQL");
        Timber.i("%s", toSql());
    }

    public String toSql() {
        return getSelect().toSql() + getFrom().toSql() + getJoin() + getLimit().toSql().trim();
    }

}
