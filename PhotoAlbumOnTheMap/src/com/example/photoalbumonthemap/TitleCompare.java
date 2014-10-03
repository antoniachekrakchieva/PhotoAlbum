package com.example.photoalbumonthemap;

import java.util.Comparator;

public class TitleCompare implements Comparator<String>{
		public int compare(String one, String two){
		return one.compareTo(two);
		}
}
