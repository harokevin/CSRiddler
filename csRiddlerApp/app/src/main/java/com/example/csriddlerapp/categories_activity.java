package com.example.csriddlerapp;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.HONEYCOMB) public class categories_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        
        //button dataStructures
        Button buttonDataStructures = (Button) findViewById(R.id.datastructures_button);
        buttonDataStructures.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent  = new Intent(getApplicationContext(), dataStructures.class);
				startActivity(intent);
				
			}//closing onClick()
        	
        });//closing buttonDataStructures onClickListener
        
        
        //button for code segments
        Button codeSegments = (Button) findViewById(R.id.code_button);
        codeSegments.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent  = new Intent(getApplicationContext(), codeSegments.class);
				startActivity(intent);
				
			}//closing onClick()
        	
        });//closing code segments button onClickListener
        
        
        //buttonAlgorithms
        Button algorithmButton = (Button) findViewById(R.id.algorithms_button);
        algorithmButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent  = new Intent(getApplicationContext(), algorithms.class);
				startActivity(intent);
				
			}//closing onClick()
        	
        });//closing buttonAlgorithms onClickListener
        
        
        //buttonAlgorithms
        Button puzzlesButton = (Button) findViewById(R.id.puzzles_button);
        puzzlesButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent intent  = new Intent(getApplicationContext(), puzzles.class);
				startActivity(intent);
				
			}//closing onClick()
        	
        });//closing buttonPuzzles onClickListener
        
        
        
        
        
    }//closing onCreat


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
