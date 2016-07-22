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
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class puzzles extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //creating an array from the file
        returnArray();        
        run();
    }//closing onCreate()
    
    
    
	//method to select an array element randomly
	public int randomMethod(int arraySize){
		Random random = new Random();
		int randomElement = random.nextInt(arraySize);
		return randomElement;
	}//closingrandomMethod
	
	
	
	//main array and a tester array
		String[] mainArrayFile;
		int[] testerArray;
		//counter for how many spots have been taken in the tester array
		int numOfUsedElements=0;
		
		
		
		
		public void returnArray(){//method is ran when the activaty is first started
			 //getting an array of strings from txt file
			 String[] mainArray = parseFile(this, R.raw.puzzles);
			 mainArrayFile = mainArray;
			 int arraySize = mainArray.length;
			 
			 //initializing the tester array and setting it to 0
			 int[] someArray = new int[arraySize]; 
			 for(int i=0; i<arraySize; i++){
				 someArray[i]=0;
				 i++;
			 }//closing for-loop
			 testerArray = someArray;
		}//closing returnArray()
		
		
		//method to gotoThe next question
		public void run(){
			 //getting an array of strings from text file
		   //String[] array = parseFile(this, R.raw.datastructures);
			
			String[] array = mainArrayFile;
			
		   //System.out.println("elements in the file should be 3 but has :"+array.length);
	   	
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
	       
	       //splitting the retrieved random number 
	       String currentElement = array[nextQuestionElement];
	       String[] currentQuestionSlip = currentElement.split("/");
	      
	       if(currentQuestionSlip ==null){//checking if the new line is empty 
	    	   System.out.println("Error ,, not enough elements in array!!");
	    	   
	       }else{
	       final String questionString = currentQuestionSlip[0];
	       final String asnwerString = currentQuestionSlip[1];
	       //checking if true or false
	       String lastChar = currentElement.substring(currentElement.length()-2);
	       boolean trueAndFalse = currentElement.contains("*");
	       boolean multipleChoice = currentElement.contains("?");
	       boolean questionAnswer = currentElement.contains("!");
	       System.out.println(asnwerString);
	       if(multipleChoice){//if multiple choice question
	    	   setContentView(R.layout.sample);
	      	 //setting a textView for the question
	           TextView questionView = (TextView)findViewById(R.id.multipleChoiceQuestion);
	           questionView.setText(questionString);
	           TextView feedbackView = (TextView)findViewById(R.id.response);
	           feedbackView.setText("");
	           
	           final String[] multipleChoiceAnswer = asnwerString.split("\\$");//5 tokens for every multiple choice question
	           
	           System.out.println(multipleChoiceAnswer.length+"elelemtns in the answer array"
	        		   +multipleChoiceAnswer[0]);
	           
	           
	           final RadioGroup choices = (RadioGroup) findViewById(R.id.choices);
	           RadioButton buttonA =(RadioButton) findViewById(R.id.aButton);
	           buttonA.setText(multipleChoiceAnswer[0]);
	           RadioButton buttonB =(RadioButton) findViewById(R.id.bButton);
	           buttonB.setText(multipleChoiceAnswer[1]);
	           RadioButton buttonC =(RadioButton) findViewById(R.id.cButton);
	           buttonC.setText(multipleChoiceAnswer[2]);
	           RadioButton buttonD =(RadioButton) findViewById(R.id.dButton);
	           buttonD.setText(multipleChoiceAnswer[3]);
	           
	           
	           Button submitButton = (Button) findViewById(R.id.submitButton);
	           submitButton.setOnClickListener(new OnClickListener(){
	        	   @Override
	        	   public void onClick(View v){
	        		   RadioButton checkedRadio = (RadioButton) findViewById(choices.getCheckedRadioButtonId());        		   
	        		   String response = checkedRadio.getText().toString();
	        		   System.out.println("the selected item is :  "+response);
	        		   TextView feedBackView = (TextView)findViewById(R.id.response);
	        		   if((multipleChoiceAnswer[4]).contains(response)){
	        		   		feedBackView.setText("Correct!");
	        		   }else{
	        			   	feedBackView.setText("Incorrect!");
	        		   }//closing if-else statement
	        	   }//closing onClick()
	           });//closing submitButton Listener
	    	   
	    	    	      	   
	    	  
	           //for the next question button
	           Button nextButton = (Button) findViewById(R.id.nextQuestionButton);
	           nextButton.setOnClickListener(new OnClickListener(){
	   			@Override
	   			public void onClick(View v) {
	   				run();	
	   			}//closing onClick()
	           });//closing nextButton onClickListener
	           
	       }//closing if statement for multiple choice questions
	              
	       //checking to see what XML file to load
	       if(questionAnswer){//if simple question and answer
	       	setContentView(R.layout.simpleanswer);
	       	//setting a textView for the question
	           TextView questionView = (TextView)findViewById(R.id.questionView);
	           questionView.setText(questionString); 
	       	
	       	Button AasnwerButton = (Button) findViewById(R.id.answerButton);
	           AasnwerButton.setOnClickListener(new OnClickListener(){
	   			@Override
	   			public void onClick(View v) {
	   				TextView questionTextView = (TextView)findViewById(R.id.answerString);
	   		        questionTextView.setText(asnwerString);
	   			}//closing onClick()
	           });//closing answerButton onClickListener
	           //testing for the next question button
	           Button nextButton = (Button) findViewById(R.id.nextbutton);
	           nextButton.setOnClickListener(new OnClickListener(){
	  
	   			@Override
	   			public void onClick(View v) {
	   				//Intent intent  = new Intent(getApplicationContext(), dataStructures.class);
	   				//startActivity(intent);
	   				run();
	   			}//closing onClick()
	           });//closing nextButton onClickListener
	       }
	       if(trueAndFalse){//just an asnwer format
	       	setContentView(R.layout.trueandfalse);
	           //setting a textView for the question
	           TextView questionView = (TextView)findViewById(R.id.questionView);
	           questionView.setText(questionString);  
	       	
	           if(lastChar.equals("t*")){//if the secret charcter is t
	           	 Button BasnwerButton = (Button) findViewById(R.id.trueButton);
	                BasnwerButton.setOnClickListener(new OnClickListener(){       	 
	        			@Override
	        			public void onClick(View v) {
	        				TextView questionTextView = (TextView)findViewById(R.id.answerView);
	        		        questionTextView.setText("Correct!");
	        			}//closing onClick()
	                });//closing answerButton onClickListener
	                
	                
	                Button falseButton = (Button) findViewById(R.id.falseButton);
	                falseButton.setOnClickListener(new OnClickListener(){
	        			@Override
	        			public void onClick(View v) {
	        				TextView questionTextView = (TextView)findViewById(R.id.answerView);
	        		        questionTextView.setText("INCORRECT!");
	        			}//closing onClick()
	                });//closing answerButton onClickListener 
	                
	                

	                //testing for the next question button
	                Button nextButton = (Button) findViewById(R.id.nextbutton);
	                nextButton.setOnClickListener(new OnClickListener(){
	        			@Override
	        			public void onClick(View v) {
	        				//Intent intent  = new Intent(getApplicationContext(), dataStructures.class);
	        				//startActivity(intent);
	        				run();	
	        			}//closing onClick()
	                });//closing nextButton onClickListener
	                
	               
	           }else{
	           	TextView questionTextView = (TextView)findViewById(R.id.answerView);
	   	        questionTextView.setText("");
	   	        
	   	     Button BasnwerButton = (Button) findViewById(R.id.trueButton);
	         BasnwerButton.setOnClickListener(new OnClickListener(){
	 			@Override
	 			public void onClick(View v) {
	 				TextView questionTextView = (TextView)findViewById(R.id.answerView);
	 		        questionTextView.setText("Nope!");
	 			}//closing onClick()
	         });//closing answerButton onClickListener
	         
	         
	         Button falseButton = (Button) findViewById(R.id.falseButton);
	         falseButton.setOnClickListener(new OnClickListener(){
	 			@Override
	 			public void onClick(View v) {
	 				TextView questionTextView = (TextView)findViewById(R.id.answerView);
	 		        questionTextView.setText("Correct!");
	 			}//closing onClick()
	         });//closing answerButton onClickListener 
	   	        
	   	     
	            //testing for the next question button
	            Button nextButton = (Button) findViewById(R.id.nextbutton);
	            nextButton.setOnClickListener(new OnClickListener(){
	           	 
	    			@Override
	    			public void onClick(View v) {
	    				//Intent intent  = new Intent(getApplicationContext(), dataStructures.class);
	    				//startActivity(intent);
	    				run();
	    			}//closing onClick()
	            });//closing nextButton onClickListener
	   	        
	   	        
	       	}//closing inner if-else statement
	           
	       }//closing main if-else statement

	       }//closing if-else to check if the string(line from text) is empty of not 
	    }//if-else to check the tester array on top    
		}//closing run()

		

      
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
