package com.example.josien.josienpset3;

/*    Pset 3: to-do list
/**   Josien Jansen
/**   11162295
/**   Universiteit van Amsterdam
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayListToDo;
    private ArrayAdapter<String> arrayAdapterToDo;

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save todolist items
        outState.putStringArrayList("todolist", arrayListToDo);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        Check for any previous states and edit todoList
        if(savedInstanceState != null) {
            arrayListToDo = savedInstanceState.getStringArrayList("todolist");
        }

        arrayListToDo = new ArrayList<String>();
        arrayAdapterToDo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  arrayListToDo);
        ListView listviewToDo = (ListView) findViewById(R.id.list_view);
        listviewToDo.setAdapter(arrayAdapterToDo);
        listviewToDo.setLongClickable(true);
        listviewToDo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoList.remove(position);
                arrayAdapterToDo.notifyDataSetChanged();
                return true;
            }
        });
            public void addTodo(View view) {
                EditText editTextToDo = (EditText) findViewById(R.id.edittext);
                String todo = ((EditText) findViewById(R.id.edittext)).getText().toString().trim();
                arrayAdapterToDo.add(todo);
                editTextToDo.setText("");
                if (todo.isEmpty()) {
                    return;
                }
            }
        }
    }

