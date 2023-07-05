package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    TextView tv3;
    TextView tv4;
    TextView SignUp;
    EditText etEmailAddress;
    EditText etPassword;
    Button btn_Login;
    ImageView img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        SignUp=(TextView)findViewById(R.id.btn_Login);
        etEmailAddress=(EditText)findViewById(R.id.etEmailAddress);
        etPassword=(EditText)findViewById(R.id.etPassword);
        img3=(ImageView)findViewById(R.id.img3);
        btn_Login=(Button)findViewById(R.id.btn_Login);
        firebaseAuth= FirebaseAuth.getInstance();

        }

    private void LoginUser(){
        String email =etEmailAddress.getText().toString();
        String pass =etPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"Field cant be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Activity_Login.this, "Successful Login", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Activity_Login.this,DashBoard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Activity_Login.this, "Unsuccessful Attempt", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void Log(View view) {
        LoginUser();
    }

    public void GotoSign(View view) {
        Intent intent=new Intent(Activity_Login.this,SignUpActivity.class);
        startActivity(intent);
    }

}









