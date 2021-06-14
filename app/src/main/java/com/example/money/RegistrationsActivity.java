package com.example.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationsActivity<Private> extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPass;
    private Button btnReg;
    private TextView mSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations);

        registration();

    }

    private  void registration(){

        mEmail=findViewById(R.id.email_reg);
        mPass=findViewById(R.id.Password_reg);
        btnReg=findViewById(R.id.btn_reg);
        mSignin=findViewById(R.id.sign_in_here);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=mEmail.getText().toString().trim();
                String pass=mPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    mEmail.setError("Email Required..");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {

                    mPass.setError("Password Required..");
                    return;
                }
            }
        });

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationsActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

    }
}