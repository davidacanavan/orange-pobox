package com.orange.pobox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddressActivity extends Activity implements OnItemSelectedListener {
	Spinner countriesSp;
	Spinner citySp;
	String country;
	String city;
	
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.address_activity);
     countriesSp = (Spinner) findViewById(R.id.countries_spinner);
     citySp = (Spinner) findViewById(R.id.cities_spinner);
     //RH@C when country is selected the cities spinner will load 
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
    	     (this, R.array.countries, R.layout.spinner_txt_color);
     
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

             
             if(country.equals("Ireland")) {
                 //populating the city spinner
                 ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
        	    		 (getApplicationContext(), R.array.cities_ireland, R.layout.spinner_txt_color);
                 cityAdapter.setDropDownViewResource(R.layout.spinner_txt_color);
                 citySp.setAdapter(cityAdapter);
             }
             //End of redundant code the spinner is filled up with cities
         }
         public void onNothingSelected(AdapterView<?> arg0) { }         
     });
   
     //End of RH@C
    }
    
    
    public void findStore(View v){
    	Toast.makeText(getApplicationContext(), city + " " + country, 5).show();
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}   
}