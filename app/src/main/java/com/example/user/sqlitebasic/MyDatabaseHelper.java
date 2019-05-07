package com.example.user.sqlitebasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "Student_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "Age";
    private static final String GENDER = "Gender";
    private static  final int VERSION_NUMBER =2;
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+"( "+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255),"+AGE+" INTEGER,"+GENDER+" VARCHAR);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM "+ TABLE_NAME;

    private Context context;
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch(Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(String name, String age, String gender) {

        SQLiteDatabase db = this.getWritableDatabase(); //to write or read in database we need to help getWriteableDatabase method and it will return a SqLiteDatabase
        //to store all the data together we need to use ContentValues class.So we need to make an object of ContentValues
        ContentValues contentValues = new ContentValues();
        //by using put method we get the data from the user and store them all together
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);

        //now we can insert the data into database finally
        //to insert the data into database we need to use db.insert() method
        long rowId= db.insert(TABLE_NAME,null,contentValues); //insert method returns a id of a row if the row is successfully stored into database and the id is a long data type
        return  rowId;   //the row is not successfully stored it will return -1


    }

    public Cursor displayAllData()  //create a method to display all data which is a return type of "Cursor"
    {
        SQLiteDatabase db = this.getWritableDatabase(); //to access the data from in database we need to help getWriteableDatabase method and it will return a SqLiteDatabase

        //While playng a query all the resultset will return and then to access/read/write it we have to use Cursor interface
        Cursor cursor = db.rawQuery(SELECT_ALL,null); //we have to write a query by the help of database "db" and the rawQuery method of SQLiteDatabase returns a dataset or resultset and the resultset must be kept in a variable or interface and it is cursor interface
        return cursor;
    }
}
