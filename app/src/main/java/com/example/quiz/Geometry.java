package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Geometry extends AppCompatActivity implements View.OnClickListener {
    private TextView question, question_no, time_counter;
    private Button op1, op2, op3, op4;
    private List<Questions> questionsList;
    private int current_ques_no;
    private CountDownTimer countdown;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        question = findViewById(R.id.question);
        question_no = findViewById(R.id.question_no);
        time_counter = findViewById(R.id.countdown);

        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);

        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        op4.setOnClickListener(this);

        get_question_list();
        set_Question();
        score=0;
    }

    private void get_question_list()
    {
        questionsList = new ArrayList<>();
        questionsList.add(new Questions(getString(R.string.Question1G) , getString(R.string.Option1_1G), getString(R.string.Option2_1G) ,getString(R.string.Option3_1G),getString(R.string.Option4_1G),4));
        questionsList.add(new Questions(getString(R.string.Question2G) , getString(R.string.Option1_2G), getString(R.string.Option2_2G) ,getString(R.string.Option3_2G),getString(R.string.Option4_2G),2));
        questionsList.add(new Questions(getString(R.string.Question3G) , getString(R.string.Option1_3G), getString(R.string.Option2_3G) ,getString(R.string.Option3_3G),getString(R.string.Option4_3G),3));
        questionsList.add(new Questions(getString(R.string.Question4G) , getString(R.string.Option1_4G), getString(R.string.Option2_4G) ,getString(R.string.Option3_4G),getString(R.string.Option4_4G),1));
        questionsList.add(new Questions(getString(R.string.Question5G) , getString(R.string.Option1_5G), getString(R.string.Option2_5G) ,getString(R.string.Option3_5G),getString(R.string.Option4_5G),1));
        questionsList.add(new Questions(getString(R.string.Question6G) , getString(R.string.Option1_6G), getString(R.string.Option2_6G) ,getString(R.string.Option3_6G),getString(R.string.Option4_6G),4));
        questionsList.add(new Questions(getString(R.string.Question7G) , getString(R.string.Option1_7G), getString(R.string.Option2_7G) ,getString(R.string.Option3_7G),getString(R.string.Option4_7G),3));
        questionsList.add(new Questions(getString(R.string.Question8G) , getString(R.string.Option1_8G), getString(R.string.Option2_8G) ,getString(R.string.Option3_8G),getString(R.string.Option4_8G),1));


    }

    private void set_Question()
    {
        time_counter.setText(String.valueOf(10));
        question.setText(questionsList.get(0).getQuestion());
        op1.setText(questionsList.get(0).getOption1());
        op2.setText(questionsList.get(0).getOption2());
        op3.setText(questionsList.get(0).getOption3());
        op4.setText(questionsList.get(0).getOption4());

        question_no.setText(String.valueOf(1) + "/" + String.valueOf(questionsList.size()));

        start_timer();
        current_ques_no =0;

    }

    private void start_timer()
    {
        countdown = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 10000)
                    time_counter.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                change_question();

            }
        };
        countdown.start();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        int chosen_answer = 0;
        switch (v.getId())
        {
            case R.id.option1:
                chosen_answer = 1;
                break;
            case R.id.option2:
                chosen_answer = 2;
                break;
            case R.id.option3:
                chosen_answer = 3;
                break;
            case R.id.option4:
                chosen_answer = 4;
                break;

            default:

        }

        countdown.cancel();

        check_answer(chosen_answer,v);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void check_answer(int chosen_answer, View view){
        if(chosen_answer == questionsList.get(current_ques_no).getCorrect_ans()){
            // Correct Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;

        }
        else{
            // Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionsList.get(current_ques_no).getCorrect_ans())
            {
                case 1:
                    op1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    op2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    op3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    op4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

            }

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                change_question();
            }
        },2000);


    }

    private void change_question(){
        if (current_ques_no < questionsList.size()-1){
            current_ques_no++;
            play_animation(question, 0,0);
            play_animation(op1, 0,1);
            play_animation(op2, 0,2);
            play_animation(op3, 0,3);
            play_animation(op4, 0,4);

            question_no.setText(String.valueOf(current_ques_no+1) + "/" + String.valueOf(questionsList.size()));

            time_counter.setText(String.valueOf(10));
            start_timer();

        }
        else{
            // Show score now
            Intent intent = new Intent(Geometry.this, Score_Activity.class);
            intent.putExtra("Score", String.valueOf(score)+"/"+String.valueOf(questionsList.size()));
            startActivity(intent);
            Geometry.this.finish();
        }
    }

    private void play_animation(View view, final int value, int view_num){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0){
                            switch (view_num){
                                case 0:
                                    ((TextView)view).setText(questionsList.get(current_ques_no).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionsList.get(current_ques_no).getOption1());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionsList.get(current_ques_no).getOption2());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionsList.get(current_ques_no).getOption3());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionsList.get(current_ques_no).getOption4());
                                    break;

                            }

                            if (view_num!=0)
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E5C91A")));

                            play_animation(view,1,view_num);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }
}