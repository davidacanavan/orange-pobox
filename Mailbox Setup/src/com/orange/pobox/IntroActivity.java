package com.orange.pobox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class IntroActivity extends Activity 
{
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.intro_activity);
     this.applyListenersToChildren(findViewById(R.id.ia_pick_store_layout), new View.OnClickListener()
     {
        public void onClick(View view)
        {
         usePickStoreButtonClicked();
        }
     });
     this.applyListenersToChildren(findViewById(R.id.ia_pick_by_maps_layout), new View.OnClickListener()
     {
        public void onClick(View view)
        {
         pickByMapButtonClicked();
        }
     });
    }
   
    private void usePickStoreButtonClicked()
    {
     Intent intent = new Intent(this, AddressActivity.class);
     this.startActivity(intent);
    }
    
    private void pickByMapButtonClicked()
    {
     Intent intent = new Intent(this, MapsActivity.class);
     this.startActivity(intent);
    }
    
    private void applyListenersToChildren(View view, View.OnClickListener listener)
    {
     view.setOnClickListener(listener);
    }
}
