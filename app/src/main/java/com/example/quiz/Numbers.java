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

public class Numbers extends AppCompatActivity implements View.OnClickListener {
    private TextView question, question_no, time_counter;
    private Button op1, op2, op3, op4;
    private List<Questions> questionsList;
    private int current_ques_no;
    private CountDownTimer countdown;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
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
        questionsList.add(new Questions("Can we write 0 in the form of p/q?", "Yes", "No", "Cannot be explained", "None of the above",1));
        questionsList.add(new Questions(" In between any two numbers, there are:", "Only one rational number", "Two rational numbers", "Infinite rational numbers", "No rational number",3));
        questionsList.add(new Questions("Every rational number is:", "Whole number", "Natural number", "Integer", "Real number",4));
        questionsList.add(new Questions("√9 is  __________ number.", "A rational", "An irrational", "Neither rational nor irrational", "None of the above",1));
        questionsList.add(new Questions("√6 x √27 is equal to:", "9√2", "3√3", "2√2", "9√3",1));
        questionsList.add(new Questions("Which of the following is equal to x^3?", "x^6 – x^3", "x^6.x^3", "x^6/x^3", "(x^6)^3",3));
        questionsList.add(new Questions("How many prime numbers are less than 50 ? ", "16", "14", "15", "18",3));
        questionsList.add(new Questions("4500 x ? = 3375", "2/5", "3/4", "1/4", "3/5",2));
        questionsList.add(new Questions("Which one of the following is not a prime number?", "31", "61", "71", "91",4));
        questionsList.add(new Questions("The smallest prime number is:", "1", "3", "2", "4",3));

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
            Intent intent = new Intent(Numbers.this, Score_Activity.class);
            intent.putExtra("Score", String.valueOf(score)+"/"+String.valueOf(questionsList.size()));
            startActivity(intent);
            Numbers.this.finish();
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