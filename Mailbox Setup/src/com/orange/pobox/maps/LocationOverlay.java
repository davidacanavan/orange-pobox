package com.orange.pobox.maps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class LocationOverlay extends ItemizedOverlay<OverlayItem>
{
    public LocationOverlay(Drawable defaultMarker) 
    {
     super(boundCenterBottom(defaultMarker));
    }
    
    public LocationOverlay(Drawable defaultMarker, Context context) 
    {
     super(boundCenterBottom(defaultMarker));
     //this.context = context;
    }
    
    public void addOverlay(OverlayItem overlay) 
    {
     overlays.add(overlay);
     this.populate();
    }
    
    protected OverlayItem createItem(int i) 
    {
     return overlays.get(i);
    }
    
    public int size() 
    {
     return overlays.size();
    }
    
    //protected boolean onTap(int index) 
    //{
    // return true;
    //}
    
 private ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
 //private Context context;
}

