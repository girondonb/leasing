package com.leasing2.utilities;

import java.util.Calendar;

import android.text.format.DateFormat;

public class CalculsEngine {
	
	private static final int NumberOfWorkingDay = 220;
	
	private double kilometerNumberToWork; 
	private double kilometerNumberPerso;
	private double contractDuration;
	private double kilometerNumberCurrent;
	private Calendar contractBeginningDate;
	
	
	/**
	 * Constructor of the class --> No other constructor authorised for the moment
	 * @param kilomterNumberToWork : The number of kilometer that employee have to do to go to work
	 * @param kilomterNumberPerso: The Number of kilometer the employee can do in one year
	 * @param contractDuration : time of the contract
	 * @param contractBeginningDate : The date of the beginning of the contract
	 */
	public CalculsEngine(double kilometerNumberToWork, double kilometerNumberPerso, double contractDuration, double kilometerNumberCurrent, Calendar contractBeginningDate)
	{
		this.kilometerNumberToWork = kilometerNumberToWork; 
		this.kilometerNumberPerso = kilometerNumberPerso;
		this.contractDuration = contractDuration; 
		this.contractBeginningDate = contractBeginningDate;
		this.kilometerNumberCurrent = kilometerNumberCurrent; 
	}
	
	/**
	 * Get the ending date of the contract
	 * @return ending date of the contract
	 */
	public String GetEndContract()
	{	
		
		Calendar calendar = (Calendar)contractBeginningDate.clone(); 
		calendar.add(Calendar.YEAR, (int) contractDuration); 
		return DateFormat.format("dd/MM/yyyy", calendar).toString(); 
	}
	
	/**
	 * Get the total number of kilometer that user can do during all the leasing period
	 * @return number of kilometer
	 */
	public String getTotalKilometers()
	{
		return Double.toString((((kilometerNumberToWork * 2) * NumberOfWorkingDay) * contractDuration) + (kilometerNumberPerso * contractDuration)) ;
	}
	
	/**
	 * Get the number of annual kilometer the user can do.
	 * @return number of kilometer
	 */
	public String getAnnualKilometer()
	{
		return Double.toString(((kilometerNumberToWork * 2) * NumberOfWorkingDay) + kilometerNumberPerso);
	}
	
	/**
	 * Get the number of kilometer that a user can do during one month
	 * @return number of kilometer
	 */
	public String getMonthlyKilomter()
	{
		return Double.toString(Double.parseDouble(getAnnualKilometer())/12) ;
	}
	
	/**
	 * Get the number of kilometer that a user can do for one day
	 * @return number of kilometer
	 */
	public String getDailyKilomter()
	{
		return Double.toString(Double.parseDouble(getAnnualKilometer())/365);
	}
	
	/**
	 * get the number of kilometers that user can do for one day except working journey
	 * @return number of kilometers
	 */
	public String getDailyKilometerwithoutWorkingJourney()
	{
		return Double.toString(Double.parseDouble(getDailyKilomter()) - (kilometerNumberToWork * 2)); 
	}
	/**
	 * get the number of kilometers that user will do in one working week
	 * @return number of kilometers
	 */
	public String getWeekWorkingKilometer()
	{
		return Double.toString((kilometerNumberToWork * 2) * 5); 
	}
	
	/**
	 * get the number of kilometer that user can do except working kilometers in one week
	 * @return number of kilometer
	 */
	public String getWeekEndKilometersIfUserOnlyDoneWorkingDay()
	{
		return Double.toString(Double.parseDouble(getDailyKilometerwithoutWorkingJourney()) * 7); 
	}
	
	/**
	 * get the percentage of advancement about current kilometers.
	 * @return percentage of advanvement/exceeding
	 */
	public String getPercentageOfAdvancement()
	{
		//get the difference between now and beginning date 
		Calendar calendarNow = Calendar.getInstance();
		
		int result1 = (int)( (calendarNow.getTime().getTime() - contractBeginningDate.getTime().getTime()) / (1000 * 60 * 60 * 24)); 
		
		//get the difference between ending date and beginning date 
		Calendar calendarEnding  = (Calendar)contractBeginningDate.clone(); 
		calendarEnding.add(Calendar.YEAR, (int) contractDuration);
		
		int result2 = (int)((calendarEnding.getTime().getTime() - contractBeginningDate.getTime().getTime()) / (1000 * 60 * 60 * 24));
		
		//Get the number of kilometer user will have done in the end of the contract
		double finalKilometerEstimation = (kilometerNumberCurrent / result1) * (result2); 
		
		//Get the final result to display it
		double finalResult = ((finalKilometerEstimation / Double.parseDouble(getTotalKilometers()))* 100) - 100; 
		
		if (finalResult > 0)
			return Double.toString(finalResult) + "% (dépassement)";
		else 
			return Double.toString(finalResult) + "% (tempérence)";
	}
}
