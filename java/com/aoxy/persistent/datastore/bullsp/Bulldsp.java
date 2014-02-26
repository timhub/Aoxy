/******************************************************
 * Copyright Aoxy 2014 all right reserved.            *
 ******************************************************/
package com.aoxy.persistent.datastore.bullsp;

/**
 * Data bean for bull items, the properties are corresponding with the hbm XML files. 
 *
 * @author Wino
 */
public class Bulldsp {
	
	private String BId;
	private String FId;
	private String MId;
	private String TAction;
	private String Nation;
	private String Gender;
	private String TCondition;
	
	/**
	 * Check if the Id is empty.
	 * This method is used for checking Id value before inserting to DB.
	 * @return
	 */
	public boolean isIDEmpty(){
		if (BId == null || BId.trim().equals("")) {
			return false;
		}
		return true;
	}
	
	public String getBId()
	{
		return BId;
	}
	
	public void setBId(String TBId)
	{
		this.BId=TBId;
	}
	
	public String getFId()
	{
		return FId;
	}
	
	public void setFId(String TFId)
	{
		this.FId=TFId;
	}
	
	public String getMId()
	{
		return MId;
	}
	
	public void setMId(String TMId)
	{
		this.MId=TMId;
	}
	
	public String getTAction()
	{
		return TAction;
	}
	
	public void setTAction(String TAction)
	{
		this.TAction=TAction;
	}
	
	public String getNation()
	{
		return Nation;
	}
	
	public void setNation(String TNation)
	{
		this.Nation=TNation;
	}
	
	public String getGender()
	{
		return Gender;
	}
	
	public void setGender(String TGender)
	{
		this.Gender=TGender;
	}
	
	public String getTCondition()
	{
		return TCondition;
	}
	
	public void setTCondition(String TCondition)
	{
		this.TCondition=TCondition;
	}
}
