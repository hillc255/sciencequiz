package com.example.android.sciencequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.android.sciencequiz.Constants.*;

/**
 * This app displays a science quiz with multiple screens
 * Question 1
 */
public class Main2Activity extends AppCompatActivity {

    Button nextButton;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    TextView textView;
    String buttonSelection;
    int correctResponse;
    int incorrectResponse;
    int quizNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        displayQuizCounter();

        // displayRadioButtons();

        //Radio button 2 is the correct answer
        radioButton = findViewById(R.id.radioButton2);
        //Display correct answer in this textView
        textView = findViewById(R.id.answer);

        //sets up radio button group
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Disable all radio buttons once one is checked
                int max = group.getChildCount();
                for (int i = 0; i < max; i++) {
                    group.getChildAt(i).setEnabled(false);
                }

                //Next button set invisible unless radio button is checked
                Button nextButton = findViewById(R.id.nextButton);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    nextButton.setVisibility(View.GONE);
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                }

                RadioButton radiobutton = findViewById(checkedId);
                // Set the checked radio button background color from hex string
                radiobutton.setBackgroundColor(Color.parseColor("#7e7e7e"));
                radiobutton.setTextColor(Color.parseColor("#ffffff"));

                //Determine if radio button selection is correct or incorrect - display response
                if (radioButton.isChecked()) {
                    textView.setText(R.string.quiz1_correct);
                    correctResponse = correctResponse + 1;
                } else {
                    textView.setText(R.string.quiz1_incorrect);
                    incorrectResponse = incorrectResponse + 1;
                }
                //Display correct and incorrect score
                displayScoreCounter(correctResponse, incorrectResponse);
            }

        });

        //Find the Next button and assign a listener
        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Start the next activity and pass the variables
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra(QUIZ_NUMBER_STRING, quizNumber);
                intent.putExtra(CORRECT_RESPONSE_STRING, correctResponse);
                intent.putExtra(INCORRECT_RESPONSE_STRING, incorrectResponse);
                startActivity(intent);
            }
        });
    }


    /**
     * This method displays the current quiz number
     */
    private void displayQuizCounter() {
        quizNumber = quizNumber + 1;
        TextView quizTextView = findViewById(R.id.questionNumber);
        quizTextView.setText(quizNumber + "/12");
    }

    /**
     * This method displays the correct and incorrect responses
     *
     * @param correctResponse
     * @param incorrectResponse
     */
    private void displayScoreCounter(int correctResponse, int incorrectResponse) {
        TextView scoreTextView = findViewById(R.id.scoreCount);
        scoreTextView.setText(correctResponse + "/" + incorrectResponse);
    }

}
