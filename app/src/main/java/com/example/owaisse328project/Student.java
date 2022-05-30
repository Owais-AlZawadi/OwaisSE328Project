package com.example.owaisse328project;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Student {
    public int studentId;
    public String name;
    public String surName;
    public String fatherName;
    public int nationalId;
    public String dateOfBirth;
    public String gender;

    public Student(int studentId, String name, String surName, String fatherName, int nationalId, String dateOfBirth, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.surName = surName;
        this.fatherName = fatherName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}