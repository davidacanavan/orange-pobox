package com.orange.pobox;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.orange.pobox.maps.LocationFinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MapsActivity extends MapActivity implements LocationFinder.LocationResult
{
    public void onCreate(Bundle savedInstanceState)
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.maps_activity);
     this.mapView = (MapView) findViewById(R.id.ma_map);
     ((Button) findViewById(R.id.ma_select_button)).setOnClickListener(new View.OnClickListener()
     {
        public void onClick(View view)
        {
         selectButtonClicked();
        }
     });
     //this.progressDialog = ProgressDialog.show(this, "", "Searching for your location...", true, false);
     //new LocationFinder().getLocation(this, this);
     this.loadMapOverlay((MapView) findViewById(R.id.ma_map), new GeoPoint((int) (40.778624 * 1E6), (int) (-73.953567 * 1E6)));
    }
    
    private void selectButtonClicked()
    {
     Intent intent = new Intent(this, StoreActivity.class);
     GeoPoint point = mapView.getProjection().fromPixels(mapView.getWidth() / 2, mapView.getHeight() / 2);
     intent.putExtra(StoreActivity.INTENT_INPUT_LATITUDE, point.getLatitudeE6());
     intent.putExtra(StoreActivity.INTENT_INPUT_LONGITUDE, point.getLongitudeE6());
     this.startActivity(intent);
    }

    public void gotLocation(Location location)
    {
     this.loadMapOverlay((MapView) findViewById(R.id.ma_map), 
             new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6)));
     progressDialog.cancel();
    }
    
    public void networkNotEnabled() // TODO Catch the error...
    {
    }

    /**
     * Loads the map overlays.
     */
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

 private ProgressDialog progressDialog;
 private MapView mapView;
 public static final int DEFAULT_MAP_ZOOM = 18;
}
