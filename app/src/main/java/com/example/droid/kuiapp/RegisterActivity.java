package com.example.droid.kuiapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstname;
    private  EditText lastname;
    private EditText Email;
    private EditText phoneNumber;
    private Button DateOfBirth;
    private EditText editDob;
    private EditText password;
    private Button Signup;
    private DatePickerDialog.OnDateSetListener mdateSetListener;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        Email = (EditText)findViewById(R.id.enEmail);
        phoneNumber = (EditText)findViewById(R.id.phonenumber);
        DateOfBirth = (Button) findViewById(R.id.dob);
        editDob = (EditText) findViewById(R.id.endob);
        password = (EditText)findViewById(R.id.pass);
        Signup = (Button)findViewById(R.id.btnNext);
        db= FirebaseDatabase.getInstance().getReference("signupdetails");
        DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,mdateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        mdateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String date = dayOfMonth + "/ "+ month + "/ "+ year;
                editDob.setText(date);
            }
        };

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Register();

            }
        });

    }
    public void Register(){
        String fn=firstname.getText().toString();
        String  ln=lastname.getText().toString();
        String email = Email.getText().toString();
        String  phone=phoneNumber.getText().toString();
        String  DOB = editDob.getText().toString();
        String pass = password.getText().toString();




        if (!TextUtils.isEmpty(fn)&&(!TextUtils.isEmpty(ln)&&(!TextUtils.isEmpty(email)&&(!TextUtils.isEmpty(phone)&&(!TextUtils.isEmpty(DOB)&&(!TextUtils.isEmpty(pass))))))){
            String Id =db.push().getKey();
            SignUp signup=new SignUp(fn,ln,email,phone,DOB,pass);
            db.child(Id).setValue(signup);
            Toast.makeText(RegisterActivity.this,"Member Details Already Captured",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);


        }
        else{
            Toast.makeText(RegisterActivity.this,"Please Enter All Fields",Toast.LENGTH_LONG).show();
        }

    }

}
