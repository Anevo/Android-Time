package com.example.cezar.economic_timer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Login_second;
    EditText Email;
    EditText Password;
    /*
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabaseObj;
    DatabaseHelper sqLiteHelper;
    String TempPassword = "NOT_FOUND";
    public static final String UserEmail = "";*/


    //private CheckBox saveLoginCheckBox;
    //private SharedPreferences loginPreferences;
    //private SharedPreferences.Editor loginPrefsEditor;
    //private Boolean saveLogin;
    //private String usernameSave, passwordSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.ed_email_login);
        Password = (EditText) findViewById(R.id.ed_password);
        Login_second = (Button) findViewById(R.id.bt_login_second);

        Login_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
                // Calling EditText is empty or no method.
                //CheckEditTextStatus();
                // Calling login method.
                //LoginFunction();
            }
        });
    }

    private void validate(String usrEmail, String userPassword) {
        if ((usrEmail.equals("test@test.com")) && (userPassword.equals("test"))) {
            Toast.makeText(MainActivity.this,"Welcome " + usrEmail, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, TimerActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Email and password do not match!", Toast.LENGTH_SHORT).show();
        }
    }


    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}