package com.akshayk.vocabuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener {

	TextView tvQ,tvS;
	CheckBox cb;
	TextToSpeech tts;
	RadioButton r0,r1,r2,r3;
	RadioGroup rg;
	Button b;
	int cl=0,change=0;
	int wa=0;
	long score=0;
	String q[] = new String[4];
	Functions Obj_Fun;
	InputStream obj;
	SharedPreferences saveData;
	public static String fileName="MyFile";
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts!= null)
		{
			tts.stop();
			tts.shutdown();
		}
			
			super.onPause();
			SharedPreferences.Editor e=saveData.edit();
			e.putString("text1", ""+score);
			e.commit();
			
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//saveData=getSharedPreferences(fileName,0);
		//printText=saveData.getString("text1", "Couldn't load text");
		
		//if(printText!=null)
		//	score=Integer.parseInt(printText);
		
		
		intial();
		
		//Created a file with file name "MyFile" and 0 repersent private mode
				saveData=getSharedPreferences(fileName,0);
				
				saveData=getSharedPreferences(fileName,0);
				String printText=saveData.getString("text1", "00");
				score=Integer.parseInt(printText);
				
		Obj_Fun=new Functions();
		obj=this.getResources().openRawResource(R.raw.helptxt);
		cl=Obj_Fun.countToatalLines(obj, getBaseContext());
		
		b.setOnClickListener(this);
		generateQ();
	}
		
	




	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		intial_T2S();
	}
	private void intial() {
		// TODO Auto-generated method stub
		tvQ=(TextView)findViewById(R.id.tVQ);
		tvS=(TextView)findViewById(R.id.textView1);
		b=(Button)findViewById(R.id.btnSubmit);
		r0=(RadioButton)findViewById(R.id.radio0);
		r1=(RadioButton)findViewById(R.id.radio1);
		r2=(RadioButton)findViewById(R.id.radio2);
		r3=(RadioButton)findViewById(R.id.radio3);
		rg=(RadioGroup)findViewById(R.id.rgOpinion);
		cb=(CheckBox)findViewById(R.id.checkBox1);
		rg.setOnCheckedChangeListener(this);
		intial_T2S();

	}

 void intial_T2S()
 {
	 tts= new TextToSpeech(MainActivity.this,new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status != TextToSpeech.ERROR)
				tts.setLanguage(Locale.ENGLISH);
				tts.setSpeechRate((float) 0.7);
			}
		});
 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		
		/*case R.id.menuAboutUs:
			Intent pre=new Intent("com.akshayk.vocabuilder.ABOUTUS");
			startActivity(pre);
			break;*/
		case R.id.menuAbout:
			Intent pr=new Intent("com.akshayk.vocabuilder.About");
			startActivity(pr);
			break;
		case R.id.menuExit:
			finish();
			break;
		}
		return false;
		
	}




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		if(change==1)
			score+=5;
		
		rg.clearCheck();
		//tvS.setText("Score: "+score);
		generateQ();
	}



	private void generateQ() {
		// TODO Auto-generated method stub
		
		change=0;
		
		Random rand=new Random();
		int ln=rand.nextInt((cl-5));
		int cq=0;
		 InputStream obj=this.getResources().openRawResource(R.raw.helptxt);
			BufferedReader br=new BufferedReader(new InputStreamReader(obj));
			String text="",t="";
			
		
			try {
				while((text=br.readLine())!=null)
				{
				 
					cq++;
					if(cq==ln)
						break;
				}
				
			
				String texta=text.substring(0,text.indexOf('-'));
				//textq=textq.replaceAll("[^A-Z]","");
				String textq=text.substring(text.indexOf('-')+1);
				wa=rand.nextInt(4);
				for(int i=0;i<q.length;i++)
				{
					if(wa==i)
						continue;
					q[i]=br.readLine();
				}
				//q[wa]="-"+texta; 
				q[wa]=texta+"-";
				/*
				 q[0]=texta;
				q[1]=br.readLine();
				q[2]=br.readLine();
				q[3]=br.readLine();
				*/
				//q[3]="-"+texta;
			  q=ConvertToAnswer(q);
			  
			  setRadioButtons(q);
			  //tvQ.setText("Ques:"+textq +"\n Ans:"+texta + "\nRandom no:"+ln + " Rand: "+wa);
			  tvQ.setText(""+textq);
			  if(cb.isChecked())
				  tts.speak(textq, TextToSpeech.QUEUE_FLUSH, null);
			  
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getBaseContext(), "Error 201: Unable to read from helptxt.txt", Toast.LENGTH_LONG);
			}
			
		
	}



	private void setRadioButtons(String[] q) {
		// TODO Auto-generated method stub
		
		r0.setText(q[0]);
		r1.setText(q[1]);
		r2.setText(q[2]);
		r3.setText(q[3]);
		/*
		r0.setChecked(false);
	    r1.setChecked(false);
	    r2.setChecked(false);
	    r3.setChecked(false);*/
		r0.setTextColor(Color.BLACK);
	   r1.setTextColor(Color.BLACK);
		r2.setTextColor(Color.BLACK);
		r3.setTextColor(Color.BLACK);
	}



	private String[] ConvertToAnswer(String[] q) {
		// TODO Auto-generated method stub
		for(int i=0;i<q.length;i++)
			q[i]=q[i].substring(0,q[i].indexOf('-'));
		return q;
		
	}



	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
		switch(arg1)
		{
		case R.id.radio0:
			if(wa==0 && change==0)
			{
				r0.setTextColor(Color.GREEN);
				
				score+=10;
				change=1;
			}
			else
			{
			r0.setTextColor(Color.RED);
			score-=5;
			}
			break;
        case R.id.radio1:
        	if(wa==1&& change==0)
			{
				r1.setTextColor(Color.GREEN);
				score+=10;
				change=1;
			}
			else
			{
			r1.setTextColor(Color.RED);
			score-=5;
			}
			break;
        case R.id.radio2:
        	if(wa==2 && change==0)
			{
				r2.setTextColor(Color.GREEN);
				score+=10;
				change=1;
			}
			else
			{
			r2.setTextColor(Color.RED);
			score-=5;
			}
			break;
        case R.id.radio3:
        	if(wa==3 && change==0)
			{
				r3.setTextColor(Color.GREEN);
				score+=10;
				change=1;
			}
			else
			{
			r3.setTextColor(Color.RED);
			score-=5;
			}
			break;
		}
		tvS.setText("Score: "+score);
	}

}
