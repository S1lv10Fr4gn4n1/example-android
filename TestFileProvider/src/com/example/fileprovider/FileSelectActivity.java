package com.example.fileprovider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FileSelectActivity extends ActionBarActivity {
	
	private File privateRootDir;
	private File imagesDir;
	File[] imageFiles;
	String[] imageFilenames;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_file_select);
		
		this.listView = (ListView) findViewById(R.id.listview);
		
		// set up an Intent to send back to apps that request a file
		final Intent resultIntent = new Intent("com.example.fileprovider.FILESHARING");
		
		// get the files/ subdirectory of internal storage
		privateRootDir = getFilesDir();
		
		// get the files/images subdirectory;
		imagesDir = new File(privateRootDir, "images");
		if (!imagesDir.exists()) {
			imagesDir.mkdirs();
		}
		
		createFiles(imagesDir.getAbsolutePath());
		
		// get the files in the images subdirectory
		imageFiles = imagesDir.listFiles();
		// set the Activity's result to null to begin with
		this.setResult(Activity.RESULT_CANCELED, null);
		
		imageFilenames = new String[imageFiles.length];
		for (int i = 0; i < imageFiles.length; i++) {
			imageFilenames[i] = imageFiles[i].getName();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, imageFilenames);
		this.listView.setAdapter(adapter);
		
		this.listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				File requestFile = new File(imagesDir.getAbsolutePath() + "/" + imageFilenames[position]);
				try {
					Uri fileUri = FileProvider.getUriForFile(FileSelectActivity.this, "com.example.fileprovider.FILESHARING", requestFile);
					if (fileUri != null) {
						// Grant temporary read permission to the content URI
						resultIntent.setDataAndType(fileUri, getContentResolver().getType(fileUri));
						resultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
						setResult(Activity.RESULT_OK, resultIntent);
					} else {
						resultIntent.setDataAndType(null, "");
						setResult(RESULT_CANCELED, resultIntent);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				
				finish();
			}
		});
	}
	
	
	static final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus fringilla nisl eu odio egestas, at commodo lorem egestas. Sed posuere elit eu sagittis elementum. Aliquam sagittis et urna vel molestie. Donec quis velit diam. Integer dignissim vulputate orci, vitae facilisis erat auctor at. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum feugiat, est eu venenatis feugiat, augue velit accumsan leo, sed vulputate lorem mauris a libero. Nullam vel nibh in nisi ultricies ullamcorper. Phasellus id urna nulla. Maecenas id laoreet lorem. Nam quis metus mauris.";
	static final String lorem_2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n"+
								  "Duis tincidunt nibh sit amet mattis luctus.\n"+
								  "Phasellus sit amet massa eu orci accumsan aliquam quis a ante.";
	
	private void createFiles(String path) {
		for (int i = 0; i < 5; i++) {
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(path + "/file" + i + ".txt"));
				if (i%2==0) {
					writer.write("Hello World " + i +"\n" + lorem);
				} else {
					writer.write("Hello World " + i +"\n" + lorem_2);
				}
				
			} catch (IOException e) {} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {}
			}
		}
	}
	
}
