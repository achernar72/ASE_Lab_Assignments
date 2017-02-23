package com.example.rust.signupapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class registerActivity extends AppCompatActivity {
    private static EditText fastname;
    private static EditText lastname;
    private static EditText email;
    private static EditText password;
    private static EditText repassword;
    private static EditText phone;
    private static EditText city;
    private static EditText address;
    private static EditText country;
    private static EditText state;
    private static Button Register;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private ImageView ivImage;
    private String userChoosenTask;
    private String fName, lName, Email, pass, repass, phon, cit, addr, countr, stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        ivImage = (ImageView) findViewById(R.id.ivImage);
        fastname= (EditText)findViewById(R.id.etfirst);
         lastname= (EditText)findViewById(R.id.etlast);
         email= (EditText)findViewById(R.id.etemail);
         password= (EditText)findViewById(R.id.etPassword);
         repassword= (EditText)findViewById(R.id.etrepassword);
        phone= (EditText)findViewById(R.id.etphone);
        city= (EditText)findViewById(R.id.etCity);
         address= (EditText)findViewById(R.id.etaddress);
        country= (EditText)findViewById(R.id.etcountry);
         state= (EditText)findViewById(R.id.etstate);
        Register=(Button)findViewById(R.id.btRegister);
        Register.setOnClickListener(new View.OnClickListener()
        {

   public void onClick(View v1) {
        int id = v1.getId();
        if (id == R.id.ivImage) {

        } else if (id == R.id.btRegister) {
            if (isValidate()) {
                SharedPreferences sharedPreferences=getSharedPreferences("REGISTRATION",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Name", fName + " " + lName);
                editor.commit();
                Intent intent=new Intent(registerActivity.this,GMapsActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }});}
  private boolean isValidate() {
      fName = fastname.getText().toString();//  private String fName, lName, Email,pass,repass,phon,cit,addr,countr,stat;
      lName = lastname.getText().toString();
      Email= email.getText().toString();
      repass=repassword.getText().toString();
      pass = password.getText().toString();
      phon=phone.getText().toString();
      cit=city.getText().toString();
      addr=address.getText().toString();
      countr=country.getText().toString();
      stat=state.getText().toString();
      if(fName.equals("")){
          fastname.setError("Enter first name");
          fastname.requestFocus();
          return false;
      }else if(lName.equals("")){
          lastname.setError("Enter last name");
          lastname.requestFocus();
          return false;
      }
      else if(pass.equals("")){
          password.setError("Enter password");
          password.requestFocus();
          return false;
      }
      else if(pass!=repass){
          password.setError("Password doesnt match");
          password.requestFocus();
          return false;
      }else if(Email.equals("")){
          email.setError("Enter email");
          email.requestFocus();
          return false;
      }
      else if(phon.equals("")){
          phone.setError("Enter Phone number");
          phone.requestFocus();
          return false;
      }
      else if(cit.equals("")){
          city.setError("Enter your cityr");
          city.requestFocus();
          return false;
      }
      else if(stat.equals("")){
          state.setError("Enter your state");
          state.requestFocus();
          return false;
      }
      else if(countr.equals("")){
          country.setError("Enter your country");
          country.requestFocus();
          return false;
      }
      return true;
  }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(registerActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=utility.checkPermission(registerActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage.setImageBitmap(bm);
    }

}
