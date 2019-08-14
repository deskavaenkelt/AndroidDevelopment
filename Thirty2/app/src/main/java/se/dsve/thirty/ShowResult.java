package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowResult extends AppCompatActivity {

    private boolean mGameOver;

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

    // Updates entire object every time the function is called
    private void receivedIntent() {
        String[] scoreBank = new String[]{"r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10"};
        String isGameOver = "gameOver";

        Intent receivedIntent = getIntent();
        for (int i = 0; i < scoreBank.length; i++) {
            mResultBank[i].updateScore(i, receivedIntent.getIntExtra(scoreBank[i], 0));
        }
        mGameOver = receivedIntent.getBooleanExtra(isGameOver, false);
    }

    private void returnThis(/*boolean restart*/) {
        // Could have done this check in Throw dices but wanted to return something
//        String resetGame = "reset_game";
        Intent sendBackThis = new Intent();
//        sendBackThis.putExtra(resetGame, restart);
        setResult(RESULT_OK, sendBackThis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        receivedIntent();
        TextView sendToTextView = findViewById(R.id.tvSentTextHere);
        TextView totalScoreTextView = findViewById(R.id.twTotalResult);

        Button resetButton = findViewById(R.id.btnRestartGame);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                returnThis(true);
                finish();
            }
        });

        // Print out all results
        StringBuilder output = new StringBuilder("3 or less = " + mResultBank[0].getScore() + "\n");
        for (int i = 1; i < mResultBank.length; i++) {
            output.append("Sum of ").append(i + 4).append(" = ").append(mResultBank[i].getScore()).append("\n");
        }
        sendToTextView.setText(output.toString());

        // Print out the total score for all results
        int sum = 0;
        for (Score score : mResultBank) {
            sum += score.getScore();
        }
        String mTotalScore = "Total score is " + sum;
        totalScoreTextView.setText(mTotalScore);

        // Check if the game is over
        if (mGameOver) {
            resetButton.setVisibility(View.VISIBLE);
        } else {
            resetButton.setVisibility(View.INVISIBLE);
        }

        returnThis(/*false*/);
    }
}
