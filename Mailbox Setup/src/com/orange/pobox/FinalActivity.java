package com.orange.pobox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.orange.pobox.db.StoreLocation;

public class FinalActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.final_activity);
     this.progressDialog = ProgressDialog.show(this, "Sending Application", 
    		 "Your application is currently being sent to the UPS ofice of your choice.");
     Intent creator = this.getIntent();
     StoreLocation storeLocation = (StoreLocation) creator.getSerializableExtra(INTENT_INPUT_STORE_LOCATION);
     this.createPDFAndSendEmail(storeLocation);
     this.progressDialog.dismiss();
    }
    
    private void createPDFAndSendEmail(StoreLocation storeLocation)
    { // TODO This is you Monica
    }

 private ProgressDialog progressDialog;
 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
}
