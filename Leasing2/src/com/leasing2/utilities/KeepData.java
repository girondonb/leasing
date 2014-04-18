package com.leasing2.utilities;

import java.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;

/**
 * Class used to keep all the data of a view. If there is an add to do add the component type you want to save in the routine. 
 * @author Benoit
 *
 */
public class KeepData {
	
	/**
	 * Function used to keep all the data of the main activity 
	 * @param savedInstanceState the bundle passed as reference
	 * @param mainView the layout passed as reference
	 */
	public static void keepMainActivity(Bundle savedInstanceState, View mainView)
	{
		//check the class of the layout
		if (mainView.getClass() == ScrollView.class)
		{
			//the mainview containse two linear layouts
			for(int i = 0; i < ((ScrollView) mainView).getChildCount() ; i++)
			{
				//So we test the linear layout here
				if (((ScrollView) mainView).getChildAt(i).getClass() == LinearLayout.class)
				{
					LinearLayout layout = (LinearLayout) ((ScrollView) mainView).getChildAt(i);
					//We have as follow to iterate on the linear layout intern views
					for(int j = 0; j < layout.getChildCount(); j++){
						KeepData.keep(savedInstanceState, (LinearLayout) layout.getChildAt(j));
					}
				}
			}
		}
	}
	
	/**
	 * function used to keep the data when user change orientation
	 * @param savedInstanceState the bundle passed as reference
	 * @param layout the layout passed as reference
	 */
	private static void keep(Bundle savedInstanceState, View layout)
	{
		//check the class of the layout
		if (layout.getClass() == LinearLayout.class)
		{
			//We have to create a linear layout for convenience
			LinearLayout linearLayout = (LinearLayout) layout;
			//Iterate on the childs in the linear layout
			for (int i = 0; i < linearLayout.getChildCount(); i++)
			{
				//Check the class of the child - that ruuuuuuuuules !!!
				if(linearLayout.getChildAt(i).getClass() == EditText.class && ((EditText)linearLayout.getChildAt(i)).getTag() != null)
				{
					//Save The instance State with a putString --> (string, String)
					savedInstanceState.putString(((EditText)linearLayout.getChildAt(i)).getTag().toString(), ((EditText)linearLayout.getChildAt(i)).getText().toString());
				}
				if(linearLayout.getChildAt(i).getClass() == DatePicker.class && ((DatePicker)linearLayout.getChildAt(i)).getTag() != null)
				{
					//Save the instance state with a putString --> (String, String)
					savedInstanceState.putString(((DatePicker)linearLayout.getChildAt(i)).getTag().toString(), getDateToString((DatePicker) linearLayout.getChildAt(i))); 
				}
				if(linearLayout.getChildAt(i).getClass() == TextView.class && ((TextView)linearLayout.getChildAt(i)).getTag() != null)
				{
					//Save the instance state with a putString --> (String, String)
					savedInstanceState.putString(((TextView)linearLayout.getChildAt(i)).getTag().toString(), ((TextView)linearLayout.getChildAt(i)).getText().toString()); 
				}
			}
		}
	}
	
	/**
	 * Function used to restore all the data of the main activity
	 * @param savedInstanceState the bundle passed as reference
	 * @param layout the layout passed as reference
	 */
	public static void restoreMainActivity(Bundle savedInstanceState, View mainView)
	{
		//check the class of the layout
		if (mainView.getClass() == ScrollView.class)
		{
			//the mainview contains two linear layouts
			for(int i = 0; i < ((ScrollView) mainView).getChildCount() ; i++)
			{
				//So we test the linear layout here
				if (((ScrollView) mainView).getChildAt(i).getClass() == LinearLayout.class)
				{
					LinearLayout layout = (LinearLayout) ((ScrollView) mainView).getChildAt(i);
					for(int j = 0; j < layout.getChildCount(); j++){
						KeepData.restore(savedInstanceState, (LinearLayout) layout.getChildAt(j));
					}
				}
			}
		}
	}
	
	/**
	 * Function used to restore the data when user change orientation
	 * @param savedInstanceState the bundle passed as reference
	 * @param layout the layout passed as reference
	 */
	private static void restore(Bundle savedInstanceState, View layout)
	{
		//check the class of the layout
		if (layout.getClass() == LinearLayout.class)
		{
			//We have to create a linear layout for convenience
			LinearLayout linearLayout = (LinearLayout) layout;
			//iterate on the children of the linear layout
			for(int i = 0; i < linearLayout.getChildCount(); i++)
			{
				//check the type of field
				if (linearLayout.getChildAt(i).getClass() == EditText.class && ((EditText)linearLayout.getChildAt(i)).getTag() != null)
				{
					//Fill the editText with the value in the savedInstanceState
					((EditText)linearLayout.getChildAt(i)).setText(savedInstanceState.getString((String)((EditText)linearLayout.getChildAt(i)).getTag())); 
				}
				if (linearLayout.getChildAt(i).getClass() == DatePicker.class && ((DatePicker)linearLayout.getChildAt(i)).getTag() != null)
				{
					//We have to create a calendar to fill the datePicker and set the date
					Calendar calendar = getStringToDate(savedInstanceState.getString((String)((DatePicker)linearLayout.getChildAt(i)).getTag())); 
					
					//Fill the datePicker with the calendar
					((DatePicker)linearLayout.getChildAt(i)).updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				}
				if (linearLayout.getChildAt(i).getClass() == TextView.class && ((TextView)linearLayout.getChildAt(i)).getTag() != null)
				{
					//Fill the editText with the value in the savedInstanceState
					((TextView)linearLayout.getChildAt(i)).setText(savedInstanceState.getString((String)((TextView)linearLayout.getChildAt(i)).getTag())); 
				}
			}
		}
	}
	
	/**
	 * Use That method to stock the datepicker field value to the storage
	 * @param datePicker the datPicker to Stringify
	 * @return the stringified date
	 */
	private static String getDateToString(DatePicker datePicker)
	{
		//Set the calendar to get the instance
		Calendar calendar = Calendar.getInstance(); 
		//Set the date of the calendar
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()); 
		
		//Return the formatted date
		return DateFormat.format("yyyy/MM/dd", calendar).toString(); 
	}
	
	/**
	 * Use that method when data return from the store to recreate calendar. 
	 * To avoid problems with performance create a variable and call getStringToDate to store calendar in the value.
	 * It permit to avoid unuseful computing
	 * @param stringToConvert The string to Datify
	 * @return The datefield string
	 */
	private static Calendar getStringToDate(String stringToConvert)
	{
		//Create a calendar object and gt the instance
		Calendar calendar = Calendar.getInstance(); 
		//Set the date of the calendar from the string
		calendar.set(Integer.parseInt(stringToConvert.substring(0, 4)), Integer.parseInt(stringToConvert.substring(5, 7)) - 1, Integer.parseInt(stringToConvert.substring(8, 10))); 
		
		//Return the created calendar. 
		return calendar; 
	}
}
