package com.example.user.assignment_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by user on 02-Oct-16.
 */

public class InternalStorageActivity extends AppCompatActivity{
    private Button button5;
    private Button button6;
    private EditText text1;
    private TextView textView1;
    private final String filename="Hello";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage_activity);
        text1=(EditText)findViewById(R.id.editText1);
        button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().equals(""))
                {

                }
                else {
                    try {

                        FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(text1.getText().toString().getBytes());
                        outputStream.close();
                        Toast.makeText(getApplicationContext(), "file created in Internal Storage", Toast.LENGTH_LONG).show();
                        text1.setText("");


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Couldn't create", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1=(TextView)findViewById(R.id.textView1);
                try {
                    String message;
                    FileInputStream inputStream=openFileInput(filename);
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer=new StringBuffer();
                    while((message=bufferedReader.readLine())!=null)
                    {
                        stringBuffer.append(message+"\n");
                    }
                    textView1.setText(stringBuffer.toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
