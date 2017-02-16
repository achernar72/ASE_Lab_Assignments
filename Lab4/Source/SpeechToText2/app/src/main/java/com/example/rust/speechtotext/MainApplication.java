package com.example.rust.speechtotext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.EditText;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import android.speech.RecognizerIntent;
import java.util.Locale;
import android.content.ActivityNotFoundException;
import android.widget.Toast;


public class MainApplication extends AppCompatActivity {
    String sourceText;
    TextView outputTextView;
    TextView outputTextView1;
    Context mContext;
    private TextView Text;

    protected static final int RESULT_SPEECH = 1;

    private ImageButton btnSpeak;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        outputTextView = (TextView) findViewById(R.id.txt_Result);
        outputTextView1 = (TextView) findViewById(R.id.txt_Result1);

        Text = (TextView) findViewById(R.id.Text);

        editText = (EditText)findViewById(R.id.txt_Email);

        btnSpeak = (ImageButton) findViewById(R.id.mic);
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







