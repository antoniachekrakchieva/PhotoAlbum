package com.example.photoalbumonthemap;

import java.util.Date;

public class ImageItem {

	private int id;
	
	private String src;
	private String title;
	private String description;
	
	private Date date;
    
    private double latitude;
    private double longitude;
    
    public ImageItem(int id){
    	this.id = id;
    	
    }
    
    public void setSrc(String src){
    	this.src = src;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }
    
    public void setCoordinates(double latitude, double longiude){
    	setLatitude(latitude);
    	setLongitude(longiude);
    }
    
    public void setDate(Date date){
    	this.date = date;
    }
    
    public void setLatitude(double latitude){
    	
    	this.latitude = latitude;
    }
    
    public void setLongitude(double longitude){
    	this.longitude = longitude;
    }
    
    public int getId(){
    	return this.id;
    }
    
    public String getTitle(){
    	return this.title;
    }
    
    public String getDesctiprion(){
    	return this.description;
    }
    
    public String getSrc(){
    	return this.src;
    }
    
    public Date getDate(){
    	return this.date;
    }
    
    public double getLatitude(){
    	return this.latitude;
    }
    
    public double getLongitude(){
    	return this.longitude;
    }
    
}
