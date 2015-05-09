package net.orderinstyles;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
import android.widget.Toast;

public class Location extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;
    
	EditText lc;
	
    Button ok;
    
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	//private static String url_all_products = "http://59.92.51.182/nearby.php?location=";

	// JSON Node names
	//private static final String TAG_SUCCESS = "success";
	//private static final String TAG_PRODUCTS = "products";
	//private static final String TAG_PID = "pid";
	//private static final String TAG_COUPON = "couponid";
	//private static final String TAG_NAME = "name";
	//private static final String TAG_AREA = "area";
	//private static final String TAG_COMPANY = "company";
	// products JSONArray
	JSONArray products = null;

	//protected Object adapter_1;
	//SimpleAdapter adapter_1 = new SimpleAdapter( Location.this, productsList, R.layout.list_item, new String[] { TAG_PID, TAG_COUPON,TAG_NAME, TAG_AREA, TAG_COMPANY}, new int[] { R.id.pid, R.id.coupon, R.id.name, R.id.area, R.id.company});
	//setListAdapter(adapter_1);
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
            	Intent define = new Intent(getApplicationContext(), Nearby.class);
            	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        	    nameValuePairs.add(new BasicNameValuePair("location", lc.getText().toString()));
        	    try {
        	        HttpClient httpclient = new DefaultHttpClient();
        	        HttpPost httppost = new HttpPost("http://180.215.114.192/nearby.php?location=");
        	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        	        HttpResponse response = httpclient.execute(httppost);
        	        HttpEntity entity = response.getEntity();
        	      //  InputStream is = entity.getContent();
        	    } catch (Exception e) {
        	        Log.e("log_tag", "Error in http connection" + e.toString());
        	    }
				startActivityForResult(define,0);
            }
        });
        
       
    }
}