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
    ListView newList;
    DBhelper DBhelper;
    ArrayAdapter<TodoList> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.todo_edittext);
        newList = (ListView) findViewById(R.id.list_view);
        DBhelper = new DBhelper(this, null, null, 1);

        setupTodoListView();
    }

    /*
    * Add a to-do into the database
    */
    public void addTodo(View view) {

        String input = String.valueOf(editText.getText());

        // Check for correct input.
        if (!input.matches("[a-zA-Z1-9\\s]+")) {
            Toast.makeText(this, "Input isn't correct, try again", Toast.LENGTH_LONG).show();
        }
        else {

            TodoList todo = new TodoList(editText.getText().toString());

            DBhelper.addTodo(todo);
            // Add item to the ListView
            listAdapter.add(todo);
            Toast.makeText(this, "To-do is added!", Toast.LENGTH_LONG).show();
            // Clear the input field
            editText.setText("");
        }
    }

    /*
    * Combine the listview and database correctly
    */
    public void setupTodoListView(){

        final ArrayList<TodoList> todoListArray = DBhelper.retrieveTodoLists();

        // Make a new ArrayAdapter to handle the objects, and add them to the view
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, todoListArray);

        // Apply the adapter on the ViewList
        newList.setAdapter(listAdapter);

        // Add a new on click listener
        newList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Clicked item value
                TodoList item = (TodoList) newList.getItemAtPosition(position);
                // Delete item from database
                DBhelper.deleteItem(item.get_id());
                // Delete item from listView
                listAdapter.remove(item);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
