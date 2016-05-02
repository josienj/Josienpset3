package com.example.josien.josienpset3;

/**
 * Created by Josien on 29-4-2016.
 */
public class todoList {

    private int _id;
    private String _todo;

    public todoList () {

    }

    public todoList(String _todo) {
        this._todo = _todo;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_todo(String _todo) {
        this._todo = _todo;
    }

    public int get_id() {
        return _id;
    }

    public String get_todo() {
        return _todo;
    }

    public String toString() {
        return _todo;
    }
}


