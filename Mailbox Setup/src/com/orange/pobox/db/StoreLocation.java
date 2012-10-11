package com.orange.pobox.db;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.maps.GeoPoint;
import com.orange.pobox.MinimumLinkedList;

public class StoreLocation
{
    private StoreLocation(long key, String country, String state, String city, String street, String zip, int latitude, int longitude)
    {
     this.key = key;
     this.street = street;
     this.state = state;
     this.country = country;
     this.zip = zip;
     this.city = city;
     this.latitude = latitude;
     this.longitude = longitude;
    }
    
    public long getKey()
    {
     return key;
    }
    
    public String getStreet()
    {
     return street;
    }
    
    public String getState()
    {
     return state;
    }
    
    public String getZip()
    {
     return zip;
    }
    
    public String getCity()
    {
     return city;
    }
    
    public String getCountry()
    {
     return country;
    }
    
    public String getRestOfAddress()
    {
     return city;
    }
    
    public int getLatitude()
    {
     return latitude;
    }
    
    public int getLongitude()
    {
     return longitude;
    }
    
    public GeoPoint getGeoPoint()
    {
     return new GeoPoint(latitude, longitude);
    }
    
    public static StoreLocation create(SQLiteDatabase db, String country, String state, String city, String street, String zip, int latitude, int longitude)
    {
     ContentValues values = new ContentValues();
     values.put(VAR_COUNTRY, country);
     values.put(VAR_STATE, state);
     values.put(VAR_CITY, city);
     values.put(VAR_STREET, street);
     values.put(VAR_ZIP, zip);
     values.put(VAR_LATITUDE, latitude);
     values.put(VAR_LONGITUDE, longitude);
     long key = db.insert(TABLE_NAME, null, values);
     return new StoreLocation(key, country, state, city, street, zip, latitude, longitude);
    }
    
    public static List<StoreLocation> loadClosest(SQLiteDatabase db, int latitude, int longitude)
    {
     MinimumLinkedList<StoreLocation> locations = new MinimumLinkedList<StoreLocation>(CLOSEST_MATCHES);
     double lat = latitude, lon = longitude;
     Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
     boolean hasNext = cursor.moveToFirst();
     
         while (hasNext)
         {
          StoreLocation location = new StoreLocation(cursor.getInt(IND_KEY), cursor.getString(IND_COUNTRY), 
                  cursor.getString(IND_STATE), cursor.getString(IND_CITY), cursor.getString(IND_STREET), 
                  cursor.getString(IND_ZIP), cursor.getInt(IND_LATITUDE), cursor.getInt(IND_LONGITUDE));
          double clat = location.getLatitude(), clon = location.getLongitude();
          locations.add(location, Math.sqrt(Math.pow(lat - clat, 2) + Math.pow(lon - clon, 2)));
          hasNext = cursor.moveToNext();
         }
     
     cursor.close();
     return locations.getArrayList();
    }
    
    public static String getTableCreationString()
    {
     return "create table " + TABLE_NAME + " (" +
            "id integer primary key autoincrement," +
            VAR_COUNTRY + " varchar(50) not null," +
            VAR_STATE + " varchar(50) not null," +
            VAR_CITY + " varchar(50) not null," +
            VAR_STREET + " varchar(50) not null," +
            VAR_ZIP + " varchar(50) not null," +
            VAR_LATITUDE + " integer not null," +
            VAR_LONGITUDE + " integer not null" +
            ");";
    }
    
 private long key;
 private String street, state, zip, country, city;
 private int latitude, longitude;
 private static final int CLOSEST_MATCHES = 10;
 private static final String TABLE_NAME = "store_location", VAR_COUNTRY = "country", VAR_STATE = "state", 
         VAR_CITY = "city", VAR_STREET = "street", VAR_ZIP = "zip", VAR_LATITUDE = "latitude", VAR_LONGITUDE = "longitude";
 private static final int IND_KEY = 0, IND_COUNTRY = 1, IND_STATE = 2, IND_CITY = 3, IND_STREET = 4, IND_ZIP = 5, 
         IND_LATITUDE = 6, IND_LONGITUDE = 7;
}



