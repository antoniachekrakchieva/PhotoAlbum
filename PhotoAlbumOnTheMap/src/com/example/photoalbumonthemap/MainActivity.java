package com.example.photoalbumonthemap;


import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageButton mapButton;
	RadioGroup sortElements;
	CheckBox checkBoxForSwitch;
	GridView gridviewGallery;
	ListView listViewGallery;
	private boolean isGridView = true;
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_view);
		createAlbum();
		addMapButtonListenerOnButton();
		addSortEvents();
		addCheckBoxEvents();
		addGridViewEvents();
		addListEvents();
		
	}
	@Override
	public void onResume(){
	    super.onResume();
	    reloadData();
	    if (checkBoxForSwitch.isChecked()){
	    	gridviewGallery.setVisibility(View.INVISIBLE);
	    	listViewGallery.setVisibility(View.VISIBLE);
	    }
	}

	public void reloadData(){
		gridviewGallery.setAdapter(new ImageAdapter(this));
		listViewGallery.setAdapter(new ImageAdapter(this));
	}
	
	public void addGridViewEvents(){
		gridviewGallery = (GridView) findViewById(R.id.gridViewGallery);
		gridviewGallery.setAdapter(new ImageAdapter(this));
		if (isGridView == false){
			gridviewGallery.setVisibility(View.INVISIBLE);
		}
	}
	
	public void addListEvents(){
		
		listViewGallery = (ListView) findViewById(R.id.listViewGallery);
		if (isGridView == true){
			listViewGallery.setVisibility(View.INVISIBLE);
		}
		listViewGallery.setAdapter(new ImageAdapter(this));
				
	}
	public void addCheckBoxEvents(){
		
		checkBoxForSwitch = (CheckBox) findViewById(R.id.checkBoxTableView);
		checkBoxForSwitch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkBoxForSwitch.isChecked()){
                	listViewGallery.setVisibility(View.VISIBLE);
                	gridviewGallery.setVisibility(View.INVISIBLE);
                }else{
                	listViewGallery.setVisibility(View.INVISIBLE);
                	gridviewGallery.setVisibility(View.VISIBLE);
                }
            }
        });
	}
	
	public void addMapButtonListenerOnButton() {
		 
		mapButton = (ImageButton) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new View.OnClickListener() {
			 
            public void onClick(View v) {

                Intent mapActivityIntent = new Intent(getApplicationContext(), MapActivity.class);
                mapActivityIntent.putExtra("EXTRA_SESSION_ID", "sds");
                startActivity(mapActivityIntent);
            }
        });
 
	}
	
	public void addSortEvents(){
		 sortElements = (RadioGroup) findViewById(R.id.sortElements);
		 sortElements.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			 @Override
			 public void onCheckedChanged(RadioGroup arg0, int id) {
				 if (id == R.id.sortByName){
					 ImageAlbum.sortByTitle();
					 reloadData();

				 } else {
					 ImageAlbum.sortByDate();
					 reloadData();
				 }
				 
			 }
		 });
	}
	
	@SuppressWarnings("deprecation")
	public void createAlbum(){
		ImageAlbum album = ImageAlbum.getInstance();
		if(album.getCountOfImages() == 0){
			ImageItem narodenT = new ImageItem(5);
			narodenT.setSrc("naroden");
			narodenT.setTitle("Народен театър  „Иван Вазов“");
			narodenT.setDescription("За начало на летоброенето на Народния театър се счита заповед на министъра.");
			narodenT.setDate(new Date(2014, 4, 25));

			narodenT.setCoordinates(42.693889, 23.326389);
			ImageAlbum.addElements(narodenT);
			
			ImageItem ndk = new ImageItem(2);
			ndk.setSrc("ndk");
			ndk.setTitle("Национален дворец на културата");
			ndk.setDescription("Най-голямата зала („Зала 1“) разполага с 3380 места");	
			ndk.setDate(new Date(2014, 2, 11));
			ndk.setCoordinates(42.684722, 23.318889);
			ImageAlbum.addElements(ndk);
			
			ImageItem iujenP = new ImageItem(4);
			iujenP.setSrc("iujen");
			iujenP.setTitle("Южен парк");
			iujenP.setDescription("В Южния парк има сцена, на която нерядко се изнасят безплатни концерти.");
			iujenP.setDate(new Date(2013, 4, 25));
			iujenP.setCoordinates(42.668727,23.308921);
			ImageAlbum.addElements(iujenP);
			ImageAlbum.sortByTitle();
			
			ImageItem fmi = new ImageItem(1);
			fmi.setSrc("fmi");
			fmi.setTitle("ФМИ");
			fmi.setDescription("ФМИ предлага обучение в образователно-квалификационна степен (ОКС) бакалавър и ОКС магистър");
			fmi.setDate(new Date(2012, 9, 11));

			fmi.setCoordinates(42.674538,23.330377);
			ImageAlbum.addElements(fmi);
			
			ImageItem borisova = new ImageItem(0);
			borisova.setSrc("borisova");
			borisova.setTitle("Борисова градина");
			borisova.setDescription("Борисовата градина е била разположена в покрайнините на столицата.");
			borisova.setDate(new Date(2011, 1, 1));

			borisova.setCoordinates(42.678686,23.341828);
			ImageAlbum.addElements(borisova);
			
			ImageItem ezero = new ImageItem(3);
			ezero.setSrc("ariana");
			ezero.setTitle("Езеро Ариана");
			ezero.setDescription("Ариана е изкуствено езеро в долната част Борисовата градина в София.");
			ezero.setDate(new Date(2013, 2, 14));

			ezero.setCoordinates(42.674538,23.330377);
			ImageAlbum.addElements(ezero);
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
