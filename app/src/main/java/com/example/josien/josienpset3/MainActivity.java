package com.example.josien.josienpset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView newlist;
    DBhelper DBhelper;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
        newlist = (ListView) findViewById(R.id.list_view);
        DBhelper= new DBhelper(this, null,null, 1);
        printDatabase();
    }

    public void addTodo(View view){
        todoList todo = new todoList(editText.getText().toString());
        DBhelper.addTodo(todo);
        printDatabase();
    }

    public void setListAdapter(ListAdapter listAdapter) {
        this.listAdapter = listAdapter;
        newlist.setAdapter(listAdapter);
    }


    public void printDatabase(){
        String dbString = DBhelper.databasetoString();
        editText.setText("");
    }
}
