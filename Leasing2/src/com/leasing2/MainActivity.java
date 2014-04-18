package com.leasing2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.leasing2.dataentryfragment.*;
import com.leasing2.resultfragment.ResultFragment;
import com.leasing2.utilities.*;

public class MainActivity extends Activity 
	implements DataEntryFragment.OnButtonPressedListener	
	{
	
	//********* Devices events *********
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		KeepData.keepMainActivity(outState, findViewById(R.id.mainActivity)); 
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		KeepData.restoreMainActivity(savedInstanceState, findViewById(R.id.mainActivity)); 
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onValidateButtonPressed(CalculsEngine calc) {
		
		ResultFragment resultFrag = (ResultFragment) getFragmentManager().findFragmentById(R.id.ResultFragment);
		
		if (resultFrag != null)
		{
			resultFrag.DisplayResult(calc); 
		} else {
			throw new NullPointerException("The Fragment waas not Found" + R.id.ResultFragment); 
		}
		
	}

}
