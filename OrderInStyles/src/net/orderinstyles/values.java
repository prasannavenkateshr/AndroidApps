package net.orderinstyles;

//import java.util.ArrayList;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;

//import java.io.InputStream;
//import java.util.ArrayList;

//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.text.Editable;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.content.Context;
//import android.widget.Toast;
//import android.widget.Toast;

public class values extends Activity {
    EditText un,pw,email, mobile, lc;
	TextView error, tip;
    Button ok, signup, signup1,signup2 ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.values);
        
        ok=(Button)findViewById(R.id.btn_login);
        
        
        signup=(Button)findViewById(R.id.btn_signup);
        signup1 = (Button)findViewById(R.id.btn_signup1);
        signup2 = (Button)findViewById(R.id.btn_signup2);
        
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent def_intent3=new Intent(v.getContext(),Redeem.class);
            	startActivityForResult(def_intent3,0);
            }	
         });
        signup1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent def_intentA=new Intent(v.getContext(),Favorites.class);
            	startActivityForResult(def_intentA,0);
            }	
         });
        signup2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	// getIntent().getExtras().
            	Intent def_intentB=new Intent(v.getContext(),Locate.class);
            	
              	startActivityForResult(def_intentB,0);
            	
            }	
         });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            	
            	Intent def_intent4=new Intent(v.getContext(),AllProductsActivity.class);
            	startActivityForResult(def_intent4,0);


            }
        });
        
    }
}
