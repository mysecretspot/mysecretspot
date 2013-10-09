package csc305.mysecretspot;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class UserInput extends Activity implements OnItemSelectedListener{
	public String fishName[] = new String[100];
    public String fishWeight[] = new String[100];
    private String fishType, baitType;
    double weight, length;
    private Spinner spinner1, spinner2;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_input);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(this);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener(this);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
					R.array.fishTypeArray, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner1.setAdapter(adapter);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
					R.array.baitTypeArray, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner2.setAdapter(adapter2);
        
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_input, menu);
		return true;
	}
	public void submitClicked(View view){
		// Inserting Contacts
		DatabaseHandler db = new DatabaseHandler(this);
		EditText weightEntry=(EditText)findViewById(R.id.editText3);
	    weight=Double.parseDouble(weightEntry.getText().toString());
	    EditText lengthEntry=(EditText)findViewById(R.id.editText4);
	    length=Double.parseDouble(lengthEntry.getText().toString());
	    double[] d = getlocation();
	    double lat = d[0];
	    double lng = d[1];
		Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact(lat, lng, fishType, baitType, weight, length));       
        Intent myIntent = new Intent(UserInput.this, MainActivity.class);
		UserInput.this.startActivity(myIntent);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		fishType = String.valueOf(spinner1.getSelectedItem());
		baitType = String.valueOf(spinner2.getSelectedItem());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
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
