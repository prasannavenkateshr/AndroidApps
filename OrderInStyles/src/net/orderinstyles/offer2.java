package net.orderinstyles;

import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject; 
import android.app.ListActivity; 
import android.net.ParseException; 
import android.os.Bundle; 
import android.util.Log; 
import android.widget.Toast; 

public class offer2 extends ListActivity { 
	JSONArray jArray; 
	String result = null; 
	InputStream is = null; 
	StringBuilder sb=null;
	int ctid; 
	String ctname, ctname2;
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.offers); 
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
		  try{ 
			  HttpClient httpclient = new DefaultHttpClient(); 
			  HttpPost httppost = new HttpPost("http://127.0.0.1/offers.php"); 
			  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
			  HttpResponse response = httpclient.execute(httppost); 
			  HttpEntity entity = response.getEntity(); 
			  is = entity.getContent(); 
			  }
		  catch(Exception e){ 
			  Log.e("log_tag", "Error in http connection"+e.toString()); 
			  } 
		  try{ 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8); 
			 sb = new StringBuilder(); 
			 sb.append(reader.readLine() + "\n"); 
			 String line="0"; 
			 while ((line = reader.readLine()) != null) 
			 { sb.append(line + "\n"); } 
			 is.close(); 
			 result=sb.toString(); 
			 }
		  catch(Exception e)
		  { 
			  Log.e("log_tag", "Error converting result "+e.toString()); 
		  } 
 
		  try{ 
			  jArray = new JSONArray(result); 
			  JSONObject json_data=null; 
			  for(int i=0;i<jArray.length();i++)
			  { 
			  json_data = jArray.getJSONObject(i); 
			  //ctid=json_data.getInt("CITY_ID"); 
			  ctname=json_data.getString("offers");
			  ctname2=json_data.getString("company"); 
			  } 
			  } 
		  catch(JSONException e1)
		  { Toast.makeText(getBaseContext(), "Sorry! No Offers Found" ,Toast.LENGTH_LONG).show(); } 
		  catch (ParseException e1) 
		  { e1.printStackTrace(); } 
		} 
	}