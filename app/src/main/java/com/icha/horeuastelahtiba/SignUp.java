package com.icha.horeuastelahtiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //variabel
    TextInputLayout daftar_fullname, daftar_username, daftar_email, daftar_phone, daftar_password;
    Button daftar_go, daftar_kelogin;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        daftar_kelogin = findViewById(R.id.daftarkelogin);
        daftar_kelogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }

        });

        //Firebase Instantiation
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        //Hooks to all xml elements in activity_sign_up.xml
        daftar_fullname = findViewById(R.id.daftarfullname);
        daftar_username = findViewById(R.id.daftarusername);
        daftar_email = findViewById(R.id.daftaremail);
        daftar_phone = findViewById(R.id.daftarphone);
        daftar_password = findViewById(R.id.daftarpassword);


    }

    private Boolean validateName() {
        String val = daftar_fullname.getEditText().getText().toString();

        if (val.isEmpty()) {
            daftar_fullname.setError("Field cannot be empty");
            return false;
        } else {
            daftar_fullname.setError(null);
            daftar_fullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = daftar_username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            daftar_username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            daftar_username.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            daftar_username.setError("White Spaces are not allowed");
            return false;
        } else {
            daftar_username.setError(null);
            daftar_username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = daftar_email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            daftar_email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            daftar_email.setError("Invalid email address");
            return false;
        } else {
            daftar_email.setError(null);
            daftar_email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = daftar_phone.getEditText().getText().toString();

        if (val.isEmpty()) {
            daftar_phone.setError("Field cannot be empty");
            return false;
        } else {
            daftar_phone.setError(null);
            daftar_phone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = daftar_password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter

                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            daftar_password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            daftar_password.setError("Password is too weak");
            return false;
        } else {
            daftar_password.setError(null);
            daftar_password.setErrorEnabled(false);
            return true;
        }
    }

    //This function will execute when user click on Register Button
    public void registerUser(View view) {

        //Performing Validation by calling validation functions
        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }

        //Get all the values in String
        String name = daftar_fullname.getEditText().getText().toString();
        String username = daftar_username.getEditText().getText().toString();
        String email = daftar_email.getEditText().getText().toString();
        String phoneNo = daftar_phone.getEditText().getText().toString();
        String password = daftar_password.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);

        Context context = getApplicationContext();
        CharSequence text = "Sekarang kamu bisa login";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();




    }
}