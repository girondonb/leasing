package com.leasing2.utilities;


import android.app.*;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapFragment;
import com.leasing2.R;

public class MapsUtils {
	private GoogleMap theMap;
	
	/**
     * function used to initialize the map view
     */
    public GoogleMap initMaps(FragmentManager fragmentManager)
    {
    	if (theMap == null)
    	{
    		FragmentManager myFM = fragmentManager;	
    		theMap  = ((MapFragment) myFM.findFragmentById(R.id.map)).getMap();
    		if (theMap != null)
    		{
    			theMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); 
    			return theMap;
    		}
    	}
    	return null; 
    }
    
    /**
     * function used to update the camera position
     */
    public void updatePlaces(double latitude, double longitude, float zoom)
    { 
    	if (theMap != null)
		{
	    	// set the latitude and longitude (LUXEMBOURG)
	    	LatLng lastLatLng = new LatLng(latitude, longitude);
	    	
	    	//Center the camera on the LUXEMBOURG
	    	theMap.moveCamera(CameraUpdateFactory.newLatLng(lastLatLng)); 
	    	theMap.animateCamera(CameraUpdateFactory.zoomTo(zoom), 2000, null); 
		} else {
			throw new NullPointerException(); 
		}
    } 
}
