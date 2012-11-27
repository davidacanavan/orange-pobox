package com.orange.pobox;

import java.io.FileOutputStream;
import java.io.IOException;

import com.orange.pobox.db.CustomerInfo;
import com.orange.pobox.db.StoreLocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
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
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setTitle("Confimation");
     builder.setMessage("The following screen will send your application by email to the UPS store you have selected. " +
     		"Please send the email to complete the application process.");
     builder.setCancelable(false);
     builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() 
     {
    	 public void onClick(DialogInterface dialog, int which) 
    	 {
    	  dialog.dismiss();
	      createPDFAndSendEmail();
	     }
     });
     builder.show();
    }
    
    @SuppressLint("WorldReadableFiles")
	private void createPDFAndSendEmail()
    {
     CustomerInfo info = new CustomerInfo(getEditTextText(R.id.cia_first_name_edit), getEditTextText(R.id.cia_surname_edit), 
   		 getEditTextText(R.id.cia_email_edit), getEditTextText(R.id.cia_home_phone_edit), 
   		 getEditTextText(R.id.cia_mobile_phone_edit), getEditTextText(R.id.cia_street_address_edit), 
   		 getEditTextText(R.id.cia_city_edit), getEditTextText(R.id.cia_county_edit), 
   		 getEditTextText(R.id.cia_state_edit), getEditTextText(R.id.cia_country_edit), 
   		 getEditTextText(R.id.cia_zip_code_edit));
     Intent intent = new Intent(Intent.ACTION_SEND);
     intent.putExtra(Intent.EXTRA_SUBJECT, "Mailbox Application: " + info.getFirstName() 
    		 + " " + info.getSurname());
     intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"teamorangecontext@gmail.com"});
     
     
     	try
     	{
     	 System.out.println("Saving file to html");
     	 FileOutputStream out = this.openFileOutput("temporary_ups_email.html", Context.MODE_WORLD_READABLE);
     	 out.write(HtmlFormGenerator.getHtmlString(storeLocation, info).getBytes());
     	 out.close();
     	 System.out.println("File saved");
     	}
     	catch (IOException e) {}//TODO Sort the IO error out
     	
     intent.putExtra(
    		 Intent.EXTRA_TEXT,
    		 Html.fromHtml(new StringBuilder()
		     .append("<p>Customer Application...</p>")
    		 .toString())
    		 );
     intent.setType("text/html");
     intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(getFileStreamPath("temporary_ups_email.html")));
     this.startActivityForResult(intent, 1001);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
     Intent intent = new Intent(this, FinalActivity.class);
     this.startActivity(intent);
    }
    
    private String getEditTextText(int id)
    {
     return ((EditText) findViewById(id)).getText().toString();
    }
    
 private StoreLocation storeLocation;
 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
}
