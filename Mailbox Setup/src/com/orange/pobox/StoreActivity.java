package com.orange.pobox;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.orange.pobox.db.Database;
import com.orange.pobox.db.StoreLocation;
import com.orange.pobox.maps.LocationOverlay;

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
     locations = StoreLocation.loadClosest(db, latitude, longitude);
     storeListView = (ListView) findViewById(R.id.sa_list);
     GeoPoint centre = new GeoPoint(latitude, longitude);
     storeListView.setAdapter(new StoreListAdapter(this, locations, centre));
     storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
     {
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3)
        {
         listItemClicked(position);
        }
     });
     db.close();
     this.loadMapOverlay(mapView, centre, locations);
    }
    
    private void loadMapOverlay(MapView mapView, GeoPoint point, List<StoreLocation> locations)
    {
     Resources resources = this.getResources();
     List<Overlay> mapOverlays = mapView.getOverlays(); // TODO allow for the center point to not be bottom aligned
     Drawable mapMarker = resources.getDrawable(R.drawable.google_map_marker);
     Drawable greenDot = resources.getDrawable(R.drawable.green_dot);
     greenDot = resize(resources, greenDot, 20, 20);
     
     LocationOverlay greenDotOverlay = new LocationOverlay(greenDot, null);
     OverlayItem overlayItem = new OverlayItem(point, "", "");
     greenDotOverlay.addOverlay(overlayItem);
     mapOverlays.add(greenDotOverlay);
     
     LocationOverlay storeOverlay = new LocationOverlay(mapMarker, new LocationOverlay.OnMapOverlayTapListener()
     {
        public void overlayTapped(int index)
        {
         mapOverlayItemClicked(index);
        }
     });
     
         for (StoreLocation location : locations)
         {
          storeOverlay.addOverlay(new OverlayItem(location.getGeoPoint(), "", ""));
          mapOverlays.add(storeOverlay);
         }

     MapController controller = mapView.getController();
     controller.setZoom(DEFAULT_MAP_ZOOM);
     controller.setCenter(point);
    }
    
    private void listItemClicked(int position)
    {
     mapView.getController().animateTo(locations.get(position).getGeoPoint());
    }

    private void mapOverlayItemClicked(int index)
    {
     //storeListView.smoothScrollToPosition(index);
     //storeListView.setSelection(index);
     StoreLocation storeLocation = locations.get(index); // Get the selected location
     Intent intent = new Intent(this, FinalActivity.class);
     intent.putExtra(FinalActivity.INTENT_INPUT_STORE_LOCATION, storeLocation);
     this.startActivity(intent);
    }
    
    private Drawable resize(Resources resources, Drawable image, int dpWidth, int dpHeight) 
    {
     Bitmap d = ((BitmapDrawable) image).getBitmap();
     int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpWidth, resources.getDisplayMetrics());
     int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpHeight, resources.getDisplayMetrics());
     Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, width, height, false);
     return new BitmapDrawable(resources, bitmapOrig);
    }
    
    protected boolean isRouteDisplayed()
    {
     return false;
    }

    private static class StoreListAdapter implements ListAdapter
    {
        public StoreListAdapter(Context context, List<StoreLocation> locations, GeoPoint centre)
        {
         this.locations = locations;
         this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         this.centre = createFromGeoPoint(centre);
         this.formatter = new DecimalFormat("#.#");
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
         String distance = formatter.format(centre.distanceTo(createFromGeoPoint(location.getGeoPoint())) / METRES_PER_KM);
         holder.setTitle(location.getStreet());
         holder.setSubTitle(location.getRestOfAddress() + " (" + distance + "km)");
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
         return true;
        }

        public boolean isEnabled(int position)
        {
         return true;
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
        
        private Location createFromGeoPoint(GeoPoint point)
        {
         Location location = new Location("");
         location.setLatitude(point.getLatitudeE6() / 1E6f);
         location.setLongitude(point.getLongitudeE6() / 1E6f);
         return location;
        }
        
     private LayoutInflater inflater;
     private List<StoreLocation> locations;
     private Location centre;
     private DecimalFormat formatter;
    }
    
 private MapView mapView;
 private ListView storeListView;
 private List<StoreLocation> locations;
 public static final int DEFAULT_MAP_ZOOM = 16;
 public static final String INTENT_INPUT_LATITUDE = "lat", INTENT_INPUT_LONGITUDE = "long";
 private static final int METRES_PER_KM = 1000;
}
