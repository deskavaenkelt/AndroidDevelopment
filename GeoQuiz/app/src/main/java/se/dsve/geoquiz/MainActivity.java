package se.dsve.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private TextView mResultTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private boolean[] mUserAnswer = new boolean[6];
    private boolean[] mHasAlreadeAnswerd = new boolean[6];

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate (Bundle): called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);
        mResultTextView = findViewById(R.id.result_text_view);

        mTrueButton = findViewById(R.id.btnTrue);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // .this refers to View.OnClickListener
                checkAnswer(true);
                nextQuestion();
                Log.d(TAG, "onClick: TRUE_BUTTON");
            }
        });

        mFalseButton = findViewById(R.id.btnFalse);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                Log.d(TAG, "onClick: FALSE_BUTTON");
            }
        });

        mNextButton = findViewById(R.id.btnNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        mPreviousButton = findViewById(R.id.btnPrevious);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: called");
        outState.putInt(KEY_INDEX, mCurrentIndex);

        outState.putBoolean("Answer0", mUserAnswer[0]);
        outState.putBoolean("Answer1", mUserAnswer[1]);
        outState.putBoolean("Answer2", mUserAnswer[2]);
        outState.putBoolean("Answer3", mUserAnswer[3]);
        outState.putBoolean("Answer4", mUserAnswer[4]);
        outState.putBoolean("Answer5", mUserAnswer[5]);

        outState.putBoolean("HasAnswerd0", mHasAlreadeAnswerd[0]);
        outState.putBoolean("HasAnswerd1", mHasAlreadeAnswerd[1]);
        outState.putBoolean("HasAnswerd2", mHasAlreadeAnswerd[2]);
        outState.putBoolean("HasAnswerd3", mHasAlreadeAnswerd[3]);
        outState.putBoolean("HasAnswerd4", mHasAlreadeAnswerd[4]);
        outState.putBoolean("HasAnswerd5", mHasAlreadeAnswerd[5]);

        outState.putCharSequence("Points", mResultTextView.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUserAnswer[0] = savedInstanceState.getBoolean("Answer0");
        mUserAnswer[1] = savedInstanceState.getBoolean("Answer1");
        mUserAnswer[2] = savedInstanceState.getBoolean("Answer2");
        mUserAnswer[3] = savedInstanceState.getBoolean("Answer3");
        mUserAnswer[4] = savedInstanceState.getBoolean("Answer4");
        mUserAnswer[5] = savedInstanceState.getBoolean("Answer5");

        mHasAlreadeAnswerd[0] = savedInstanceState.getBoolean("HasAnswerd0");
        mHasAlreadeAnswerd[1] = savedInstanceState.getBoolean("HasAnswerd1");
        mHasAlreadeAnswerd[2] = savedInstanceState.getBoolean("HasAnswerd2");
        mHasAlreadeAnswerd[3] = savedInstanceState.getBoolean("HasAnswerd3");
        mHasAlreadeAnswerd[4] = savedInstanceState.getBoolean("HasAnswerd4");
        mHasAlreadeAnswerd[5] = savedInstanceState.getBoolean("HasAnswerd5");


        mResultTextView.setText(savedInstanceState.getCharSequence("Points"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called\n================");
    }

    private void previousQuestion() {
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        if (mCurrentIndex < 0) {
            mCurrentIndex = mQuestionBank.length - 1;
        }
        updateQuestion();
    }

    private void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        if (!mHasAlreadeAnswerd[5]) {
            updateQuestion();
        } else {
            int score = calculateScore();
            String text = "Score is " + score + "/6p";
            mResultTextView.setText(text);
        }

    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        if (mHasAlreadeAnswerd[mCurrentIndex]) {
            mResultTextView.setText("Already answered");
        } else {
            mResultTextView.setText("Not answered");
        }
    }

    private boolean isAllQuestionsAnswered() {
        int sum = 0;
        for (int i = 0; i < mHasAlreadeAnswerd.length; i++) {
            if (mHasAlreadeAnswerd[i]) {
                sum++;
            }
        }

        if (sum == mHasAlreadeAnswerd.length) {
            Log.d(TAG, "isAllQuestionsAnswered: true");
            return true;
        }

        Log.d(TAG, "isAllQuestionsAnswered: false");
        return false;
    }

    private int calculateScore() {
        int totalScore = 0;

        for (int i = 0; i < mHasAlreadeAnswerd.length; i++) {
            if (mUserAnswer[i] == mQuestionBank[i].isAnswerTrue()) {
                totalScore++;
            }
        }

        return totalScore;
    }

    private void checkAnswer(boolean userPressedTrue) {
        storeAnswer(userPressedTrue);
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private void storeAnswer(boolean answerIsTrue) {
        if (!mHasAlreadeAnswerd[mCurrentIndex]) {
            mUserAnswer[mCurrentIndex] = answerIsTrue;
            mHasAlreadeAnswerd[mCurrentIndex] = true;
        }
        for (int i = 0; i < mUserAnswer.length; i++) {
            Log.d(TAG, "storeAnswer: index " + i + " = " + mUserAnswer[i] + ", is answered = " + mHasAlreadeAnswerd[i]);
        }
    }
}













