package com.example.user.assignment_3;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
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

public class ExternalStorageActivityPrivate extends AppCompatActivity{

    private EditText text2;
    private Button button7;
    private Button button8;
    private TextView textView2;
    private final String filename="Hello_external_private";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage_private_activity);
        text2=(EditText)findViewById(R.id.editText2);
        button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text2.getText().toString().equals("")) {

                }
                else {
                    try
                    {
                        String state= Environment.getExternalStorageState();
                        if(Environment.MEDIA_MOUNTED.equals(state))
                        {
                            File Root=Environment.getExternalStorageDirectory();
                            File Dir=new File(Root.getAbsolutePath()+"/myAppFolder");
                            if(!Dir.exists())
                            {
                                Dir.mkdir();
                            }
                            File file=new File(Dir,filename);
                            FileOutputStream outputStream=new FileOutputStream(file);
                            outputStream.write(text2.getText().toString().getBytes());
                            outputStream.close();
                            text2.setText("");
                            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        button8=(Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2=(TextView)findViewById(R.id.textView2);
                try {
                    File Root=Environment.getExternalStorageDirectory();
                    File Dir=new File(Root.getAbsolutePath()+"/myAppFolder");
                    File file=new File(Dir,filename);
                    String message;
                    FileInputStream inputStream=new FileInputStream(file);
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer=new StringBuffer();
                    while((message=bufferedReader.readLine())!=null)
                    {
                        stringBuffer.append(message+"\n");
                    }
                    textView2.setText(stringBuffer.toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

