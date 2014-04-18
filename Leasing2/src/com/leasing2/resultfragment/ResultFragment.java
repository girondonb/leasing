package com.leasing2.resultfragment;

import com.leasing2.R;
import com.leasing2.utilities.CalculsEngine;
import android.widget.TextView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(com.leasing2.R.layout.result_fragment, container, false);
	}
	
	public void DisplayResult(CalculsEngine calc)
	{
		((TextView) getView().findViewById(R.id.result_textview_dateendcontract)).setText(calc.GetEndContract()); 
		((TextView) getView().findViewById(R.id.result_textview_kilometersauthorised)).setText(calc.getTotalKilometers()); 
		((TextView) getView().findViewById(R.id.result_textView_kilometersyearly)).setText(calc.getAnnualKilometer());  
		((TextView) getView().findViewById(R.id.result_textview_kilometersmonthly)).setText(calc.getMonthlyKilomter()); 
		((TextView) getView().findViewById(R.id.result_textview_kilometersdaily)).setText(calc.getDailyKilomter()); 
		((TextView) getView().findViewById(R.id.result_textview_kilometersdailywithoutworkingjourney)).setText(calc.getDailyKilometerwithoutWorkingJourney()); 
		((TextView) getView().findViewById(R.id.result_textview_weekworkingkilometer)).setText(calc.getWeekWorkingKilometer()); 
		((TextView) getView().findViewById(R.id.result_textview_weekendkilometersifuseronlydoneworkingday)).setText(calc.getWeekEndKilometersIfUserOnlyDoneWorkingDay()); 
		((TextView) getView().findViewById(R.id.result_textview_percentageofadvancement)).setText(calc.getPercentageOfAdvancement()); 
	}
}
