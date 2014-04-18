package com.leasing2.maps;

import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MapsViewFragment extends MapFragment {
	
	
    /*@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.maps_view_fragment);
        
        MapsUtils mapsutils = new MapsUtils();
        theMap = mapsutils.initMaps(getSupportFragmentManager());
        
        mapsutils.updatePlaces(INITIAL_LATITUDE, INITIAL_LONGITUDE, INITIAL_ZOOM);
        
        (new AsyncTask<String, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(String... params) {
				LatLng fromPosition = new LatLng(INITIAL_LATITUDE, INITIAL_LONGITUDE);
	            LatLng toPosition = new LatLng(55.749792, 37.632495);

	            GMapV2Direction md = new GMapV2Direction();

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
        
        
    }*/
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
    	
    	return inflater.inflate(com.leasing2.R.layout.maps_view_fragment, container, false);
    }
    
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}