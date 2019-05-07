package com.example.user.sqlitebasic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        e3=(EditText)findViewById(R.id.et3);
        e4=(EditText)findViewById(R.id.et4);
        b1=(Button)findViewById(R.id.btn1);
        b2=(Button)findViewById(R.id.btn2);
        b3=(Button)findViewById(R.id.btn3);
        b4=(Button)findViewById(R.id.btn4);

        myDatabaseHelper=new MyDatabaseHelper(this);
        //SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=e1.getText().toString();
                String age=e2.getText().toString();
                String gender=e3.getText().toString();
                String id=e4.getText().toString();

                if(name.isEmpty())
                {
                    e1.setError("enter Name");
                    //e2.setError("enter Age");
                    //e3.setError("enter Gender");
                }

                else if (age.isEmpty())
                {
                    //e1.setError("enter Name");
                    e2.setError("enter Age");
                    //e3.setError("enter Gender");
                }

                else if(gender.isEmpty())
                {
                    //e1.setError("enter Name");
                    //e2.setError("enter Age");
                    e3.setError("enter Gender");
                }

                else
                {
                    long rowId = myDatabaseHelper.insertData(name,age,gender);
                    if(rowId==-1){
                        Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Row"+rowId+"is successfully inserted", Toast.LENGTH_SHORT).show();
                    }
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                }



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=myDatabaseHelper.displayAllData();
                if(cursor.getCount()==0){
                  showdata("Error","no data found");
                  return;
                }else{
                    StringBuffer stringBuffer= new StringBuffer();
                    while(cursor.moveToNext()){
                        stringBuffer.append("ID:"+cursor.getString(0)+"\n");
                        stringBuffer.append("Name:"+cursor.getString(1)+"\n");
                        stringBuffer.append("Age:"+cursor.getString(2)+"\n");
                        stringBuffer.append("Gender:"+cursor.getString(3)+"\n\n");

                    }
                    showdata("Result",stringBuffer.toString());
                }

            }
        });

    }

    private void showdata(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
