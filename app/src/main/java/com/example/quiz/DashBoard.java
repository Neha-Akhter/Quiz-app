package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class DashBoard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    CardView algebraCard,trigCard, geoCard,num_card;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private Toolbar appBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar appBar = findViewById(R.id.appBar);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        setSupportActionBar(appBar);
        mainDrawer = (DrawerLayout) findViewById(R.id.mainDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawer, appBar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //defining cardd
        algebraCard = (CardView) findViewById(R.id.algebra_card);
        trigCard = (CardView) findViewById(R.id.trig_card);
        geoCard = (CardView) findViewById(R.id.geometry_card);

        num_card = (CardView) findViewById(R.id.numbers_card);


        //Adding on click listeners
        algebraCard.setOnClickListener(this);
        trigCard.setOnClickListener(this);
        geoCard.setOnClickListener(this);

        num_card.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.algebra_card:
                i = new Intent(this, Algebra.class);
                startActivity(i);
                break;
            case R.id.trig_card:
                i = new Intent(this, Trigonometry.class);
                startActivity(i);
                break;
            case R.id.geometry_card:
                i = new Intent(this, Geometry.class);
                startActivity(i);
                break;
            case R.id.numbers_card:
                i = new Intent(this, Numbers.class);
                startActivity(i);
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.profile:
                Intent intent=new Intent(DashBoard.this,profile_Activity.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                Intent intent1=new Intent(DashBoard.this,FeedbackActivity.class);
                startActivity(intent1);
                break;
            default:
                return true;

        }
        return false;
    }
}


