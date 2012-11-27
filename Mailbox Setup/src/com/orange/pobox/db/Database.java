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
     StoreLocation.create(db, "United States", "New York", "New York City", "105 W 86TH ST", "NY 10024", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.786781 * 1E6), (int) (-73.972435 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "366 AMSTERDAM AVE", "NY 10024", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.782163 * 1E6), (int) (-73.979171 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "119 W 72ND ST", "NY 10023", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.777857 * 1E6), (int) (-73.979461 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1202 LEXINGTON AVE", "NY 10028-1425", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.776682 * 1E6), (int) (-73.957791 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1324 LEXINGTON AVE", "NY 10128-1145", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.78103 * 1E6), (int) (-73.954466 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1474 3rd Ave", "NY 10028", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.777152 * 1E6), (int) (-73.955444 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "2576 BROADWAY", "NY 10025-5657", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.795419 * 1E6), (int) (-73.970967 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1636 3RD AVE", "NY 10128-3622", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.782413 * 1E6), (int) (-73.95145 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1397 2ND AVE", " NY 10021", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.769328 * 1E6), (int) (-73.958162 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1838 2ND AVE", "NY 10128", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.783506 * 1E6), (int) (-73.947578 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1562 FIRST AVE", "NY 10028-4004", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.773674 * 1E6), (int) (-73.951171 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "2753 BROADWAY", "NY 10025", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.801232 * 1E6), (int) (-73.96817 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "331 W 57TH ST", "NY 10019-3101", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.767376 * 1E6), (int) (-73.984424 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "326 E 65TH ST", "NY 10065", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.76387 * 1E6), (int) (-73.960632 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1173A SECOND AVE", "NY 10065", "212-202-0534", "646-253-7756", "store5979@theupsstore.com", (int) (40.762385 * 1E6), (int) (-73.963247 * 1E6));
     
     StoreLocation.create(db, "USA", "Illinois", "Chicago", "910 W VAN BUREN", "IL 60607-3523", "312-226-3333", "312-226-3335", "store3571@theupsstore.com", (int) (41.876703 * 1E6), (int) (-87.650055 * 1E6));
     StoreLocation.create(db, "USA", "Illinois", "Chicago", "27 N WACKER DR DOCK", "IL 60603", "312-372-2727", "312-372-2770", "store3878@theupsstore.com", (int) (41.87811 * 1E6), (int) (-87.62980 * 1E6));
     StoreLocation.create(db, "USA", "Illinois", "Chicago", "207 E OHIO ST", "IL 60611", "312-644-6245", "312-372-2770", "store3899@theupsstore.com", (int) (41.89243 * 1E6), (int) (-87.62196 * 1E6));
     StoreLocation.create(db, "USA", "Illinois", "Chicago", "40 E CHICAGO AVE", "IL 60611", "312-644-6245", "312-372-2770", "store3388@theupsstore.com", (int) (41.89685 * 1E6), (int) (-87.62672 * 1E6));
     StoreLocation.create(db, "USA", "Illinois", "Chicago", "917 W WASHINGTON BLVD", "IL 60607", "312-850-4616", "312-850-4612", "store3338@theupsstore.com", (int) (41.88163 * 1E6), (int) (-87.65005 * 1E6));
     
     StoreLocation.create(db, "USA", "Colorado", "Denver", "1550 LARIMER ST", "CO 80202-1602", "303-825-8060", "303-825-8056", "store1024@theupsstore.com", (int) (39.749006 * 1E6), (int) (-104.997708 * 1E6));
     StoreLocation.create(db, "USA", "Colorado", "Denver", "757 E 20TH AVE370", "CO 80205", "303-825-8000", "303-0000-8056", "store1114@theupsstore.com", (int) (39.74808 * 1E6), (int) (-104.97754 * 1E6));
     StoreLocation.create(db, "USA", "Colorado", "Denver", "303 S BROADWAY 200", "CO 80209", "303-825-8000", "303-0000-8056", "store1224@theupsstore.com", (int) (39.71109 * 1E6), (int) (-104.98756 * 1E6));
     StoreLocation.create(db, "USA", "Colorado", "Denver", "700 N COLORADO BLVD", "CO 80206", "303-825-8000", "303-0000-8056", "store2224@theupsstore.com", (int) (39.72808 * 1E6), (int) (-104.94052 * 1E6));
     StoreLocation.create(db, "USA", "Colorado", "Denver", "1685 S COLORADO BLVDUNIT S", "CO 80222", "303-825-8333", "303-0000-111", "store2224@theupsstore.com", (int) (39.68652 * 1E6), (int) (-104.94089 * 1E6));
     
     StoreLocation.create(db, "Canada", "Quebec", "Montreal", "3450 ST DENIS ST", "H2X3L3", "514-508-1444", "514-508-1445", "store459@theupsstore.ca", (int) (45.5172 * 1E6), (int) (-73.568389 * 1E6));
     StoreLocation.create(db, "Canada", "Quebec", "Montreal", "6185 boul Taschereau", "J4Z 0E4", "(450) 445-8665", "(450) 445-0685", "store381@theupsstore.ca", (int) (45.474967 * 1E6), (int) (-73.466562 * 1E6));
     StoreLocation.create(db, "Canada", "Quebec", "Montreal", "6228 St. Jacques Street West", "H4B 1T6", "(514) 733-0000", "(514) 461-1397", "store@theupsstore.ca", (int) (45.463256 * 1E6), (int) (-73.617445 * 1E6));
     StoreLocation.create(db, "Canada", "Quebec", "Montreal", "765 Beaubien St E", "H2S 1S8", "(514) 278-9119", "(514) 278-9121", "store@theupsstore.ca", (int) (42.333375 * 1E6), (int) (-83.041535 * 1E6));
     StoreLocation.create(db, "Canada", "Quebec", "Montreal", "11 - 38 Place du Commerce Ile des Soeurs", "H3E 1T8", "(514) 769-6245", "(514) 769-1286", "store@theupsstore.ca", (int) (45.461523 * 1E6), (int) (-73.546376 * 1E6));
     StoreLocation.create(db, "Canada", "Quebec", "Westmount", "4148A Ste-Catherine St W", "H3Z 0A2", "(514) 937-1200", "(514) 937-5125", "store@theupsstore.ca", (int) (45.549016 * 1E6), (int) (-73.535143 * 1E6));
     
     
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "2 TORONTO ST", "ON, M5C2B5", "416-363-8331", "416-363-2588", "store336@theupsstore.ca", (int) (43.649803 * 1E6), (int) (-79.376081 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "5 112 ELIZABETH ST", "ON, M5G1P5", "416-971-5001", "416-971-5001", "store479@theupsstore.ca", (int)(43.653735 * 1E6), (int) (-79.383512 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "371 FRONT STREET WEST", "ON, M5V3S8", "416-979-8700", "416-979-8700", "store421@theupsstore.ca", (int)(43.642776 * 1E6),(int)(-79.392397 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "720 KING ST W", "ON, M5V3S5", "416-366-5700", "416-366-5700",  "store287@theupsstore.ca", (int)(43.643861 * 1E6), (int)(-79.404007 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING ST. 54TH FL", "ON ,M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6),(int) (-79.38157 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING ST. 48TH FL", "ON ,M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6), (int)(-79.38157 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING ST. 47TH FL", "ON, M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6), (int)(-79.38157 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING 57TH FL", "ON, M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6), (int)(-79.38157 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING ST 6TH FL", "ON, M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6), (int)(-79.38157 * 1E6));
     StoreLocation.create(db, "Canada", "Toronto", "Ontario", "100 KING ST. 4TH FL", "ON, M5X1H3", "416-366-5700", "416-366-5700", "store@theupsstore.ca", (int)(43.64866 * 1E6), (int)(-79.38157 * 1E6));
     
     
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "2818 MAIN STREET", "V5T0C1", "604-874-6860", "604-874-6885", "store407@theupsstore.ca", (int) (49.259932 * 1E6), (int) (-123.100858 * 1E6));
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "1917 West 4th Ave", "V6J 1M7", "(604) 731-1048", "(604) 731-6023", "store94@theupsstore.ca", (int) (49.268224 * 1E6), (int) (-123.148307 * 1E6));
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "1350 Burrard St", "V6Z 0C2", "(604) 682-3399", "(604) 685-4455", "store@theupsstore.ca", (int) (49.277314 * 1E6), (int) (-123.131413 * 1E6));
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "280 Nelson St", "V6B 2E2", "(604) 608-6681", "(604) 608-6337", "store209@theupsstore.ca", (int) (49.276364 * 1E6), (int) (-123.118628 * 1E6));
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "101 - 1001 West Broadway", "V6H 4E4", "(604) 732-4147", "(604) 732-4959", "store@theupsstore.ca", (int) (49.263627 * 1E6), (int) (-123.127081 * 1E6));
     StoreLocation.create(db, "Canada", "British Columbia", "Vancouver", "3381 Cambie Street", "V5Z 4R3", "(604) 871-0038", "(604) 871-0039", "store@theupsstore.ca", (int) (49.25525 * 1E6), (int) (-123.115221 * 1E6));
     
     /*RH@C Commenting this out as the demo version shows three stores per city. We can get back to this later
     StoreLocation.create(db, "United States", "New York", "New York City", "888-C 8TH AVE", "NY 10019", (int) (40.763869 * 1E6), (int) (-73.984766 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "55 W 116TH ST", " NY 10026", (int) (40.800794 * 1E6), (int) (-73.946764 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "603 W 115TH ST", "NY 10025-7816", (int) (40.807576 * 1E6), (int) (-73.964945 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "1040 1ST AVE", "NY 10022-2902", (int) (40.758154 * 1E6), (int) (-73.963068 * 1E6));
     StoreLocation.create(db, "United States", "New York", "New York City", "694 10th Avenue", "NY 10019", (int) (40.763703 * 1E6), (int) (-73.992308 * 1E6));*/
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
    
 private static final String DB_NAME = "ups";
}
