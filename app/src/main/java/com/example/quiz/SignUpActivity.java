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
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.ProgressBar;


    public class SignUpActivity extends AppCompatActivity {
        FirebaseAuth firebaseAuth;
        TextView tv5;
        TextView tv6;
        EditText etsignemail;
        EditText etsignPassword;
        EditText etConfirm;
        Button btn_SignUp;
        ImageView img4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            tv5=(TextView)findViewById(R.id.tv5);
            tv6=(TextView)findViewById(R.id.tv6);
            etsignemail=(EditText)findViewById(R.id.etsignemail);
            etsignPassword=(EditText)findViewById(R.id.etsignPassword);
            etConfirm=(EditText)findViewById(R.id.etConfirm);
            img4=(ImageView)findViewById(R.id.img4);
            btn_SignUp=(Button)findViewById(R.id.btn_SignUp);
            firebaseAuth= FirebaseAuth.getInstance();



            btn_SignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newUsernameString = etsignemail.getText().toString().trim();
                    String newUserPasswordString = etsignPassword.getText().toString().trim();
                    String ConfirmPasswordString= etConfirm.getText().toString().trim();


                    if(TextUtils.isEmpty(newUsernameString)){
                        Toast.makeText(SignUpActivity.this,"Field cant be empty",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(newUserPasswordString)){
                       Toast.makeText(SignUpActivity.this,"Field cant be empty",Toast.LENGTH_SHORT).show();
                       return;
                    }

                    if (TextUtils.isEmpty(ConfirmPasswordString)){
                        Toast.makeText(SignUpActivity.this,"Field cant be empty",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    //firebase
                    firebaseAuth.createUserWithEmailAndPassword(newUsernameString,newUserPasswordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Successful SignUp", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this,DashBoard.class);
                                startActivity(intent);}
                            else {
                                Toast.makeText(SignUpActivity.this, "Unsuccessful SignUp", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });



        }

    }
