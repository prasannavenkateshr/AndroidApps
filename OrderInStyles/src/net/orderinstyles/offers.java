package net.orderinstyles;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class offers extends Activity {
    EditText un,pw;
	TextView error;
    Button ok, signup;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ok=(Button)findViewById(R.id.btn_login);
        
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            	ArrayList<NameValuePair> getParameters = new ArrayList<NameValuePair>();
            	getParameters.add(new BasicNameValuePair("offers", un.getText().toString()));
            	getParameters.add(new BasicNameValuePair("area", pw.getText().toString()));
            	getParameters.add(new BasicNameValuePair("company", pw.getText().toString()));
            	
            	String response = null;
            	try {
            	    response = CustomHttpClient.executeHttpPost("http://59.92.35.112/offers.php", getParameters);
            	    String res=response.toString();
            	    Toast.makeText(getBaseContext(), res , Toast.LENGTH_SHORT).show();
            	    res= res.replaceAll("\\s+","");         	              	 
            	  
            	} catch (Exception e) {
            		error.setText(e.toString());
            	}
            	
            }
        });
    }
}
