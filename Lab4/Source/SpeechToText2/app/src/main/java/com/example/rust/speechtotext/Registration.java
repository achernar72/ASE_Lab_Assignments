package com.example.rust.speechtotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static EditText Age;
    private static EditText EmailAddress;
    private static EditText Phonenumber;
    private static Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Registeration();
    }
    public void Registeration(){
        username =(EditText) findViewById(R.id.editText_usernamer);
        password =(EditText)findViewById(R.id.editText_passwordr);
        Age =(EditText)findViewById(R.id.editText_ager);
        EmailAddress =(EditText)findViewById(R.id.editText_emailaddress);
        Phonenumber=(EditText)findViewById(R.id.editText_phonenumberr);
        Register =(Button)findViewById(R.id.button_register);
        Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent("com.example.rust.firstapp.Registration");
                startActivity(launchActivity1);

            }
        });

    }



}
