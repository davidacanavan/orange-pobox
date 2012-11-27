package com.orange.pobox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
     intent.putExtra(
    		 Intent.EXTRA_TEXT,
    		 Html.fromHtml(new StringBuilder()
		     .append(HtmlFormGenerator.getHtmlString(storeLocation, customerInfo))
    		 .toString())
    		 );
     intent.setType("text/html");
     this.startActivity(intent);
    }

 private StoreLocation storeLocation;
 private CustomerInfo customerInfo;
 public static final String INTENT_INPUT_STORE_LOCATION = "store_location";
 public static final String INTENT_INPUT_CUSTOMER_INFO = "customer_information";
}
