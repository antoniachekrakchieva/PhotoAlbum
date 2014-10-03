package com.example.photoalbumonthemap;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EditDetailsActivity extends Activity {

	private Calendar calendar;
	private int day;
	private int month;
	private int year;
	private ImageItem currentImage;
	private EditText editTitle;
	private EditText editDescription;
	private TextView editDate;
	private EditText editCoordinates;
	private ImageView showImage;
	private int currentImageSrc;
	private ImageButton calendarButton;
	private InputMethodManager imm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_details);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		addFieldForImage();
		addFieldForCalendar();
		addFieldForTitle();
		addFieldForEditDescription();
		addTextFieldForDate();
	
	}
	private void addTextFieldForDate(){
		
		String date = currentImage.getDate().getDay() + "-" + currentImage.getDate().getMonth() + "-" + currentImage.getDate().getYear();
		editDate = (TextView) findViewById(R.id.textViewForDate);
		editDate.setText(date);
	}
	
	private void addFieldForEditDescription(){
		editDescription = (EditText) findViewById(R.id.editDescription);
		editDescription.setText(currentImage.getDesctiprion());
		editDescription.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				currentImage.setTitle(editDescription.getText().toString());
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}
		});
	}
	
	private void addFieldForCalendar(){
		
		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH);
		year = calendar.get(Calendar.YEAR);
		
		calendarButton = (ImageButton) findViewById(R.id.changeCalendarButton);
		calendarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				useDatePicker();
				
			}
		});

		calendarButton.setOnFocusChangeListener(new View.OnFocusChangeListener(){
		    @Override
		    public void onFocusChange(View v, boolean hasFocus)
		    {
		        if (!hasFocus){
		        	useDatePicker();
		        }
		    }
		});
	}
	
	private void addFieldForImage(){
		currentImage = ImageAlbum.getImageById(getIntent().getIntExtra("currentImageId", -1));
		currentImageSrc = getIntent().getIntExtra("currentImageSrc", -1);
		showImage = (ImageView) findViewById(R.id.imageView1);
		showImage.setImageResource(currentImageSrc);
	}
	
	private void addFieldForTitle(){
		editTitle = (EditText) findViewById(R.id.editTitle);
		editTitle.setText(currentImage.getTitle());
		editTitle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				currentImage.setTitle(editTitle.getText().toString());
		        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}
		});
	}
	
	private void useDatePicker(){
		DatePickerDialog datePicker = new DatePickerDialog(this, datePickerListener, currentImage.getDate().getYear(),
				currentImage.getDate().getMonth(), currentImage.getDate().getDay());
		datePicker.show();
	}
	

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
				currentImage.setDate(new Date(selectedYear,selectedMonth,selectedDay));
				int currentMonth = selectedMonth+1;
				editDate.setText(selectedDay + "-" + currentMonth + "-" + selectedYear);
	
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_details, menu);
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
