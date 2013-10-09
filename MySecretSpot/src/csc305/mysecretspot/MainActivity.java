package csc305.mysecretspot;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends android.support.v4.app.FragmentActivity {
	GoogleMap googleMap;
    MarkerOptions markerOptions;
    GPSTracker gps = new GPSTracker(MainActivity.this);
    public String fishType[] = new String[100];
    public String baitType[] = new String[100];
    public double fishWeight[] = new double[100];
    public double fishLength[] = new double[100];
    public double latitude2[] = new double[100];
    public double longitude2[] = new double[100];
    double latitude, longitude;
    int i = 0;
    int j = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	DatabaseHandler db = new DatabaseHandler(this);
    	setUpMapIfNeeded();
    	// Reading all contacts
    	Log.d("Reading: ", "Reading all contacts..");
    	List<Contact> contacts = db.getAllContacts();      
    	for (Contact cn : contacts) {
    		String log = "Id: "+cn.getID()+" ,Latitude: " + cn.getLatitude() + " ,Longitude: " + cn.getLongitude() + 
        		" ,FishType: " + cn.getFishType() + " ,BaitType: " + cn.getBaitType() + " ,Weight: " + cn.getWeight() +
        		",Length: " + cn.getLength();
            // Writing Contacts to log
    		latitude2[i] = cn.getLatitude();
    		longitude2[i] = cn.getLongitude();
    		fishLength[i] = cn.getLength();
    		baitType[i] = cn.getBaitType();
    		fishType[i] = cn.getFishType();
    		fishWeight[i] = cn.getWeight();
    		i++;
    		Log.d("Name: ", log);
    	} 
    	//Declare the timer
    	Timer t = new Timer();
    	//Set the schedule function and rate
    	t.scheduleAtFixedRate(new TimerTask() {
    		public void run() {
            //Called each time when 1000 milliseconds (1 second) (the period parameter)
        	
        	//We must use this function in order to change the text view text
    			runOnUiThread(new Runnable() {
    				@Override
    				public void run() {
    					while (fishType[j] != null){
    						googleMap.addMarker(new MarkerOptions()
    							.position(new LatLng(latitude2[j], longitude2[j]))
    							.title("fish caught")
    							.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    							.snippet("I caught a " + fishType[j] +  " here using "+ baitType[j] + " it's weight was "
    									+ fishWeight[j] + " lbs and it was " + fishLength[j] + " inches long"));
    						j++;
    					}
    				}
        	     });
    		}
    	},
    	//Set how long before to start calling the TimerTask (in milliseconds)
    	3000,
    	//Set the amount of time between each execution (in milliseconds)
    	5000);
    }
    private void setUpMapIfNeeded() {
    	if (googleMap == null) {
    		Log.e("", "Into null map");
    		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    	}
    	if (googleMap != null) {
            Log.e("", "Into full map");
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
        double[] d = getlocation();
        double lat = d[0];
        double lng = d[1];
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
            	new LatLng(lat, lng), 15));
    }
    public void caughtFish(View view){
    	Intent myIntent = new Intent(MainActivity.this, UserInput.class);
    	MainActivity.this.startActivity(myIntent);
    }
    public double[] getlocation() {
    	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	List<String> providers = lm.getProviders(true);
    	Location l = null;
    	for (int i = 0; i < providers.size(); i++) {
    		l = lm.getLastKnownLocation(providers.get(i));
    		if (l != null)
    			break;
    	}
    	double[] gps = new double[2];
    	if (l != null) {
    		gps[0] = l.getLatitude();
    		gps[1] = l.getLongitude();
    	}
    	return gps;
    }
}