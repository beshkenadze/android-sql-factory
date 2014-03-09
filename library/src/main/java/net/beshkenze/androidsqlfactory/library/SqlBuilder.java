package net.beshkenze.androidsqlfactory.library;

import android.database.DatabaseUtils;
import net.beshkenze.androidsqlfactory.library.helper.*;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class SqlBuilder {
    private Select mSelect = new Select();
    private From mFrom = new From();
    private Join mJoin = new Join();
    private Limit mLimit = new Limit();
    private Where mWhere = new Where();

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

    public SqlBuilder count(String field, String pseudo) {
        return select("count(" + field + ")", pseudo);
    }

    public SqlBuilder from(String tableName) {
        getFrom().setTable(tableName);
        return SqlBuilder.this;
    }

    public SqlBuilder join(String type, String tableName, String tableField, String targetField) {
        getJoin().add(type, tableName, tableField, targetField);
        return SqlBuilder.this;
    }

    public SqlBuilder join(String type, String tableName[], String tableField, String targetField) {
        getJoin().add(type, tableName, tableField, targetField);
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

    public SqlBuilder where(String name, String exp, String value) {
        switch (exp) {
            default:
                getWhere().addCondition(name + " " + exp + " " + DatabaseUtils.sqlEscapeString(value));
                break;

        }
        return SqlBuilder.this;
    }


    public SqlBuilder eq(String name, String value) {
        where(name, Where.EQUAL, value);
        return SqlBuilder.this;
    }

    public SqlBuilder ne(String name, String value) {
        where(name, Where.NOT_EQUAL, value);
        return SqlBuilder.this;
    }

    public SqlBuilder like(String name, String value) {
        where(name, Where.LIKE, value);
        return SqlBuilder.this;
    }

    public SqlBuilder notLike(String name, String value) {
        where(name, Where.NOT_LIKE, value);
        return SqlBuilder.this;
    }

    public SqlBuilder is(String name, String value) {
        like(name, value);
        return SqlBuilder.this;
    }

    public SqlBuilder isNot(String name, String value) {
        notLike(name, value);
        return SqlBuilder.this;
    }

    public SqlBuilder notContains(String name, String value) {
        where(name, Where.NOT_LIKE, "%".concat(value).concat("%"));
        return SqlBuilder.this;
    }

    public SqlBuilder contains(String name, String value) {
        where(name, Where.LIKE, "%".concat(value).concat("%"));
        return SqlBuilder.this;
    }

    public SqlBuilder leftContains(String name, String value) {
        where(name, Where.LIKE, value.concat("%"));
        return SqlBuilder.this;
    }

    public SqlBuilder rightContains(String name, String value) {
        where(name, Where.LIKE, "%".concat(value));
        return SqlBuilder.this;
    }

    public SqlBuilder gt(String name, String value) {
        where(name, Where.GREATER_THAN, value);
        return SqlBuilder.this;
    }

    public SqlBuilder gte(String name, String value) {
        where(name, Where.GREATER_THAN_EQUAL, value);
        return SqlBuilder.this;
    }

    public SqlBuilder lt(String name, String value) {
        where(name, Where.LESS_THAN, value);
        return SqlBuilder.this;
    }

    public SqlBuilder lte(String name, String value) {
        where(name, Where.LESS_THAN_EQUAL, value);
        return SqlBuilder.this;
    }


//    public SqlBuilder in(String name, String[] strings) {
////        TODO
//        return SqlBuilder.this;
//    }

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

    public Join getJoin() {
        return mJoin;
    }

    public void setJoin(Join join) {
        mJoin = join;
    }

    public Where getWhere() {
        return mWhere;
    }

    public void setWhere(Where where) {
        mWhere = where;
    }

    public Limit getLimit() {
        return mLimit;
    }

    public void setLimit(Limit limit) {
        mLimit = limit;
    }

    public String find() {
        return toSql();
    }

    public String one() {
        setLimit(new Limit("1"));
        return toSql();
    }

    public String toSql() {
        return getSelect().toSql() + getFrom().toSql() + getJoin().toSql() + getWhere().toSql() + getLimit().toSql().trim();
    }
}
