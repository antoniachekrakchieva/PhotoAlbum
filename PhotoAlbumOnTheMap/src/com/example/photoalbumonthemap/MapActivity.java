package com.example.photoalbumonthemap;


import java.lang.reflect.Field;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements LocationListener, OnMarkerDragListener, OnMapClickListener  {


	private GoogleMap map;
    LatLng myPosition;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		SupportMapFragment fragmentMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = fragmentMap.getMap();
        map.setMyLocationEnabled(true);
        map.setOnMarkerDragListener(this);
        map.setOnMapClickListener(this);
        
        Criteria criteria = new Criteria();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(criteria, true);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Location currentLocation = locationManager.getLastKnownLocation(provider);
        double latitude;
        double longitude;
        if (currentLocation != null){
        	latitude = currentLocation.getLatitude();
        	longitude = currentLocation.getLongitude();
        } else {
        	latitude = 42.696552;
        	longitude = 23.32601;
        }
        

        LatLng latLng = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));       
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
        
        addMarkers();
	}
	
	public void addMarkers(){
		
		for (int i=0;i<ImageAlbum.getCountOfImages();i++){
			ImageItem currenImage = ImageAlbum.getImageByPosition(i);
			LatLng markerPossition = new LatLng(currenImage.getLatitude(), currenImage.getLongitude());
			map.addMarker(new MarkerOptions()
							.position(markerPossition)
							.title(currenImage.getTitle())
							.icon(BitmapDescriptorFactory.fromResource(getImageName(currenImage)))
							.draggable(true));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
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


	@Override
	public void onLocationChanged(Location arg0) {
		
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		int currentMarker1 = Integer.parseInt(arg0.getId().substring(1));
		LatLng dragPosition = arg0.getPosition();
        double dragLat = dragPosition.latitude;
        double dragLong = dragPosition.longitude;
        ImageItem currenImage = ImageAlbum.getImageByPosition(currentMarker1);
        currenImage.setCoordinates(dragLat, dragLong);
        
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		
	}

	@Override
	public void onMapClick(LatLng point) {
		map.addMarker(new MarkerOptions()
        .position(point)
        .draggable(true));
	}
	private int getImageName(ImageItem currentImage) {
		String imageName = currentImage.getSrc();
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
		    return drawableId;

	}
}
