package net.orderinstyles;
 
import android.app.Activity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.Collection;

//import android.util.Log;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
 
public class JSONExampleActivity extends Activity {
    /** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://59.92.51.236/offers.php");
        TextView textView = (TextView)findViewById(R.id.textView1);
       
  try {
    
   HttpResponse response = httpclient.execute(httppost);
   
   String jsonResult = (String) inputStreamToString(response.getEntity().getContent()).toString();
   // response.getEntity().getContent()
   JSONArray jArray = new JSONArray(jsonResult);
   JSONObject object=null;
 
   for(int i=0;i<jArray.length();i++){
	   
	  object = jArray.getJSONObject(i); 
      String name = object.getString("offers");
      String verion = object.getString("area");
      String name1 = object.getString("company");
    
      textView.setText(name + " - " + verion + " - " + name1);
     
   }   
  } 
  catch (JSONException e) {
   e.printStackTrace();
  } 
  catch (ClientProtocolException e) {
   e.printStackTrace();
  } 
  catch (IOException e) {
   e.printStackTrace();
  }
 
 
       }
    
	private StringBuilder inputStreamToString(InputStream is) {
	//	is = getResources().openRawResource(R.raw.json);
        
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder answer = new StringBuilder();
        String rLine = null;
        try {
         while ((rLine = rd.readLine()) != null) {
          answer.append(rLine + "\n");
          
           }
        }
          
        catch (IOException e) {
            e.printStackTrace();
         }
        return answer;
       }
}


