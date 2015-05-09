package net.orderinstyles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
import android.widget.Toast;

public class Nearby extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url_all_products = "http://180.215.57.20/nearby.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "pid";
	private static final String TAG_COUPON = "couponid";
	private static final String TAG_NAME = "name";
	private static final String TAG_AREA = "area";
	private static final String TAG_COMPANY = "company";
	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		@SuppressWarnings("unused")
		ListView lv = getListView();

		// on seleting single product
		// launching Edit Product Screen
		/*lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();

				// Starting new intent
				//Intent in = new Intent(getApplicationContext(),
					//	EditProductActivity.class);
				// sending pid to next activity
			//	in.putExtra(TAG_PID, pid);
				
				// starting new activity and expecting some response back
			//	startActivityForResult(in, 100);
			}
		});*/

	}

	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		Intent i = new Intent(getApplicationContext(),values.class);
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Nearby.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String coupon = c.getString(TAG_COUPON);
						String name = c.getString(TAG_NAME);
						String area = c.getString(TAG_AREA);
						String company = c.getString(TAG_COMPANY);
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_COUPON, coupon);
						map.put(TAG_NAME, name);
						map.put(TAG_AREA, area);
						map.put(TAG_COMPANY, company);
						
						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					//Intent i = new Intent(getApplicationContext(),values.class);
					// Closing all previous activities
					//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					Toast.makeText(getBaseContext(), "Sorry!! No Offers Found", Toast.LENGTH_SHORT).show();
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter( Nearby.this, productsList, R.layout.list_item, new String[] { TAG_PID, TAG_COUPON,TAG_NAME, TAG_AREA, TAG_COMPANY}, new int[] { R.id.pid, R.id.coupon, R.id.name, R.id.area, R.id.company});
							//, R.layout.list_item, new String[] 
				             //           { TAG_PID,	TAG_NAME}, new int[] { R.id.pid, R.id.name});
					// updating listview
					setListAdapter(adapter);
				}
			});
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                // getting values from selected ListItem
	            	String coupon = ((TextView) view.findViewById(R.id.coupon)).getText().toString();
	                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
	                String area = ((TextView) view.findViewById(R.id.area)).getText().toString();
	                String company = ((TextView) view.findViewById(R.id.company)).getText().toString();
	 
	                // Starting new intent
	                Intent in = new Intent(getApplicationContext(), SingleMenu.class);
	                
	                in.putExtra(TAG_COUPON, coupon);
	                in.putExtra(TAG_NAME, name);
	                in.putExtra(TAG_AREA, area);
	                in.putExtra(TAG_COMPANY, company);
	                startActivity(in);
	            }
	        });	

		}

	}
}