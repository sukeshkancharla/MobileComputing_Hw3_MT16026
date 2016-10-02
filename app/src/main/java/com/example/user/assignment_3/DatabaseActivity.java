package com.example.user.assignment_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by user on 02-Oct-16.
 */

public class DatabaseActivity extends AppCompatActivity{
    private Button button9;
    private Button button10;
    private Button button11;
    private EditText text3;
    private TextView textView3;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);
        DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext(),"myDatabase");
        db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        button9=(Button)findViewById(R.id.button9);
        button10=(Button)findViewById(R.id.button10);
        text3=(EditText)findViewById(R.id.text3);
        textView3=(TextView)findViewById(R.id.textView3);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Enter some Value",Toast.LENGTH_LONG).show();
                }
                else {
                    db.execSQL("insert into info values('" + text3.getText().toString() + "');");
                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                }
                text3.setText("");
                textView3.setText("");
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("select * from info",null);
                c.moveToFirst();
                String row="";
                StringBuffer stringBuffer=new StringBuffer();
                if (c!=null)
                {
                    do {
                        try
                        {
                            row = c.getString(c.getColumnIndex("value")).toString();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                        stringBuffer.append(row + "\n");
                    }while (c.moveToNext());
                }
                textView3.setText(stringBuffer.toString());
                text3.setText("");
            }
        });
        button11=(Button)findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text3.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter a value",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Cursor c=db.rawQuery("select * from info where value='"+text3.getText().toString()+"'",null);
                    c.moveToFirst();
                    if(c!=null){
                        Toast.makeText(getApplicationContext(), "No such value exists", Toast.LENGTH_LONG).show();
                    }
                    else {
                        db.execSQL("delete from info where value='" + text3.getText().toString() + "'");
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();

                    }
                }
                text3.setText("");
                textView3.setText("");
            }
        });
    }
}
