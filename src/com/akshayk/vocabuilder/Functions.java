package com.akshayk.vocabuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class Functions
{
	
   
	
	protected int countToatalLines(InputStream obj,Context c)
	{
		int cl=0;
		  
			BufferedReader br=new BufferedReader(new InputStreamReader(obj));
			String text="",t="";
			
			try {
				while((text=br.readLine())!=null)
				{
					
					cl++;
				}
				//tvQ.setText("No of lines:"+cl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(c, "Error 201: Unable to read from helptxt.txt", Toast.LENGTH_LONG);
			}
		return cl;
		
	}
	
	/*
	private void generateQ(InputStream obj,int change,int cl) {
		// TODO Auto-generated method stub
		
		change=0;
		int cq=0;
		Random rand=new Random();
		int ln=rand.nextInt((cl-5));
		
		 
			BufferedReader br=new BufferedReader(new InputStreamReader(obj));
			String text="",t="";
			
		
			try {
				while((text=br.readLine())!=null)
				{
				 
					cq++;
					if(cq==ln)
						break;
				}
				
			
				String textq=text.substring(0,text.indexOf('-'));
				//textq=textq.replaceAll("[^A-Z]","");
				String texta=text.substring(text.indexOf('-')+1);
				wa=rand.nextInt(4);
				for(int i=0;i<q.length;i++)
				{
					if(wa==i)
						continue;
					q[i]=br.readLine();
				}
				q[wa]="-"+texta;
				/*
				 q[0]=texta;
				q[1]=br.readLine();
				q[2]=br.readLine();
				q[3]=br.readLine();
				
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
			*/
		
	}
	


