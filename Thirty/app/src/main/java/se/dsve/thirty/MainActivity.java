package se.dsve.thirty;
/*
  __
 /\ \
 \_\ \    ____  __  __     __
 /'_` \  /',__\/\ \/\ \  /'__`\
/\ \L\ \/\__, `\ \ \_/ |/\  __/
\ \___,_\/\____/\ \___/ \ \____\
 \/__,_ /\/___/  \/__/   \/____/
       https://dsve.se/
*/

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-07-05
 * https://github.com/deskavaenkelt/
 */



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_CODE_TURN_ENDED = 0;

    private Score[] mScoreBank = new Score[]{
            new Score(3,0),
            new Score(4,0),
            new Score(5,0),
            new Score(6,0),
            new Score(7,0),
            new Score(8,0),
            new Score(9,0),
            new Score(10,0),
            new Score(11,0),
            new Score(12,0)
    };

    private static int REQUESTED_DICE1_RESULT = 0;
    private static int REQUESTED_DICE2_RESULT = 0;
    private static int REQUESTED_DICE3_RESULT = 0;
    private static int REQUESTED_DICE4_RESULT = 0;
    private static int REQUESTED_DICE5_RESULT = 0;
    private static int REQUESTED_DICE6_RESULT = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {

//            requestCode = -1 => defaultValue
//            requestCode = 1 => get data from ThrownDices
//            requestCode = 2 => get data from SaveResult
            int mDataFromSource = data.getIntExtra("mDataFromSource", -1);

            if (1 == mDataFromSource) {
                REQUESTED_DICE1_RESULT = data.getIntExtra("resultDice0", -1);
                REQUESTED_DICE2_RESULT = data.getIntExtra("resultDice1", -1);
                REQUESTED_DICE3_RESULT = data.getIntExtra("resultDice2", -1);
                REQUESTED_DICE4_RESULT = data.getIntExtra("resultDice3", -1);
                REQUESTED_DICE5_RESULT = data.getIntExtra("resultDice4", -1);
                REQUESTED_DICE6_RESULT = data.getIntExtra("resultDice5", -1);
            } else if (2 == mDataFromSource) {

            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_main);

        Button playGame = findViewById(R.id.btnPlay);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start throw_dice_activity
                Log.d(TAG, "onClick: ThrowDices.class called");

                Intent mIntent = new Intent(MainActivity.this, ThrowDices.class);
                mIntent.putExtra("dice0", 2);
                mIntent.putExtra("dice1", 1);
                startActivityForResult(mIntent, REQUEST_CODE_TURN_ENDED);
            }
        });

        Button mCurrentResultButton = findViewById(R.id.btnResult);
        mCurrentResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SaveResult.class);
                startActivity(intent);
            }
        });

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
        Log.d(TAG, "onDestroy: called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: called");
    }
}


//                Intent intent = new Intent(MainActivity.this, ThrowDices.class);
//                boolean answerIsTrue = true;
//                Intent intent = ThrowDices.newIntent(MainActivity.this, REQUESTED_DICE1_RESULT, REQUESTED_DICE2_RESULT,REQUESTED_DICE3_RESULT, REQUESTED_DICE4_RESULT, REQUESTED_DICE5_RESULT, REQUESTED_DICE6_RESULT);
//                startActivity(intent);
//                startActivityForResult(mIntent, REQUEST_CODE_TURN_ENDED);
//                Log.i(TAG, "onClick: result is: " +
//                        REQUESTED_DICE1_RESULT + ", " +
//                        REQUESTED_DICE2_RESULT + ", " +
//                        REQUESTED_DICE3_RESULT + ", " +
//                        REQUESTED_DICE4_RESULT + ", " +
//                        REQUESTED_DICE5_RESULT + ", " +
//                        REQUESTED_DICE6_RESULT);

/*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_TURN_ENDED) {
            if (data == null) {
                return;
            }
            data = getIntent();
            REQUESTED_DICE1_RESULT = data.getExtras().getInt("dice0");
        }
        Log.i(TAG, "onActivityResult: dice0 is " + REQUESTED_DICE1_RESULT);
    }

 */