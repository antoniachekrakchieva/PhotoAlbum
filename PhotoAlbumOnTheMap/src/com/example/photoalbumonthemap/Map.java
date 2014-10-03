package com.example.photoalbumonthemap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class Map {
	
	public static String getAddressFromCoordinates(Context context, double latitude, double longitude){
		Geocoder geoCoder = new Geocoder(context, Locale.ENGLISH);
		List<Address> list = null;
		try {
			list = geoCoder.getFromLocation(latitude, longitude, 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
        if (list != null & list.size() > 0) {
            Address address = list.get(0);
            return address.getThoroughfare();
        }
        return "";
	}
}
