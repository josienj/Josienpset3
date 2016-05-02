package com.example.josien.josienpset3;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Josien on 29-4-2016.
 */
public class DBhelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_todoList = "todolist";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "todo";

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_todoList + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO + " TEXT " +
                ")";
        db.execSQL(query);

        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, "This is a to-do");
        db.insert(TABLE_todoList, null, values);

        values = new ContentValues();
        values.put(COLUMN_TODO, "Add one in the box below");
        db.insert(TABLE_todoList, null, values);

        values = new ContentValues();
        values.put(COLUMN_TODO, "Delete your todo's by long-pressing");
        db.insert(TABLE_todoList, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_todoList);
    }

    //Add a new row to the database
    public void addTodo(todoList todoList) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, todoList.get_todo());
        SQLiteDatabase db = getWritableDatabase();
        long itemId = db.insert(TABLE_todoList, null,values);
        db.close();

        todoList.set_id(((int) itemId));
    }

    //delete a item from the database
    //has to be longclick

    public void delete (int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_todoList, "_id = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    // get all todolist items from the database
    public ArrayList<todoList> retrieveTodoLists(){
        ArrayList<todoList> todoListsArray = new ArrayList<todoList>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_todoList + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        // move to the first row in your results
        // If the result is empty, we don't do anything
        if (c.moveToFirst()) {
            // Loop through all results
            do {
                // Create a new todoList object and set the correct data
                todoList todo = new todoList();
                todo.set_id(c.getInt(0));
                todo.set_todo(c.getString(1));

                todoListsArray.add(todo);
            } while (c.moveToNext()) ;
        }

        db.close();
        return todoListsArray;
    }



}
