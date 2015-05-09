package net.orderinstyles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenu  extends Activity {
	
	// JSON node keys
	private static final String TAG_COUPON = "couponid";
	private static final String TAG_NAME = "name";
	private static final String TAG_AREA = "area";
	private static final String TAG_COMPANY = "company";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String couponid = in.getStringExtra(TAG_COUPON);
        String name = in.getStringExtra(TAG_NAME);
        String cost = in.getStringExtra(TAG_AREA);
        String description = in.getStringExtra(TAG_COMPANY);
        
        // Displaying all values on the screen
        TextView lblCoupon = (TextView) findViewById(R.id.coupon_label);
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblCost = (TextView) findViewById(R.id.email_label);
        TextView lblDesc = (TextView) findViewById(R.id.mobile_label);
        
        lblCoupon.setText(couponid);
        lblName.setText(name);
        lblCost.setText(cost);
        lblDesc.setText(description);
    }
}