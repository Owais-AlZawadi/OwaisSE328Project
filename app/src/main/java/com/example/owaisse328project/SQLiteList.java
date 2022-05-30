package com.example.owaisse328project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteList extends ListActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<String> arrayList = new ArrayList<>();
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cur = db.getListContents();
        while (cur.moveToNext()) {
            arrayList.add("\nStudent ID: " + cur.getString(0) + "\nName: " + cur.getString(1) + "\nSur Name: "
                    + cur.getString(2) + "\nFather Name: " + cur.getString(3) + "\nNational ID: " + cur.getString(4) + cur.getString(5) + "\nDate of Birth: "
                    + cur.getString(6) + "\nGender:" + cur.getString(7) + "\n");
            setListAdapter(new ArrayAdapter<String>(SQLiteList.this, R.layout.activity_sqlite_list, R.id.itemlist2, arrayList));
        }
    }

    protected void onListItemClick (ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        int row = 0;
        cur = db.getListContents();

        while(row<=position){
            cur.moveToNext();
            row++;
        }

        Toasty.normal(getBaseContext(), "First Name: " + cur.getString(1) + "\nLast Name: "
                + cur.getString(2), Toast.LENGTH_SHORT).show();
    }
}