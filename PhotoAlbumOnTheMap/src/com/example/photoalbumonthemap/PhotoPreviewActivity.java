package com.example.photoalbumonthemap;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoPreviewActivity extends Activity{

	private int currentImageId;
	private ArrayList<Integer> imagesList;
	private HorizontalScrollView imagesScrollView;
	private int height;
	private int width;
	private LinearLayout ll;
	private LinearLayout.LayoutParams parms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_preview);
		String currentImageSrc = getIntent().getStringExtra("imagePossition");
		currentImageId = getIntent().getIntExtra("imageId", -1);
		imagesList = getIntent().getIntegerArrayListExtra("imageList");
		addImageResoursec();
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		
		addButtons();
		
		imagesScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		parms = new LinearLayout.LayoutParams(width,height/2);
		ll = (LinearLayout) findViewById(R.id.scrollViewLinearLayour);
		for (int i=0; i< imagesList.size();i++){
			ImageView image = new ImageView(this);
			ll.addView(image);
			image.setImageResource(imagesList.get(i));
			image.setLayoutParams(parms);
			
		}
		imagesScrollView.setBackgroundColor(-16777216);
		imagesScrollView.post(new Runnable() {
            public void run() {
            	imagesScrollView.scrollTo(width * currentImageId, 0);
            }
		});
		
		
	}
	private void addImageResoursec(){
		
		imagesScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		parms = new LinearLayout.LayoutParams(width,height/2);
		ll = (LinearLayout) findViewById(R.id.scrollViewLinearLayour);
		for (int i=0; i< imagesList.size();i++){
			ImageView image = new ImageView(this);
			ll.addView(image);
			image.setImageResource(imagesList.get(i));
			image.setLayoutParams(parms);
			
		}
		
	}
	
	private void addButtons(){
		Button buttonEdit = (Button) findViewById(R.id.editButton);
		buttonEdit.setOnClickListener(setEditMode);
		
		Button buttonDel = (Button) findViewById(R.id.deleteButton);
		buttonDel.setOnClickListener(deleteObject);
	}
	
	private OnClickListener deleteObject = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ImageAlbum.deleteItem(imagesScrollView.getScrollX() / width);
			imagesList.remove(imagesScrollView.getScrollX() / width);
			ll.removeAllViews();
			addImageResoursec();
			
			
		}
	};

	private OnClickListener setEditMode = new OnClickListener() {
	    public void onClick(View v) {

	    	Intent i = new Intent(getApplicationContext(), EditDetailsActivity.class);
	    	i.putExtra("currentImageId", imagesScrollView.getScrollX() / width);
	    	i.putExtra("currentImageSrc", imagesList.get(imagesScrollView.getScrollX() / width));
            startActivity(i);
	    }
	};
	
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    
	    outState.putIntArray("ARTICLE_SCROLL_POSITION",
	            new int[]{ imagesScrollView.getScrollX(), imagesScrollView.getScrollY()});
	}
	
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    final int[] position = savedInstanceState.getIntArray("ARTICLE_SCROLL_POSITION");

	    if(position != null)
	    	imagesScrollView.post(new Runnable() {
	            public void run() {
	            	imagesScrollView.scrollTo(position[0], position[1]);
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.photo_preview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
