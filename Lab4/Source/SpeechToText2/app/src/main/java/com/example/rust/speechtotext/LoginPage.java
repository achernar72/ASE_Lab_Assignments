package com.example.rust.speechtotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    private static TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        LoginButton();

    }
    public void LoginButton(){
        username =(EditText)findViewById(R.id.editText_usernamer);
        password =(EditText)findViewById(R.id.editText_passwordr);
        login_btn=(Button)findViewById(R.id.button_login);
        register =(TextView)findViewById(R.id.textView_register);

        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(username.getText().toString().equals("Avinash")&&
                        password.getText().toString().equals("aspk4")) {
                    Intent intent = new Intent("com.example.rust.firstapp.MainApplication");
                    startActivity(intent);
                } else{
                    Toast.makeText(LoginPage.this,"user and password is not correct",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent("com.example.rust.firstapp.RegisterActivity");
                startActivity(launchActivity1);

            }
        });



    }
}

