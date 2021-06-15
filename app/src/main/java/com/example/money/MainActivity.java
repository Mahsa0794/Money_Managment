package com.example.money;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPass;
    private Button btnLoin;
    private TextView mForgetPassword;
    private TextView mSignUpHere;

    private ProgressDialog mDialog;

    //Firebase...

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();



        mDialog = new ProgressDialog(this);

        loginDetails();

    }

    private void loginDetails(){

        mEmail=findViewById(R.id.email_login);
        mPass=findViewById(R.id.Password_login);
        btnLoin=findViewById(R.id.btn_login);
        mForgetPassword=findViewById(R.id.forget_password);
        mSignUpHere=findViewById(R.id.Sign_up);

        btnLoin.setOnClickListener(new View.OnClickListener() {
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
                mDialog.setMessage("Proccessing...");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            mDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Login Successful.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,
                                    HomeActivity.class);
                            startActivity(intent);

                        }
                        else
                        {

                            mDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        //Sign up Activity
        mSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationsActivity.class);
                startActivity(intent);
            }
        });

        //Reset Password Activity
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });
    }
}