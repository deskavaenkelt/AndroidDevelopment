package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowResult extends AppCompatActivity {
    private static final String TAG = "ShowResult";

    private TextView mSendToTextView;
    private TextView mTotalScoreTextView;
    private Button mResetButton;

    private Score[] mResultBank = new Score[]{
            new Score(3, 0),
            new Score(4, 0),
            new Score(5, 0),
            new Score(6, 0),
            new Score(7, 0),
            new Score(8, 0),
            new Score(9, 0),
            new Score(10, 0),
            new Score(11, 0),
            new Score(12, 0)
    };

    private void receivedIntent() {
        String[] scoreBank = new String[]{"r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10"};
        Intent receivedIntent = getIntent();
        for (int i = 0; i < scoreBank.length; i++) {
            mResultBank[i].updateScore(i, receivedIntent.getIntExtra(scoreBank[i], 0));
        }
    }

    private void returnThis(boolean restart) {
        Intent sendBackThis = new Intent();
        sendBackThis.putExtra("resetGame", restart);
        setResult(RESULT_OK, sendBackThis);
    }

    private boolean checkIfGameOver() {
        for (int i = 0; i < mResultBank.length; i++) {
            if (mResultBank[i].isAllowedToChange()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        receivedIntent();
        mSendToTextView = findViewById(R.id.tvSentTextHere);
        mTotalScoreTextView = findViewById(R.id.twTotalResult);
        mResetButton = findViewById(R.id.btnRestartGame);

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnThis(true);
                finish();
            }
        });

        String output = "3 or less = " + mResultBank[0].getScore() + "\n";
        for (int i = 1; i < mResultBank.length; i++) {
            output += "Sum of " + (i+4) + " = " + mResultBank[i].getScore() + "\n";
        }
        mSendToTextView.setText(output);

        int sum = 0;
        for (int i = 0; i < mResultBank.length; i++) {
            sum += mResultBank[i].getScore();
        }
        String mTotalScore = "Total score is " + sum;
        mTotalScoreTextView.setText(mTotalScore);

        if (checkIfGameOver()) {
            mResetButton.setVisibility(View.VISIBLE);
        } else {
            mResetButton.setVisibility(View.INVISIBLE);
        }

        returnThis(false);
    }
}
