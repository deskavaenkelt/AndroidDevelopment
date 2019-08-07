package se.dsve.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
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
    private static final int REQUEST_CODE_CHEAT = 0;

    private static final String ANSWER[] = new String[] { "zero", "one", "two", "three", "four", "five"};
    private static final String HAS_ANSWERED[] = new String[] { "zero", "one", "two", "three", "four", "five"};

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
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
    private boolean[] mHasAlreadyAnswered = new boolean[6];

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

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
                nextQuestion();
                Log.d(TAG, "onClick: FALSE_BUTTON");
            }
        });

        mCheatButton = findViewById(R.id.btnCheat);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start cheat activity
                Log.d(TAG, "onClick: CheatActivity called");
//                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mNextButton = findViewById(R.id.btnNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = false;
                nextQuestion();
            }
        });


        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = false;
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

    private void previousQuestion() {
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        if (mCurrentIndex < 0) {
            mCurrentIndex = mQuestionBank.length - 1;
        }
        updateQuestion();
    }

    private void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        if (!isAllQuestionsAnswered()) {
            updateQuestion();
        } else {
            int score = calculateScore();
            String text = "Score is " + score + "/6p";
            mResultTextView.setText(text);
        }

    }

    private void updateQuestion() {
//        Log.d(TAG, "updateQuestion: ", new Exception());
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        if (mHasAlreadyAnswered[mCurrentIndex]) {
            mResultTextView.setText(R.string.already_answered);
        } else {
            mResultTextView.setText(R.string.not_answered);
        }
    }

    private boolean isAllQuestionsAnswered() {
        int sum = 0;
        for (boolean b : mHasAlreadyAnswered) {
            if (b) {
                sum++;
            }
        }

        if (sum == mHasAlreadyAnswered.length) {
            Log.d(TAG, "isAllQuestionsAnswered: true");
            return true;
        }

        Log.d(TAG, "isAllQuestionsAnswered: false");
        return false;
    }

    private int calculateScore() {
        int totalScore = 0;

        for (int i = 0; i < mHasAlreadyAnswered.length; i++) {
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

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }

        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private void storeAnswer(boolean answerIsTrue) {
        if (!mHasAlreadyAnswered[mCurrentIndex]) {
            mUserAnswer[mCurrentIndex] = answerIsTrue;
            mHasAlreadyAnswered[mCurrentIndex] = true;
        }
        for (int i = 0; i < mUserAnswer.length; i++) {
            Log.d(TAG, "storeAnswer: index " + i + " = " + mUserAnswer[i] + ", is answered = " + mHasAlreadyAnswered[i]);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
        Log.i(TAG, "onActivityResult: mIsCheater is " +mIsCheater);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        for (int i = 0; i < ANSWER.length; i++) {
            mUserAnswer[i] = savedInstanceState.getBoolean(ANSWER[i]);
        }

        for (int i = 0; i < HAS_ANSWERED.length; i++) {
            mHasAlreadyAnswered[i] = savedInstanceState.getBoolean(HAS_ANSWERED[i]);
        }

        mResultTextView.setText(savedInstanceState.getCharSequence("Points"));

        mIsCheater = savedInstanceState.getBoolean("isCheater");
        Log.i(TAG, "onRestoreInstanceState: and isCheater = " + mIsCheater);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: called");
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: called");
        outState.putInt(KEY_INDEX, mCurrentIndex);

        for (int i = 0; i < ANSWER.length; i++) {
            outState.putBoolean(ANSWER[i], mUserAnswer[i]);
        }

        for (int i = 0; i < HAS_ANSWERED.length; i++) {
            outState.putBoolean(HAS_ANSWERED[i], mHasAlreadyAnswered[i]);
        }

        outState.putCharSequence("Points", mResultTextView.getText());

        outState.putBoolean("isCheater", mIsCheater);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
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
}













