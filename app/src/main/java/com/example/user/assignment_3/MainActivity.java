package com.example.user.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private Button button1;
    private Button button3;
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref=getSharedPreferences(getString(R.string.shared_pref_id),Context.MODE_PRIVATE);
        if(sharedPref.getString("lastModified","couldn't get").equals("couldn't get")) {
            text1=(TextView)findViewById(R.id.text1);
            text1.setText("Welcome for the firstTime");
        }
        else {
            text1 = (TextView) findViewById(R.id.text1);
            text1.setText("your opened last time at" + sharedPref.getString("lastModified", "couldn't get"));
        }
        Date date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("lastModified",dateFormat.format(date));
        editor.commit();
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),InternalStorageActivity.class);
                startActivity(i);
             }
        });
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),ExternalStorageActivityPrivate.class);
                startActivity(in);
            }
        });
        button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),DatabaseActivity.class);
                startActivity(in);
            }
        });
    }
}
