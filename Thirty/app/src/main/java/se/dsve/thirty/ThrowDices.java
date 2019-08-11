package se.dsve.thirty;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ThrowDices extends AppCompatActivity {
    private static final String TAG = "ThrowDices";

    private static boolean mRoundHasEnded = false;

    private static final String ROUND_HAS_ENDED = "se.dsve.thirty.round_has_ended";

    private static int sValueOfDice[] = new int[]{0, 0, 3, 4, 5, 6};
    private static boolean sLockDice[] = new boolean[]{false, false, false, false, false, false};

    private Button mDice[] = new Button[6];

    private Button mRollDiceButton;
    private boolean mChangeTextOnRollDiceButton = false;

    private int mCountTurns = 1;
    private boolean mAllowedToChangeState = false;

    RadioGroup mRadioGroup;
    RadioButton mRadioButton;
    ConstraintLayout mClWhereToSave;

    RadioButton mRadioButtons[] = new RadioButton[10];

    private void saveResult() {
        mRadioGroup = findViewById(R.id.radioGroup);
        mClWhereToSave = findViewById(R.id.whereToSave);
//        mClWhereToSave.setVisibility(View.VISIBLE);
    }

    public void checkButton(View view) {
        int mRadioId = mRadioGroup.getCheckedRadioButtonId();
        initRadioButons();

        mRadioButton = findViewById(mRadioId);

        if (mRadioButton == mRadioButtons[0]) {
            Toast.makeText(this, "Save result in 3", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[1]) {
            Toast.makeText(this, "Save result in 4", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[2]) {
            Toast.makeText(this, "Save result in 5", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[3]) {
            Toast.makeText(this, "Save result in 6", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[4]) {
            Toast.makeText(this, "Save result in 7", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[5]) {
            Toast.makeText(this, "Save result in 8", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[6]) {
            Toast.makeText(this, "Save result in 9", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[7]) {
            Toast.makeText(this, "Save result in 10", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[8]) {
            Toast.makeText(this, "Save result in 11", Toast.LENGTH_SHORT).show();
        } else if (mRadioButton == mRadioButtons[9]) {
            Toast.makeText(this, "Save result in 12", Toast.LENGTH_SHORT).show();
        }

        setChangeTextOnRollDiceButton(true);
    }

    private void initRadioButons() {
        mRadioButtons[0] = findViewById(R.id.radio_three);
        mRadioButtons[1] = findViewById(R.id.radio_four);
        mRadioButtons[2] = findViewById(R.id.radio_five);
        mRadioButtons[3] = findViewById(R.id.radio_six);
        mRadioButtons[4] = findViewById(R.id.radio_seven);
        mRadioButtons[5] = findViewById(R.id.radio_eight);
        mRadioButtons[6] = findViewById(R.id.radio_nine);
        mRadioButtons[7] = findViewById(R.id.radio_ten);
        mRadioButtons[8] = findViewById(R.id.radio_eleven);
        mRadioButtons[9] = findViewById(R.id.radio_twelve);
    }
    private void setChangeTextOnRollDiceButton(boolean allowedToChangeState) {
        mChangeTextOnRollDiceButton = allowedToChangeState;
        if (mChangeTextOnRollDiceButton) {
            mRollDiceButton.setText(R.string.apply_button);
        } else {
            mRollDiceButton.setText(R.string.roll_dice);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_throw_dices);

        initRadioButons();


        saveResult();
        mClWhereToSave.setVisibility(View.INVISIBLE);


        Intent intent = getIntent();
        int dice0 = intent.getIntExtra("dice0", -1);
        int dice1 = intent.getIntExtra("dice1", -1);
        sValueOfDice[0] = dice0;
        sValueOfDice[1] = dice1;


        mapButtons();

        for (int i = 0; i < mDice.length; i++) {
            mDice[i].setBackgroundResource(displayDice(sValueOfDice[i], sLockDice[i]));
        }
        help();

        mRollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: countTurns = " + mCountTurns);
                updateThrowText();

                if (mRoundHasEnded) {
                    saveResultIn();
                } else {
                    if (mCountTurns > 0 && mCountTurns <3) {
                        startOfRound();
                    } else {
                        endOfRound();
                    }
                }

            }
        });
    }
    private void saveResultIn() {

    }

    private void updateDices(boolean playing) {
        if (playing) {
            for (int i = 0; i < mDice.length; i++) {
                mDice[i].setBackgroundResource(displayDice(sValueOfDice[i], sLockDice[i]));
            }
        } else {
            for (int i = 0; i < mDice.length; i++) {
                mDice[i].setBackgroundResource(displayDice(sValueOfDice[i], true));
            }
        }
    }

    private void startOfRound() {
        rollDice();
        mCountTurns++;
        mAllowedToChangeState = true;
        updateDices(true);
        mClWhereToSave.setVisibility(View.INVISIBLE);
    }
    private void rollDice() {
        for (int i = 0; i < 6; i++) {
            if (!sLockDice[i]) {
                sValueOfDice[i] = (int) ((Math.random() * 6) + 1);
            }
        }
    }

    private void endOfRound() {
        mCountTurns = 1;
        mAllowedToChangeState = false;
        mRoundHasEnded = true;
        setLockDiceToFalse();
        updateDices( false);
        updateThrowText();
//        runManualCalculations();
//                    saveResultsIn();
//                    setAnswerResult(true);
        returnResult();
        mClWhereToSave.setVisibility(View.VISIBLE);
    }
    private void setLockDiceToFalse() {
        for (int i = 0; i < sLockDice.length; i++) {
            sLockDice[i] = false;
        }
        mCountTurns++;
    }



    private void returnResult() {
        Intent data = new Intent();
        data.putExtra("mDataFromSource", 1);
        data.putExtra("resultDice0", sValueOfDice[0]);
        data.putExtra("resultDice1", sValueOfDice[1]);
        data.putExtra("resultDice2", sValueOfDice[2]);
        data.putExtra("resultDice3", sValueOfDice[3]);
        data.putExtra("resultDice4", sValueOfDice[4]);
        data.putExtra("resultDice5", sValueOfDice[5]);
        // Activity finished, return the data
        setResult(RESULT_OK, data);
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

    private void mapButtons() {
        mDice[0] = findViewById(R.id.dice1);
        mDice[0].setOnClickListener(new DiceButtonClick1());

        mDice[1] = findViewById(R.id.dice2);
        mDice[1].setOnClickListener(new DiceButtonClick2());

        mDice[2] = findViewById(R.id.dice3);
        mDice[2].setOnClickListener(new DiceButtonClick3());

        mDice[3] = findViewById(R.id.dice4);
        mDice[3].setOnClickListener(new DiceButtonClick4());

        mDice[4] = findViewById(R.id.dice5);
        mDice[4].setOnClickListener(new DiceButtonClick5());

        mDice[5] = findViewById(R.id.dice6);
        mDice[5].setOnClickListener(new DiceButtonClick6());


        mRollDiceButton = findViewById(R.id.btnRollDice);
    }

    private void help() {
        final TextView mHowToPlay = findViewById(R.id.textViewHowToPlay);
        mHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHowToPlay.setText(R.string.how_to_start_game);
            }
        });
    }



    private void updateThrowText() {
        TextView numberOfThrows = findViewById(R.id.noOfThrows);
        if (mRoundHasEnded) {
            numberOfThrows.setText(R.string.save_in);
        } else {
            if (mCountTurns == 1) {
                numberOfThrows.setText(R.string.throwOne);
            } else if (mCountTurns == 2) {
                numberOfThrows.setText(R.string.throwTwo);
            } else {
                numberOfThrows.setText(R.string.throwThree);
            }
        }
    }





    private void changeStateDice(int diceId) {
        Log.d(TAG, "changeStateDice: ");
        if (mAllowedToChangeState) {
            if (sLockDice[diceId]) {
                sLockDice[diceId] = false;
            } else {
                sLockDice[diceId] = true;
            }
        }
    }

    public int displayDice(int returnDiceNumber, boolean isLocked) {
       /* if (colorToReturn.equals("")) {
            return 0;
        }*/

        /*if (colorToReturn.equals("grey")) {
            switch (returnDiceNumber) {
                case 1:
                    return R.drawable.grey1;
                case 2:
                    return R.drawable.grey2;
                case 3:
                    return R.drawable.grey3;
                case 4:
                    return R.drawable.grey4;
                case 5:
                    return R.drawable.grey5;
                case 6:
                    return R.drawable.grey6;
                default:
                    return 0;
            }
        } else*/ if (isLocked) {
            switch (returnDiceNumber) {
                case 1:
                    return R.drawable.red1;
                case 2:
                    return R.drawable.red2;
                case 3:
                    return R.drawable.red3;
                case 4:
                    return R.drawable.red4;
                case 5:
                    return R.drawable.red5;
                case 6:
                    return R.drawable.red6;
                default:
                    return 0;
            }
        } else {
            switch (returnDiceNumber) {
                case 1:
                    return R.drawable.white1;
                case 2:
                    return R.drawable.white2;
                case 3:
                    return R.drawable.white3;
                case 4:
                    return R.drawable.white4;
                case 5:
                    return R.drawable.white5;
                case 6:
                    return R.drawable.white6;
                default:
                    return 0;
            }
        }



    }

    private void diceButtonClicked(int diceId) {
        changeStateDice(diceId);
        mDice[diceId].setBackgroundResource(displayDice(sValueOfDice[diceId], sLockDice[diceId]));
        Log.d(TAG, "onClick: dice" + diceId + " status = " + sLockDice[diceId]);
//        Toast.makeText(this, "Klicked", Toast.LENGTH_SHORT).show();
    }

    class DiceButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(0);
        }
    }
    class DiceButtonClick1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(0);
        }
    }
    class DiceButtonClick2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(1);
        }
    }
    class DiceButtonClick3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(2);
        }
    }
    class DiceButtonClick4 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(3);
        }
    }
    class DiceButtonClick5 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(4);
        }
    }
    class DiceButtonClick6 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            diceButtonClicked(5);
        }
    }

}














/*
public static Intent newIntent(Context packegeContext, int dice0, int dice1, int dice2, int dice3, int dice4, int dice5) {
        Log.d(TAG, "newIntent: called");
        Intent intent = new Intent(packegeContext, ThrowDices.class);
        intent.putExtra("dice0", dice0);
//        intent.putExtra(RETURN_DICE2_RESULT, dice1);
//        intent.putExtra(RETURN_DICE3_RESULT, dice2);
//        intent.putExtra(RETURN_DICE4_RESULT, dice3);
//        intent.putExtra(RETURN_DICE5_RESULT, dice4);
//        intent.putExtra(RETURN_DICE6_RESULT, dice5);
        return intent;
    }

    private void setAnswerResult(boolean roundHasEnded) {
        mRoundHasEnded = roundHasEnded;
        Intent data = new Intent();
        data.putExtra(ROUND_HAS_ENDED, roundHasEnded);
        data.putExtra("dice0", sValueOfDice[0]);
//        data.putExtra(RETURN_DICE2_RESULT, sValueOfDice[1]);
//        data.putExtra(RETURN_DICE3_RESULT, sValueOfDice[2]);
//        data.putExtra(RETURN_DICE4_RESULT, sValueOfDice[3]);
//        data.putExtra(RETURN_DICE5_RESULT, sValueOfDice[4]);
//        data.putExtra(RETURN_DICE6_RESULT, sValueOfDice[5]);
        setResult(RESULT_OK, data);
    }
 */





