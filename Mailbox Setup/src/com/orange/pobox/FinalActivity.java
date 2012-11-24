package com.orange.pobox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.orange.pobox.db.CustomerInfo;
import com.orange.pobox.db.StoreLocation;

public class FinalActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.final_activity);
     Intent creator = this.getIntent();
     StoreLocation storeLocation = (StoreLocation) creator.getSerializableExtra(INTENT_INPUT_STORE_LOCATION);
     CustomerInfo customerInfo = (CustomerInfo) creator.getSerializableExtra(INTENT_INPUT_CUSTOMER_INFO);
     this.createPDFAndSendEmail(storeLocation, customerInfo);
    }
    
    private void createPDFAndSendEmail(StoreLocation storeLocation, CustomerInfo customerInfo)
    {
     final ProgressDialog progressDialog = ProgressDialog.show(this, "Sending Application", 
       		 "Your application is currently being sent to the UPS ofice of your choice.");
     Thread thread = new Thread()
     {
    	 public void run()
    	 {
    		 try
    		 {// TODO This is you Monica
    		  Thread.sleep(10000);
    		  progressDialog.dismiss();
    		 }
    		 catch (InterruptedException e) {}
    	 }
     };
     thread.start();
    }

 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
 public static final String INTENT_INPUT_CUSTOMER_INFO = "customer_information";
}
