package com.orange.pobox.maps;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationFinder 
{
    public void getLocation(Context context, LocationResult result)
    {
     //I use LocationResult callback class to pass location value from MyLocation to user code.
     locationResult = result;
     
        if(manager == null)
            manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try {gpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);} catch (Exception e) {}
        try {networkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);} catch (Exception e) {}

        //don't start listeners if no provider is enabled
        if(!gpsEnabled && !networkEnabled)
        {
         result.networkNotEnabled();
         return;
        }

        if(gpsEnabled)
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        
        if(networkEnabled)
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        
     timer = new Timer();
     timer.schedule(new GetLastLocation(), WAIT_TIME);
    }

    LocationListener locationListenerGps = new LocationListener() 
    {
        public void onLocationChanged(Location location)
        {
         timer.cancel();
         locationResult.gotLocation(location);
         manager.removeUpdates(this);
         manager.removeUpdates(locationListenerNetwork);
        }
        
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    LocationListener locationListenerNetwork = new LocationListener() 
    {
        public void onLocationChanged(Location location) 
        {
            timer.cancel();
            locationResult.gotLocation(location);
            manager.removeUpdates(this);
            manager.removeUpdates(locationListenerGps);
        }
        
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    class GetLastLocation extends TimerTask 
    {
        public void run() 
        {
         manager.removeUpdates(locationListenerGps);
         manager.removeUpdates(locationListenerNetwork);
         Location netLoc = null, gpsLoc=null;
         
             if(gpsEnabled)
                 gpsLoc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             
             if(networkEnabled)
                 netLoc = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

             //if there are both values use the latest one
             if(gpsLoc != null && netLoc != null)
             {
                 if(gpsLoc.getTime() > netLoc.getTime())
                     locationResult.gotLocation(gpsLoc);
                 else
                     locationResult.gotLocation(netLoc);
                 
              return;
             }

             if(gpsLoc != null)
             {
              locationResult.gotLocation(gpsLoc);
              return;
             }
             
             if(netLoc != null)
             {
              locationResult.gotLocation(netLoc);
              return;
             }
             
         locationResult.gotLocation(null);
        }
    }

    public static interface LocationResult
    {
        void gotLocation(Location location);
        void networkNotEnabled();
    }

 Timer timer;
 LocationManager manager;
 LocationResult locationResult;
 boolean gpsEnabled = false;
 boolean networkEnabled = false;
 public static final int WAIT_TIME = 10000;
}