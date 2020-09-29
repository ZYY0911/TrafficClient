package com.mad.trafficclient.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mad.trafficclient.bean.Czjl;

import java.sql.SQLException;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:52
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    public DbHelper(Context context) {
        super(context, "databaseName.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Czjl.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
