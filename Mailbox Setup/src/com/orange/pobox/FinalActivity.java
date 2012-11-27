package com.orange.pobox;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
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
     storeLocation = (StoreLocation) creator.getSerializableExtra(INTENT_INPUT_STORE_LOCATION);
     customerInfo = (CustomerInfo) creator.getSerializableExtra(INTENT_INPUT_CUSTOMER_INFO);
     
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
    
    private void createPDFAndSendEmail()
    {
     Intent intent = new Intent(Intent.ACTION_SEND);
     intent.putExtra(Intent.EXTRA_SUBJECT, "Mailbox Application: " + customerInfo.getFirstName() 
    		 + " " + customerInfo.getSurname());
     intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"teamorangecontext@gmail.com"});
     
     
     	try
     	{
     	 System.out.println("Saving file to html");
     	 FileOutputStream out = this.openFileOutput("temporary_ups_email.html", Context.MODE_WORLD_READABLE);
     	 out.write(HtmlFormGenerator.getHtmlString(storeLocation, customerInfo).getBytes());
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
     this.startActivity(intent);
    }

 private StoreLocation storeLocation;
 private CustomerInfo customerInfo;
 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
 public static final String INTENT_INPUT_CUSTOMER_INFO = "customer_information";
}
