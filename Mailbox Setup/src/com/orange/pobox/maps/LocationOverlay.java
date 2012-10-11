package com.orange.pobox.maps;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class LocationOverlay extends ItemizedOverlay<OverlayItem>
{
    public LocationOverlay(Drawable defaultMarker, OnMapOverlayTapListener listener) 
    {
     super(boundCenterBottom(defaultMarker));
     this.listener = listener;
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
    
    protected boolean onTap(int index) 
    {
        if (listener != null)
        {
         listener.overlayTapped(index);
         return true;
        }
            
     return super.onTap(index);
    }
    
    public static interface OnMapOverlayTapListener
    {
        void overlayTapped(int index);
    }
    
 private ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
 private OnMapOverlayTapListener listener;
}

