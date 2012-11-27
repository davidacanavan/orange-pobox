package com.orange.pobox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class FinalActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
     super.onCreate(savedInstanceState);
     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
     this.setContentView(R.layout.final_activity);
    }
}
