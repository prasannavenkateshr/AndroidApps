package net.orderinstyles;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login2 extends Activity {
    EditText un,pw,lc;
	TextView error;
    Button ok, signup;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        un=(EditText)findViewById(R.id.et_un);
        pw=(EditText)findViewById(R.id.et_pw);
        lc=(EditText)findViewById(R.id.et_lc);
        ok=(Button)findViewById(R.id.btn_login);
        error=(TextView)findViewById(R.id.tv_error);
        
        signup=(Button)findViewById(R.id.btn_signup);
        
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent def_intent=new Intent(v.getContext(),signup.class);
            	startActivityForResult(def_intent,0);
            }	
         });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            	postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
            	postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));
            	postParameters.add(new BasicNameValuePair("location", lc.getText().toString()));
            	//Intent def_intent0=new Intent(v.getContext(),signup.class);
            	Intent def_intent1=new Intent(v.getContext(),values.class);            	
            	//String valid = "1";
            	String response = null;
            	try {
            	    response = CustomHttpClient.executeHttpPost("http://180.215.114.192/check.php", postParameters);
            	    String res=response.toString();
            	    
            	   // res = res.trim();
            	    res= res.replaceAll("\\s+","");         	              	 
            	    //error.setText(res);
            	   
            	   if(res.equals("1")){
            	    //	error.setText("Correct Username or Password");
            		    Toast.makeText(getBaseContext(), "Correct Username or Password", Toast.LENGTH_SHORT).show();
            	   		//Intent def_intent1=new Intent(v.getContext(),offers.class);
               			startActivityForResult(def_intent1,0);  
               			} 		
            	   else
            	    	{
            		   Toast.makeText(getBaseContext(), "Sorry! Incorrect Username or Passsword", Toast.LENGTH_SHORT).show();
            		      //error.setText("Sorry!! Incorrect Username or Password");
            	    	 
            	    	}
            	   
            	    //Intent def_intent1=new Intent(v.getContext(),offers.class);
               		//startActivityForResult(def_intent1,0);
            	} catch (Exception e) {
            		un.setText(e.toString());
            	}

            }
        });
    }
}
