package com.example.owaisse328project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseList extends ListActivity {

    Firebase firebase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebase = new Firebase();
        databaseReference = firebase.myRef;

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String studentId = dataSnapshot.child("studentId").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                String surName = dataSnapshot.child("surName").getValue().toString();
                String fatherName = dataSnapshot.child("fatherName").getValue().toString();
                String nationalId = dataSnapshot.child("nationalId").getValue().toString();
                String dateOfBirth = dataSnapshot.child("dateOfBirth").getValue().toString();
                String gender = dataSnapshot.child("gender").getValue().toString();

                arrayList.add("\nStudent ID: " + studentId + "\nName: " + name + "\nSur Name: " + surName + "\nFather Name: " + fatherName + "\nNational ID: "
                        + nationalId + "\nDate of Birth: " + dateOfBirth + "\nGender: " + gender + " \n");
                setListAdapter(new ArrayAdapter<String>(FirebaseList.this, R.layout.activity_firebase_list, R.id.itemlist, arrayList));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}