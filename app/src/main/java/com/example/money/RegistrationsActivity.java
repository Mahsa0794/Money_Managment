package com.example.money;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationsActivity<Private> extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPass;
    private Button btnReg;
    private TextView mSignin;

    private ProgressDialog mDialog;

    //Firebase...

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations);

        mAuth=FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

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

                mDialog.setMessage("Proccessing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            mDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Registration Completed",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegistrationsActivity.this,
                                    HomeActivity.class);
                            startActivity(intent);

                        }
                        else
                        {

                            mDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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