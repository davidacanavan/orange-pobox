package com.orange.pobox.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    public Database(Context context)
    {
     super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db)
    {
     db.execSQL(StoreLocation.getTableCreationString());
     StoreLocation.create(db, "United States", "New York", "New York City", "105 W 86TH ST", "NY 10024", (int) (40.786781 * 1E6), (int) (-73.972435 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "366 AMSTERDAM AVE", "NY 10024", (int) (40.782163 * 1E6), (int) (-73.979171 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "119 W 72ND ST", "NY 10023", (int) (40.777857 * 1E6), (int) (-73.979461 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1202 LEXINGTON AVE", "NY 10028-1425", (int) (40.776682 * 1E6), (int) (-73.957791 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1324 LEXINGTON AVE", "NY 10128-1145", (int) (40.78103 * 1E6), (int) (-73.954466 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1474 3rd Ave", "NY 10028", (int) (40.777152 * 1E6), (int) (-73.955444 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "2576 BROADWAY", "NY 10025-5657", (int) (40.795419 * 1E6), (int) (-73.970967 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1636 3RD AVE", "NY 10128-3622", (int) (40.782413 * 1E6), (int) (-73.95145 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1397 2ND AVE", " NY 10021", (int) (40.769328 * 1E6), (int) (-73.958162 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1838 2ND AVE", "NY 10128", (int) (40.783506 * 1E6), (int) (-73.947578 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1562 FIRST AVE", "NY 10028-4004", (int) (40.773674 * 1E6), (int) (-73.951171 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "2753 BROADWAY", "NY 10025", (int) (40.801232 * 1E6), (int) (-73.96817 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "331 W 57TH ST", "NY 10019-3101", (int) (40.767376 * 1E6), (int) (-73.984424 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "326 E 65TH ST", "NY 10065", (int) (40.76387 * 1E6), (int) (-73.960632 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1173A SECOND AVE", "NY 10065", (int) (40.762385 * 1E6), (int) (-73.963247 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "888-C 8TH AVE", "NY 10019", (int) (40.763869 * 1E6), (int) (-73.984766 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "55 W 116TH ST", " NY 10026", (int) (40.800794 * 1E6), (int) (-73.946764 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "603 W 115TH ST", "NY 10025-7816", (int) (40.807576 * 1E6), (int) (-73.964945 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1040 1ST AVE", "NY 10022-2902", (int) (40.758154 * 1E6), (int) (-73.963068 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "694 10th Avenue", "NY 10019", (int) (40.763703 * 1E6), (int) (-73.992308 * 1E6));
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
    
 private static final String DB_NAME = "ups";
}
