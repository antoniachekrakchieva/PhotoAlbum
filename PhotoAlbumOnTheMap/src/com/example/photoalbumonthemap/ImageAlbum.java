package com.example.photoalbumonthemap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ImageAlbum {

	private static ImageAlbum imageAlbum;
	private static ArrayList<ImageItem> images;
	private static ArrayList<ImageItem> imagesSortByDate;
	private static ArrayList<ImageItem> imagesSortByTitle;
	
    private ImageAlbum() {
    	images = new ArrayList<ImageItem>();
    	imagesSortByDate = new ArrayList<ImageItem>();
    	imagesSortByTitle = new ArrayList<ImageItem>();
    }

    public static ImageAlbum getInstance() {
        if (imageAlbum == null) {
        	imageAlbum = new ImageAlbum();
        }
        return imageAlbum;
    }
    
    public static void addElements(ImageItem image){
    	images.add(image);
    }
    
    public static ArrayList<ImageItem> getElements(){
    	return images;
    }
    
    public static int getCountOfImages(){
    	return images.size();
    }
    
    public static String getImageSrc(ImageItem image){
    	
    	return image.getSrc();
    	
    }
    
    public static void deleteItem(int position){
    	images.remove(position);
    }
    
    public static ImageItem getImageByPosition(int position){
    	
    	return images.get(position);
    }
    
    public static ImageItem getImageById(int id){
    	for (int i=0;i<images.size();i++){
    		if(images.get(i).getId() == id){
    			return images.get(i);
    		}
    	}
    	return null;
    }
    
    public static ImageItem getImageByDate(Date date){
    	for (int i=0;i<images.size();i++){
    		if(images.get(i).getDate() == date){
    			return images.get(i);
    		}
    	}
    	return null;
    }
    
    public static ImageItem getImageByTitle(String title){
    	for (int i=0;i<images.size();i++){
    		if(images.get(i).getTitle() == title){
    			return images.get(i);
    		}
    	}
    	return null;
    }
    
    public static void sortByDate(){
    	imagesSortByDate.clear();
    	ArrayList<Date> a = new ArrayList<Date>();
    	DateCompare compare = new DateCompare();
    	for (int i=0;i<images.size();i++){
    		a.add(images.get(i).getDate());
    	}
    	Collections.sort(a, compare);
    	
    	for (int i=0;i<a.size();i++){
    		imagesSortByDate.add(getImageByDate(a.get(i)));
    	}
    	images.clear();
    	images = imagesSortByDate;
		
    }
    
    public static void sortByTitle(){
    	imagesSortByTitle.clear();
    	ArrayList<String> a = new ArrayList<String>();
    	TitleCompare compare = new TitleCompare();
    	for (int i=0;i<images.size();i++){
    		a.add(images.get(i).getTitle());
    	}
    	Collections.sort(a, compare);
    	
    	for (int i=0;i<a.size();i++){
    		imagesSortByTitle.add(getImageByTitle(a.get(i)));
    	}
    	images.clear();
    	images = imagesSortByTitle;
    }
    
}

