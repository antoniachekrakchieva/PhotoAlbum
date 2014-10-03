package com.example.photoalbumonthemap;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.internal.cu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<Integer> imageList;
	
	public ImageAdapter(Context context){
		mContext = context;
		imageList = new ArrayList<Integer>();
		getImageName();
	}
	
	public int getCount(){
		return ImageAlbum.getCountOfImages();
	}
	
	public Object getItem(int position){
		return null;
	}

	public long getItemId(int position){
		return 0;
	}
	
	public View getView(final int position, View convertView, ViewGroup parent){
		
		int currentOrientation = mContext.getResources().getConfiguration().orientation;
		ImageItem currentImage = ImageAlbum.getImageByPosition(position);
		if (parent instanceof GridView){
			ImageView imageView;
			DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
			int width = metrics.widthPixels;
			int height = metrics.heightPixels;
			if (convertView == null){
				imageView = new ImageView(mContext);
				imageView.setId(currentImage.getId());
				imageView.setImageResource(imageList.get(position));
				if (currentOrientation == 1){
					imageView.setLayoutParams(new GridView.LayoutParams(width / 3 - 10, 150));
				} else {
					imageView.setLayoutParams(new GridView.LayoutParams(width / 5, 150));
				}
				
				
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setPadding(1, 1, 1, 1);
			imageView.setOnClickListener(goToPhotoPrevieView);
			imageView.setBackgroundColor( -16777216);
			imageView.setLongClickable(true);
			imageView.setOnLongClickListener(showAlerDialog);
			return imageView;
			
		} else {
			LayoutInflater inflater = (LayoutInflater) mContext
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.gallery_listview_row, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.textView1);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
			textView.setText(currentImage.getTitle());
			imageView.setImageResource(imageList.get(position));
			rowView.setOnClickListener(goToPhotoPrevieView);
			rowView.setLongClickable(true);
			rowView.setOnLongClickListener(showAlerDialog);
			rowView.setId(currentImage.getId());
			return rowView;
		}
		
	}
	
	private OnClickListener goToPhotoPrevieView = new OnClickListener() {
	    public void onClick(View v) {
	    	Intent i = new Intent(mContext.getApplicationContext(), PhotoPreviewActivity.class);
	    	i.putExtra("imageList", imageList);
	    	i.putExtra("imageId", v.getId());
            mContext.startActivity(i);
	    }
	};
	
	private OnLongClickListener showAlerDialog = new OnLongClickListener(){
		
		@Override
		public boolean onLongClick(View v) {
			ImageItem clickedImage = ImageAlbum.getImageById(v.getId());
			String address = Map.getAddressFromCoordinates(mContext, clickedImage.getLatitude(), clickedImage.getLongitude());
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
			alertDialogBuilder.setTitle(clickedImage.getTitle());
			alertDialogBuilder
				.setMessage(clickedImage.getDesctiprion() + "\nАдрес: " +  address)
				.setCancelable(false)
				.setPositiveButton("Back",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
		return true;
		}
	};
	
	
	private void getImageName() {
		for(int i=0;i<ImageAlbum.getCountOfImages();i++){
			String imageName = ImageAlbum.getElements().get(i).getSrc();
		    Class res = R.drawable.class;
		    Field field = null;
			try {
				field = res.getField(imageName);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		    int drawableId = 0;
			try {
				drawableId = field.getInt(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		    imageList.add(drawableId);
		}

	}

}
