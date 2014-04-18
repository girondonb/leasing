package com.leasing2.dataentryfragment;

import java.util.Calendar;

import com.leasing2.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.leasing2.maps.ActivityMaps;
import com.leasing2.utilities.CalculsEngine;;

public class DataEntryFragment extends Fragment {
	OnButtonPressedListener mCallback; 
	
	public interface OnButtonPressedListener
	{
		 public void onValidateButtonPressed(CalculsEngine calc); 
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		try {
			mCallback = (OnButtonPressedListener) activity; 
		} 
		catch (ClassCastException ex)
		{
			throw new ClassCastException(activity.toString() + " have to implement OnButtonPressedListener interface"); 
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 
		return inflater.inflate(com.leasing2.R.layout.data_entry_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		generateActions();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	private void generateActions()
	{
		//Action button to validate the form
		Button validate = (Button) getView().findViewById(R.id.data_entry_button_validate); 
		validate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Calendar calendar = Calendar.getInstance(); 
				calendar.set(((DatePicker) getView().findViewById(R.id.data_entry_datepicker_beginningdate)).getYear(), ((DatePicker) getView().findViewById(R.id.data_entry_datepicker_beginningdate)).getMonth(), ((DatePicker) getView().findViewById(R.id.data_entry_datepicker_beginningdate)).getDayOfMonth());
				String ed1 = ((EditText) getView().findViewById(R.id.data_entry_edittext_dailykilometers)).getText().toString(); 
				String ed2 = ((EditText) getView().findViewById(R.id.data_entry_edittext_annualypersonalkilometer)).getText().toString(); 
				String ed3 = ((EditText) getView().findViewById(R.id.data_entry_edittext_numberofyears)).getText().toString(); 
				String ed4 = ((EditText) getView().findViewById(R.id.data_entry_editText_currentnumberofkilometer)).getText().toString(); 
				
				CalculsEngine calc = new CalculsEngine(Double.parseDouble(ed1), Double.parseDouble(ed2),Double.parseDouble(ed3), Double.parseDouble(ed4), calendar); 
				
				mCallback.onValidateButtonPressed(calc); 
				
				
			}
		});
		
		Button maps = (Button) getView().findViewById(R.id.data_entry_button_maps); 
		maps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//try{
					Intent intent = new Intent(getActivity(), ActivityMaps.class);
					startActivity(intent);
				/*}catch(Exception e){
					throw new ClassCastException(getActivity().toString() + "can't call maps API."); 
				}*/
				
			}
			
		});
	}
}
