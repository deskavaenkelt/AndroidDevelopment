package se.dsve.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE = "se.dsve.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "se.dsve.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    private static boolean mIsCheater = false;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packegeContext, boolean answerIsTrue) {
        Log.d(TAG, "newIntent: called");
        Intent intent = new Intent(packegeContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.answer_text_view);

        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ShowAnswerButton called");
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        mIsCheater = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mIsCheater = savedInstanceState.getBoolean("isCheater");
        setAnswerShownResult(mIsCheater);
        mAnswerTextView.setText(savedInstanceState.getCharSequence("answerTextView"));
        Log.i(TAG, "onRestoreInstanceState: called and isCheater = " + mIsCheater);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: called and isCheater = " + mIsCheater);

        outState.putBoolean("isCheater", mIsCheater);
        outState.putCharSequence("answerTextView", mAnswerTextView.getText());
    }

}















































