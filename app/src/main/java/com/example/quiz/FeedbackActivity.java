package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ImageView imageView;
    RatingBar ratingBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textView=(TextView)findViewById(R.id.tv_feed);
        textView=(TextView)findViewById(R.id.tv_det);
        imageView=(ImageView)findViewById(R.id.img1);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        editText=(EditText)findViewById(R.id.et_rate);
        button=(Button)findViewById(R.id.submit);

    }

    public void Submission(View view) {
        Toast.makeText(FeedbackActivity.this,"Your feedback is recorded Thankyou!",Toast.LENGTH_SHORT).show();

    }
}