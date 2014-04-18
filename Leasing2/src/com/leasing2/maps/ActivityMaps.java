package com.leasing2.maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.leasing2.R;
import com.leasing2.utilities.GMapV2Direction;
import com.leasing2.utilities.MapsUtils;

import android.app.Activity;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class ActivityMaps extends Activity{
	private final double INITIAL_LATITUDE = 49.616939;
	private final double INITIAL_LONGITUDE = 6.12329;
	private final float INITIAL_ZOOM = 8.5f;
	private GoogleMap theMap; 
	
	private PolylineOptions rectLine;
	
	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_maps);
		
		MapsUtils mapsutils = new MapsUtils();
        theMap = mapsutils.initMaps(getFragmentManager());
        
        mapsutils.updatePlaces(INITIAL_LATITUDE, INITIAL_LONGITUDE, INITIAL_ZOOM);
        
        (new AsyncTask<String, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(String... params) {
				LatLng fromPosition = new LatLng(INITIAL_LATITUDE, INITIAL_LONGITUDE);
	            LatLng toPosition = new LatLng(49.55, 6.455);

	            GMapV2Direction md = new GMapV2Direction();
	            
	            Geocoder geo = new Geocoder(getApplicationContext());
	            @SuppressWarnings("unused")
				List<Address> addressList;
	            try {
					addressList =  geo.getFromLocation(INITIAL_LATITUDE,INITIAL_LONGITUDE, 5000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
	            ArrayList<LatLng> directionPoint = md.getDirection(doc);
	            rectLine = new PolylineOptions().width(3).color(Color.RED);

	            for(int i = 0 ; i < directionPoint.size() ; i++) {          
	            	rectLine.add(directionPoint.get(i));
	            }
	            
	            mHandler.post(new Runnable() {
	                public void run() {
	                	theMap.addPolyline(rectLine);
	                }
	            });   
				return null;
			}
		}).execute("Test");
		
	}	
}
