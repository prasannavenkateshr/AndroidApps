package net.orderinstyles;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
//import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

public class checkloc extends Activity {
    EditText lc;
	
    Button ok, signup;
    JSONParser jParser = new JSONParser();
    
    //private static String  url_create_product = "http://180.215.57.20/nearby.php?";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.near);
        
        
        lc=(EditText)findViewById(R.id.et_lc);
        ok=(Button)findViewById(R.id.btn_login);
        
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            	
            	//Intent def_intent0=new Intent(v.getContext(),signup.class);
            	Intent def_intent1=new Intent(v.getContext(),Nearby.class);            	
            	//String valid = "1";
            	//String response = null;
            	
            	
            	
            	
            	String msg = lc.getText().toString();  

                // make sure the fields are not empty
                if (msg.length()>0)
                {
        	  	   // HttpClient httpclient = new DefaultHttpClient();
        	   	    //HttpPost httppost = new HttpPost("http://180.215.57.20/nearby.php");
        	   	 try {
        	   	   ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        	       nameValuePairs.add(new BasicNameValuePair("location", msg));
        	       //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        	   	   //httpclient.execute(httppost);
        	       CustomHttpClient.executeHttpPost("http://180.215.57.20/location.php", nameValuePairs);
        	   	   
        	   	   //lc.setText(""); // clear text box
        	     } catch (ClientProtocolException e) {
        	         // TODO Auto-generated catch block
        	     } catch (IOException e) {
        	         // TODO Auto-generated catch block
        	     }
        	     catch (Exception e) {
        	         // TODO Auto-generated catch block
        	     }
        	     startActivityForResult(def_intent1,0);
                }
                else
                {
                	// display message if text fields are empty
                    Toast.makeText(getBaseContext(),"Location required",Toast.LENGTH_SHORT).show();
                }
            	

            }
        });
        

    }
 
     

}   