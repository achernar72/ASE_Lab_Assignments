package com.example.rust.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainApplication extends AppCompatActivity {
    String sourceText;
    TextView outputTextView;
    TextView outputTextView1;
    Context mContext;
    private TextView Text;

    protected static final int RESULT_SPEECH = 1;

    private static Button btnSpeak;
    private static EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        editText = (EditText)findViewById(R.id.editText_textarea);

        btnSpeak = (Button) findViewById(R.id.button_speaknow);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    Text.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void logout(View v) {
        Intent redirect = new Intent(MainApplication.this, LoginPage.class);
        startActivity(redirect);
    }




    }







