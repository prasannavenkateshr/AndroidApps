package net.orderinstyles;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//import android.widget.Button;
//import android.view.Menu;
//import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StyleActivity extends Activity implements LocationListener {
	
	LocationManager locationManager;
	String provider;
	MapView mapView;
	Button ok;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
       
        ok=(Button)findViewById(R.id.btn_login);
        
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent def_intent=new Intent(v.getContext(),login2.class);
            	startActivityForResult(def_intent,0);
            }	
         });
        
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        
        if(provider!=null && !provider.equals("")){
        	 
            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);
 
            locationManager.requestLocationUpdates(provider, 20000, 1, this);
 
            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
 
        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
        
        
     }
    
    
   /* public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
 
    
    public void onLocationChanged(Location loc) {
    	GeoPoint gp; 
    	MapController mc;
    	mc=mapView.getController();
    	if (loc != null) {
			Toast.makeText(getBaseContext(),
					"Location : Lat: " + loc.getLatitude() +
					" Lng: " + loc.getLongitude(),
					Toast.LENGTH_SHORT).show();
		}
		gp = new GeoPoint( (int) (loc.getLatitude() * 1E6), (int) (loc.getLongitude() * 1E6));
		mc.animateTo(gp);
		mc.setZoom(18);
    }
 
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
    
    
   