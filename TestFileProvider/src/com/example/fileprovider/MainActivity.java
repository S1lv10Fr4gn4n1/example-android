package com.example.fileprovider;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private TextView textFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button_call_file);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType("image/jpg");
				startActivityForResult(intent, 1);
			}
		});
		
		this.textFile = (TextView) findViewById(R.id.text_file);
	}
	
	@SuppressWarnings("resource")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		
		Uri returnUri = data.getData();
		
//		Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
//		int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//		int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
//		returnCursor.moveToFirst();
		
		ParcelFileDescriptor mInputPFD;
		try {
			mInputPFD = getContentResolver().openFileDescriptor(returnUri, "r");
			System.out.println(mInputPFD);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		// Get a regular file descriptor for the file
		FileDescriptor fd = mInputPFD.getFileDescriptor();
		FileInputStream fileStream = new FileInputStream(fd);
		
		byte[] buffer = new byte[1024];
		StringBuilder sb = new StringBuilder();
		try {
			int lenght;
			while ((lenght = fileStream.read(buffer)) > 0) {
				sb.append(new String(buffer, 0, lenght).toString());
			}
			
			this.textFile.setText(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
