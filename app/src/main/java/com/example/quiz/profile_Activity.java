package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class profile_Activity extends AppCompatActivity {
    TextView tv_1; TextView tv_2; TextView tv_3; TextView tv_4;
    Button arrowBtn1; Button arrowBtn2; Button arrowBtn3; Button arrowBtn4; Button button;
    ImageView image1;
    Toolbar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv_1=(TextView)findViewById(R.id.tv_1);
        tv_2=(TextView)findViewById(R.id.tv_2);
        tv_3=(TextView)findViewById(R.id.tv_3);
        tv_4=(TextView)findViewById(R.id.tv_4);
        arrowBtn1=(Button)findViewById(R.id.arrowBtn1);
        arrowBtn2=(Button)findViewById(R.id.arrowBtn2);
        arrowBtn3=(Button)findViewById(R.id.arrowBtn3);
        arrowBtn4=(Button)findViewById(R.id.arrowBtn4);
        button=(Button)findViewById(R.id.button);
        image1=(ImageView)findViewById(R.id.image1);
        titleBar=(Toolbar)findViewById(R.id.titleBar);

    }

    public void back(View view) {
        Intent intent=new Intent(profile_Activity.this,DashBoard.class);
        startActivity(intent);
    }

    public void dropDown3(View view) {
        Toast.makeText(profile_Activity.this,"khan4201261@cloud.neduet.edu.pk",Toast.LENGTH_SHORT).show();
    }
    public void dropDown1(View view) {
        Toast.makeText(profile_Activity.this,"akhter4203255@cloud.neduet.edu.pk",Toast.LENGTH_SHORT).show();
    }
    public void dropDown4(View view) {
        Toast.makeText(profile_Activity.this,"Cloud Id:\nsaleem4207826@cloud.neduet.edu.pk",Toast.LENGTH_SHORT).show();
    }

    public void dropDown2(View view) {
        Toast.makeText(profile_Activity.this,"baig4202219@cloud.neduet.edu.pk",Toast.LENGTH_SHORT).show();
    }
}


