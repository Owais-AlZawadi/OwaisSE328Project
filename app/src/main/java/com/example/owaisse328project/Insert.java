package com.example.owaisse328project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends AppCompatActivity {

    EditText idET, nameET, snameET, fnameET, nationalIdET, dateOfBirthET, genderET;
    Firebase firebase = new Firebase();
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        idET = (EditText) findViewById(R.id.ID);
        nameET = (EditText) findViewById(R.id.name);
        snameET = (EditText) findViewById(R.id.sName);
        fnameET = (EditText) findViewById(R.id.fName);
        nationalIdET = (EditText) findViewById(R.id.nationalId);
        dateOfBirthET = (EditText) findViewById(R.id.dateOfBirth);
        genderET = (EditText) findViewById(R.id.gender);


        Button addFire = (Button) findViewById(R.id.addFire);
        Button addSQL = (Button) findViewById(R.id.addSQL);

        addFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idET.getText().toString());
                String name = nameET.getText().toString();
                String sname = snameET.getText().toString();
                String fname = fnameET.getText().toString();
                int nationalId = Integer.parseInt(nationalIdET.getText().toString());
                String dateOfBirth = dateOfBirthET.getText().toString();
                String gender = genderET.getText().toString();
                firebase.insert(id, name, sname, fname, nationalId, dateOfBirth, gender, getBaseContext());
            }
        });

        addSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idET.getText().toString());
                String name = nameET.getText().toString();
                String sname = snameET.getText().toString();
                String fname = fnameET.getText().toString();
                int nationalId = Integer.parseInt(nationalIdET.getText().toString());
                String dateOfBirth = dateOfBirthET.getText().toString();
                String gender = genderET.getText().toString();

                if (!db.addData(id,name,sname,fname,nationalId,dateOfBirth,gender)) {
                    Toasty.error(getBaseContext(), "ID is used already!", Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.success(getBaseContext(), "Added Successfully", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }
}