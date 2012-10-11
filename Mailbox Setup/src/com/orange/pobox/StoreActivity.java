package com.orange.pobox;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.orange.pobox.db.Database;
import com.orange.pobox.db.StoreLocation;

public class StoreActivity extends MapActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.store_activity);
     this.mapView = (MapView) findViewById(R.id.sa_map);
     SQLiteDatabase db = new Database(this).getWritableDatabase();
     Intent creator = this.getIntent();
     int latitude = creator.getIntExtra(INTENT_INPUT_LATITUDE, 0);
     int longitude = creator.getIntExtra(INTENT_INPUT_LONGITUDE, 0);
     ((ListView) findViewById(R.id.sa_list)).setAdapter(new StoreListAdapter(this, StoreLocation.loadClosest(db, latitude, longitude)));
     db.close();
     //this.loadMapOverlay(mapView, new GeoPoint((int) (40.778624 * 1E6), (int) (-73.953567 * 1E6)));
    }
    
    private void loadMapOverlay(MapView mapView, GeoPoint point)
    {
     MapController controller = mapView.getController();
     controller.setZoom(DEFAULT_MAP_ZOOM);
     controller.setCenter(point);
    }
    
    protected boolean isRouteDisplayed()
    {
     return false;
    }

    private static class StoreListAdapter implements ListAdapter
    {
        public StoreListAdapter(Context context, List<StoreLocation> locations)
        {
         this.locations = locations;
         this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        
        public int getCount()
        {
         return locations.size();
        }

        public Object getItem(int position)
        {
         return null;
        }

        public long getItemId(int position)
        {
         return 0;
        }

        public int getItemViewType(int position)
        {
         return 0;
        }

        public View getView(int position, View convert, ViewGroup parent)
        {
         View view = convert;
         ViewHolder holder = null;
         
             if (view == null)
             {
              view = inflater.inflate(R.layout.store_activity_list_item, parent, false);
              holder = new ViewHolder((TextView) view.findViewById(R.id.sal_street_address), 
                      (TextView) view.findViewById(R.id.sal_rest_of_address));
              view.setTag(holder);
             }
             else
                 holder = (ViewHolder) view.getTag();

         StoreLocation location = locations.get(position);
         holder.setTitle(location.getStreet());
         holder.setSubTitle(location.getRestOfAddress());
         return view;
        }

        public int getViewTypeCount()
        {
         return 1;
        }

        public boolean hasStableIds()
        {
         return false;
        }

        public boolean isEmpty()
        {
         return locations.size() > 0;
        }

        public void registerDataSetObserver(DataSetObserver arg0)
        {
        }

        public void unregisterDataSetObserver(DataSetObserver arg0)
        {
        }

        public boolean areAllItemsEnabled()
        {
         return false;
        }

        public boolean isEnabled(int position)
        {
         return false;
        }
        
        private static class ViewHolder
        {
            public ViewHolder(TextView title, TextView subTitle)
            {
             this.title = title;
             this.subTitle = subTitle;
            }
            
            public void setTitle(String string)
            {
             title.setText(string);
            }
            
            public void setSubTitle(String string)
            {
             subTitle.setText(string);
            }
            
         private TextView title, subTitle;
        }
        
     private LayoutInflater inflater;
     private List<StoreLocation> locations;
    }
    
 private MapView mapView;
 public static final int DEFAULT_MAP_ZOOM = 16;
 public static final String INTENT_INPUT_LATITUDE = "lat", INTENT_INPUT_LONGITUDE = "long";
}
