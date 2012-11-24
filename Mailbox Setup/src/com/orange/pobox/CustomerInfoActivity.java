package com.orange.pobox;

import com.orange.pobox.db.CustomerInfo;
import com.orange.pobox.db.StoreLocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomerInfoActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.customer_info_activity);
     Intent creator = this.getIntent();
     this.storeLocation = (StoreLocation) creator.getSerializableExtra(INTENT_INPUT_STORE_LOCATION);
     ((Button) findViewById(R.id.cia_submit_button)).setOnClickListener(new View.OnClickListener() 
     {
		public void onClick(View view) {submitButtonPressed();}
	 });
    }
    
    private void submitButtonPressed()
    {
     CustomerInfo info = new CustomerInfo(getEditTextText(R.id.cia_first_name_edit), getEditTextText(R.id.cia_surname_edit), 
    		 getEditTextText(R.id.cia_email_edit), getEditTextText(R.id.cia_home_phone_edit), 
    		 getEditTextText(R.id.cia_mobile_phone_edit), getEditTextText(R.id.cia_street_address_edit), 
    		 getEditTextText(R.id.cia_city_edit), getEditTextText(R.id.cia_county_edit), 
    		 getEditTextText(R.id.cia_state_edit), getEditTextText(R.id.cia_country_edit), 
    		 getEditTextText(R.id.cia_zip_code_edit));
     Intent intent = new Intent(this, FinalActivity.class);
     intent.putExtra(FinalActivity.INTENT_INPUT_STORE_LOCATION, storeLocation);
     intent.putExtra(FinalActivity.INTENT_INPUT_CUSTOMER_INFO, info);
     this.startActivity(intent);
    }
    
    private String getEditTextText(int id)
    {
     return ((EditText) findViewById(id)).getText().toString();
    }
    
 private StoreLocation storeLocation;
 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
}
