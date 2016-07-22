package com.example.csriddlerapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class codeSegments extends ActionBarActivity {

	//testing for imageView
	ImageView imageDetail;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix(); 
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2; 
	int mode = NONE;

	
		//main array and a tester array
		String[] mainArrayFile;
		int[] testerArray;
		//counter for how many spots have been taken in the tester array
		int numOfUsedElements=0;
		
		//string array for imageID's
		String[] imageIDS;
		int randomNumbers;
	
	
	public void showImage(String imageId){

		//getting the parameter and using it find corresponding image
		int resID = getResources().getIdentifier(imageId, "drawable", getApplicationContext().getPackageName());
		
        //testing for zoom in images
        imageDetail = (ImageView) findViewById(R.id.imageView1); 
        imageDetail.setImageResource(resID);
        /** * set on touch listner on image */ 
        imageDetail.setOnTouchListener(new View.OnTouchListener() { 
        	@Override public boolean onTouch(View v, MotionEvent event) {
        		ImageView view = (ImageView) v; 
        		System.out.println("matrix=" + savedMatrix.toString()); 
        		switch (event.getAction() & MotionEvent.ACTION_MASK) { 
        		case MotionEvent.ACTION_DOWN: savedMatrix.set(matrix); 
        		startPoint.set(event.getX(), event.getY()); 
        		mode = DRAG; break; 
        		case MotionEvent.ACTION_POINTER_DOWN: oldDist = spacing(event); 
        		if (oldDist > 10f) {
        			savedMatrix.set(matrix);
        			midPoint(midPoint, event);
        			mode = ZOOM; } 
        		break; 
        		case MotionEvent.ACTION_UP: 
        			case MotionEvent.ACTION_POINTER_UP: mode = NONE;
        			break; 
        			case MotionEvent.ACTION_MOVE: 
        				if (mode == DRAG) 
        				{ matrix.set(savedMatrix); 
        				matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
        				} else if (mode == ZOOM) {
        					float newDist = spacing(event);
        					if (newDist > 10f) { 
        						matrix.set(savedMatrix);
        						float scale = newDist / oldDist;
        						matrix.postScale(scale, scale, midPoint.x, midPoint.y); }
        					} 
        				break; }
        		view.setImageMatrix(matrix); return true; 
        		} @SuppressLint("FloatMath") private float spacing(MotionEvent event) { 
        			float x = event.getX(0) - event.getX(1);
        			float y = event.getY(0) - event.getY(1);
        			return FloatMath.sqrt(x * x + y * y); 
        			}
        		private void midPoint(PointF point, MotionEvent event) {
        			float x = event.getX(0) + event.getX(1);
        			float y = event.getY(0) + event.getY(1); point.set(x / 2, y / 2);
        			} 
        		});

	}//closing showImage()
	
	
	
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.code_segments);
        returnArray();
        run();
    }//closing onCreate()
    
    
   
    
    
    public int getNumber(){
    	return randomNumbers;  
    }
    
    
    public void run(){		
		String[] array = mainArrayFile;
      //getting the size of the array
      int elementsInArray = array.length;
      int nextQuestionElement = randomMethod(elementsInArray);
      
      if(testerArray[nextQuestionElement]==1){//checking if the question been used this run
   	
   			if(numOfUsedElements==elementsInArray){
   				returnArray();//restarting the file and reseting the answered questions
   				numOfUsedElements=0;
   				run();
   			}else{
   				run();
   			}//closing if-else statement
      }else{
   			//initializing the used element to 1 and incrementing the counter
    	  testerArray[nextQuestionElement] =1;
    	  numOfUsedElements++;
    	  
    	  //setting the question
    	  TextView questionBox = (TextView)findViewById(R.id.textView1);
          questionBox.setText(array[nextQuestionElement]);
    	  
      }//closing main if-else
   
      Button AasnwerButton = (Button) findViewById(R.id.answerButton);
      AasnwerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				showImage(imageIDS[getNumber()]);
  		}//closing onClick()
      });//closing answerButton onClickListener
   
   
      
      Button nextButton = (Button) findViewById(R.id.nextQuestion);
      nextButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				//reseting the imageView
				ImageView image = (ImageView) findViewById(R.id.imageView1); 
		        image.setImageResource(0);
				
				run();
			}//closing onClick()
      });//closing nextButton onClickListener
      
    }//closing run
    
    
    
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
    
    
    
  //method to parse text file full of strings
   	public String[] parseFile(Context ctx, int resId){

   		String line = null;
           List<String> list = new ArrayList<String>();
           String[] temp;
           String[] stringArray = list.toArray(new String[list.size()]);

   			try {
   				 InputStream inputStream = ctx.getResources().openRawResource(resId);

   			        InputStreamReader inputreader = new InputStreamReader(inputStream);
   			        BufferedReader br = new BufferedReader(inputreader);
   				
   			
   		        while ((line = br.readLine()) != null) {
   		            temp = line.split("#");
   		            if (temp.length > 0) {
   		                list.addAll(Arrays.asList(temp));
   		            }//closing if 
   		        }//closing while-loop
   		     
   		        //an array to have each of the lines from text as an element
   		        stringArray = list.toArray(new String[list.size()]);
   			} catch (IOException e) {
   			}//closing try-catch clause

   		return stringArray;
   	}//closing parseFile()
    
    
  //method to select an array element randomly
  	public int randomMethod(int arraySize){
  		Random random = new Random();
  		int randomElement = random.nextInt(arraySize);
  		randomNumbers= randomElement;
  		return randomElement;
  		
  	}//closingrandomMethod
  	
 
  	

  	
  	public void returnArray(){//method is ran when the activaty is first started
  		 //getting an array of strings from txt file
  		 String[] mainArray = parseFile(this, R.raw.code_segements);
  		 mainArrayFile = mainArray;
  		 int arraySize = mainArray.length;
  		 System.out.println("array size is.:"+arraySize);
  		 
  		 //initializing the tester array and setting it to 0
  		 int[] someArray = new int[arraySize]; 
  		 for(int i=0; i<arraySize; i++){
  			 someArray[i]=0;
  			 System.out.println("inside the first for loop:  "+someArray[i]);
  			
  		 }//closing for-loop
  		 testerArray = someArray;
  		 
  		 String[] testing = new String[arraySize];
  		 String imageName ="codeimage";
  		 for(int i=0; i<arraySize; i++){
  			 StringBuilder stringBuilder = new StringBuilder();
  			 stringBuilder.append(imageName+i);
  			 System.out.println(stringBuilder);
  			 testing[i] = stringBuilder.toString();
  			 
  		 }//array for image id's
  		 
  		 imageIDS = testing;
  	}//closing returnArray()
    
    
}//closing class
