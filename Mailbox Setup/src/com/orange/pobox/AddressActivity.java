package com.orange.pobox;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddressActivity extends Activity 
{	
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.address_activity);
     countriesSp = (Spinner) findViewById(R.id.countries_spinner);
     citySp = (Spinner) findViewById(R.id.cities_spinner);
     //RH@C when country is selected the cities spinner will load 
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, R.layout.spinner_txt_color);
     
     adapter.setDropDownViewResource(R.layout.spinner_txt_color);

     citySp.setOnItemSelectedListener(new OnItemSelectedListener() {
         public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
        	 city = citySp.getSelectedItem().toString();}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}});
     
     countriesSp.setOnItemSelectedListener(new OnItemSelectedListener() {
         public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
        	 country = countriesSp.getSelectedItem().toString();
             
             //Bunch of redundant code it however does the trick as a demo
             //we can put it later in a single function with a country as a parameter 
             if(country.equals("Canada")){
                //populating the city spinner following country selection
                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
       	    		 (getApplicationContext(), R.array.cities_canada, R.layout.spinner_txt_color);
                cityAdapter.setDropDownViewResource(R.layout.spinner_txt_color);
                citySp.setAdapter(cityAdapter);
                }
             
             if(country.equals("USA")) {
                 //populating the city spinner
                 ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
        	    		 (getApplicationContext(), R.array.cities_usa, R.layout.spinner_txt_color);
                 cityAdapter.setDropDownViewResource(R.layout.spinner_txt_color);
                 citySp.setAdapter(cityAdapter);	 
             }

             //RH@C taking off the ireland
             /*if(country.equals("Ireland")) {
                 //populating the city spinner
                 ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
        	    		 (getApplicationContext(), R.array.cities_ireland, R.layout.spinner_txt_color);
                 cityAdapter.setDropDownViewResource(R.layout.spinner_txt_color);
                 citySp.setAdapter(cityAdapter);
             }*/
             //End of redundant code the spinner is filled up with cities
         }
         public void onNothingSelected(AdapterView<?> arg0) { }         
     });
   
     //End of RH@C
    }
    
    public void findStore(View v)
    {
     Geocoder coder = new Geocoder(this);
     
     	try
     	{
     	 List<Address> addresses = coder.getFromLocationName(city + ", " + country, MAX_LOCATION_RESULTS);
     	 Address address = addresses.get(0);
     	 Intent intent = new Intent(this, StoreActivity.class);
         intent.putExtra(StoreActivity.INTENT_INPUT_LATITUDE, (int) (address.getLatitude() * 1E6));
         intent.putExtra(StoreActivity.INTENT_INPUT_LONGITUDE, (int) (address.getLongitude() * 1E6));
         this.startActivity(intent);
     	}
     	catch (IOException e)
     	{
     	 // TODO Do something when we can't find the adress!!!!! Just make sure we test these well
     	}
    }

 private Spinner countriesSp;
 private Spinner citySp;
 private String country;
 private String city;
 private static int MAX_LOCATION_RESULTS = 1;
}