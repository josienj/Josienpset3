package com.example.josien.josienpset3;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView newlist;
    DBhelper DBhelper;
    ArrayAdapter<todoList> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
        newlist = (ListView) findViewById(R.id.list_view);
        DBhelper = new DBhelper(this, null, null, 1);

        setupTodoListView();
    }

    public void addTodo(View view) {

        String input = String.valueOf(editText.getText());

        // Check for correct input.
        if (!input.matches("[a-zA-Z\\s]+")) {
            Toast.makeText(this, "You have to fill in something", Toast.LENGTH_LONG).show();
            return;
        } else {

            todoList todo = new todoList(editText.getText().toString());

            DBhelper.addTodo(todo);
            // Add item to the ListView
            listAdapter.add(todo);
            Toast.makeText(this, "To-do is added!", Toast.LENGTH_LONG).show();
            // Clear the input field
            editText.setText("");
        }
    }

    public void setupTodoListView(){

        final ArrayList<todoList> todoListArray = DBhelper.retrieveTodoLists();

        // Make a new ArrayAdapter to handle the objects, and add them to the view
        // The simple_list_item_1 and text1 are Android defaults
        listAdapter = new ArrayAdapter<todoList>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, todoListArray);

        // Apply the adapter on the ViewList
        newlist.setAdapter(listAdapter);

        // Add a new on click listener.
        newlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Clicked item value
                todoList item = (todoList) newlist.getItemAtPosition(position);
                // Delete item from database
                DBhelper.delete(item.get_id());
                // Delete item from listView
                listAdapter.remove(item);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
}
