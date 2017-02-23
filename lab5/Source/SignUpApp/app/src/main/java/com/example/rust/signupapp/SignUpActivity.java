package com.example.rust.signupapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button Login;
    private static Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginButton();
    }
    public void loginButton(){
        username =(EditText)findViewById(R.id.etUsername);
        password =(EditText)findViewById(R.id.etPassword);
        Login =(Button)findViewById(R.id.btLogin);
        signup =(Button)findViewById(R.id.btSignIn);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(username.getText().toString().equals("aspk4")&&
                        password.getText().toString().equals("aspk4")) {
                    Intent intent = new Intent("com.example.rust.signupapp.Googlemap");
                    startActivity(intent);
                } else{
                    Toast.makeText(SignUpActivity.this,"user and password is not correct",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent("com.example.rust.signupapp.registerActivity");
                startActivity(launchActivity1);

            }
        });



    }


}
