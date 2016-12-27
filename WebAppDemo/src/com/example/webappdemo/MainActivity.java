package com.example.webappdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	String RcvdMsg;
	HttpClient httpClient;
	HttpGet request;
	HttpResponse response;
	BufferedReader bufferedReader;
	StringBuffer stringBuffer;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText UID = (EditText)findViewById(R.id.editText1);
		final EditText PWD = (EditText)findViewById(R.id.editText2);
		Button	 B1  = (Button)findViewById(R.id.button1);
		
		B1.setOnClickListener(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
						try
						{
							RcvdMsg="";

				   			if (android.os.Build.VERSION.SDK_INT > 9)
				    		{
				    		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				    		    StrictMode.setThreadPolicy(policy);
				    		}

							httpClient = new DefaultHttpClient();
							request = new HttpGet("http://ekasmin.com/AppReception?method=login&userType=emp&uname=EMP1CAM&pass=123");
							response= httpClient.execute(request);
							InputStreamReader IR = new InputStreamReader(response.getEntity().getContent());
					        bufferedReader = new BufferedReader(IR);

					        stringBuffer = new StringBuffer("");
					        String line = "";
					        String LineSeparator = System.getProperty("line.separator");

					        while(true) 
					        {
					        	line = bufferedReader.readLine();

					        	if(line!=null)
					        	stringBuffer.append(line + LineSeparator);
					        	else
				        		break;
					        }

					        bufferedReader.close();
					        RcvdMsg = stringBuffer.toString();
					        String textmsg=RcvdMsg;
					        Toast.makeText(getApplicationContext(), textmsg, Toast.LENGTH_LONG).show();
						}
						catch(Exception E)
						{
							Toast.makeText(getApplicationContext(), E.getMessage(), Toast.LENGTH_LONG).show();
						}
					}
										
				}
							 );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}