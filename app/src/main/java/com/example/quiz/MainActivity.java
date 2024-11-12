package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView total_question;
    TextView question;
    Button ans_A,ans_B,ans_C,ans_D;
    Button submit_button;
    int score=0;
    int totalquestions =questinanswer.question.length;
    int currentquestionindex =0;
    String selectedanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total_question =findViewById(R.id.total_question);
        question=findViewById(R.id.question);
        ans_A=findViewById(R.id.ans_A);
        ans_B=findViewById(R.id.ans_B);
        ans_C=findViewById(R.id.ans_C);
        ans_D=findViewById(R.id.ans_D);
        submit_button=findViewById(R.id.submit_button);

        ans_A.setOnClickListener(this);
        ans_B.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_D.setOnClickListener(this);
        submit_button.setOnClickListener(this);
        total_question.setText("Total question" +  totalquestions);
        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        ans_A.setBackgroundColor(Color.WHITE);
        ans_B.setBackgroundColor(Color.WHITE);
        ans_C.setBackgroundColor(Color.WHITE);
        ans_D.setBackgroundColor(Color.WHITE);
        Button clickedbutton= (Button) v;
        if(clickedbutton.getId()==R.id.submit_button){
            if(selectedanswer.equals(questinanswer.correctanswers[currentquestionindex])){
                score++;

            }
            currentquestionindex++;
            loadNewQuestion();

        }
        else {
            selectedanswer=clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }


    }
    void loadNewQuestion(){
        if (currentquestionindex==totalquestions){
            finishQuiz();
            return;
        }
        question.setText(questinanswer.question[currentquestionindex]);
        ans_A.setText(questinanswer.choices[currentquestionindex][0]);
        ans_B.setText(questinanswer.choices[currentquestionindex][1]);
        ans_C.setText(questinanswer.choices[currentquestionindex][2]);
        ans_D.setText(questinanswer.choices[currentquestionindex][3]);
    }
   void finishQuiz(){
       String passstatus="";
       if(score>totalquestions*0.60){
           passstatus="Passed";
       }
       else {
           passstatus="Failed";
       }
       new AlertDialog.Builder(this)
               .setTitle(passstatus)
               .setMessage("Score is"+score+" out of "+totalquestions)
               .setPositiveButton("Restart",((dialogInterface,i) ->restartQuiz() ))
               .setCancelable(false)
               .show();
   }
   void restartQuiz(){

        score=0;
        currentquestionindex=0;
        loadNewQuestion();
   }
}