package com.example.cezar.economic_timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginRegActivity extends AppCompatActivity implements View.OnClickListener{
    private Button Login_first;
    private Button Register_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        initViews();
        initListeners();
    }

    private void initViews(){
        Login_first = (Button) findViewById(R.id.bt_login);
        Register_first = (Button) findViewById(R.id.bt_register);
    }

    private void initListeners(){
        Login_first.setOnClickListener(this);
        Register_first.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                Intent intent1 = new Intent(LoginRegActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_register:
                Intent intent2 = new Intent(LoginRegActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
