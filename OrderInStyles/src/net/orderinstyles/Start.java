package net.orderinstyles;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Start extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashme);
		Thread t1 = new Thread(){
			public void run(){
				try{
					sleep(1000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}finally{
				  Intent open = new Intent("net.orderinstyles.login2");
				  startActivity(open);
				}
			}
		};
		t1.start();
	}
}