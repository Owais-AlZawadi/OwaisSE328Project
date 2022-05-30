package com.example.owaisse328project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Firebase extends AppCompatActivity {

    final private FirebaseDatabase database;
    final public DatabaseReference myRef;
    private Query query;
    private int lastRootId = 4;
    int runTimes = 1;

    SharedPreferences sp;

    public Firebase(){
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(int studentId, String name, String surName, String fatherName, int nationalId, String dateOfBirth, String gender, Context c){
        runTimes=1;
        query = myRef.orderByChild("studentId").equalTo(studentId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                while(runTimes<=1){
                    runTimes++;
                    if (dataSnapshot.getChildrenCount() <= 0) {
                        Toasty.success(c, "Added Successfully", Toast.LENGTH_SHORT, true).show();
                        Student student = new Student(studentId, name, surName, fatherName, nationalId, dateOfBirth, gender);
                        String rootId = String.valueOf(++lastRootId);
                        writeWithSuccess(rootId, student);
                    }
                    else{
                        Toasty.error(c, "This ID is Taken", Toast.LENGTH_SHORT, true).show();
                        Log.d("Owais","id taken");
                    }
                }
                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    }
                } catch (Exception e) {
                    Log.d("Owais-Exception", e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Owais", "Failed to read value.", error.toException());
            }
        });
    }

    public void writeWithSuccess(String studentId, Student student) {
        myRef.child(studentId).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Owais", "SUCCESS writing..." + studentId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Owais", "Error: " + e);
            }
        });
    }

    public void update(int studentId, String key2update, String newValue, Context c){
        query = myRef.orderByChild("studentId").equalTo(studentId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()==0){
                    Toasty.error(c, "No such Student", Toast.LENGTH_SHORT, true).show();
                }
                try{
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        ds.getRef().child(key2update).setValue(newValue);
                        Toasty.success(c, "Updated Successfully", Toast.LENGTH_SHORT, true).show();
                    }
                }
                catch(Exception e){
                    Log.d("Owais-Exception",e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Owais", "Failed to update value.", error.toException());
            }
        });
    }


    public void delete(int studentId , Context c){
        runTimes=1;
        query = myRef.orderByChild("studentId").equalTo(studentId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                while(runTimes<=1){
                    runTimes++;
                    if (dataSnapshot.getChildrenCount() > 0) {
                        Toasty.success(c, "Deleted from Firebase Successfully", Toast.LENGTH_SHORT, true).show();
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                    }
                    else{
                        Toasty.error(c, "No Such Student", Toast.LENGTH_SHORT, true).show();
                        Log.d("Owais","id taken");
                    }
                }
                try{
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        //ds.getRef().removeValue();
                        //Toasty.success(c, "Deleted from SQL Successfully", Toast.LENGTH_SHORT, true).show();
                    }
                }
                catch(Exception e){
                    Log.d("Owais-Exception",e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Owais", "Failed to read value.", error.toException());
            }
        });
    }

}