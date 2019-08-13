package se.dsve.thirty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class ThrowDices extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ThrowDices";
    private static final int REQUEST_CODE_RESULT = 1;
    private static final int REQUEST_CODE_SAVE_IN = 2;

    // ImageView & TextViews clickable
    private ImageView[] mImageViewDices = new ImageView[6];
    private TextView mTextViewShowUsedValues;
    private void initiateImageViews() {
        mImageViewDices[0] = findViewById(R.id.ivDice1);
        mImageViewDices[1] = findViewById(R.id.ivDice2);
        mImageViewDices[2] = findViewById(R.id.ivDice3);
        mImageViewDices[3] = findViewById(R.id.ivDice4);
        mImageViewDices[4] = findViewById(R.id.ivDice5);
        mImageViewDices[5] = findViewById(R.id.ivDice6);

        mTextViewShowUsedValues = findViewById(R.id.tvShowUsedValues);
    }

    // Non-Clickable TextViews
    private TextView[] mTextViewUsedValues = new TextView[10];
    private void initiateAdressableTeaxtView() {
        mTextViewUsedValues[0] = findViewById(R.id.tvUsedValue3);
        mTextViewUsedValues[1] = findViewById(R.id.tvUsedValue4);
        mTextViewUsedValues[2] = findViewById(R.id.tvUsedValue5);
        mTextViewUsedValues[3] = findViewById(R.id.tvUsedValue6);
        mTextViewUsedValues[4] = findViewById(R.id.tvUsedValue7);
        mTextViewUsedValues[5] = findViewById(R.id.tvUsedValue8);
        mTextViewUsedValues[6] = findViewById(R.id.tvUsedValue9);
        mTextViewUsedValues[7] = findViewById(R.id.tvUsedValue10);
        mTextViewUsedValues[8] = findViewById(R.id.tvUsedValue11);
        mTextViewUsedValues[9] = findViewById(R.id.tvUsedValue12);
    }

    // Buttons
    private Button mShowResultButton;
    private Button mRollDicesButton;
    private Button mSaveToButton;
    private void initiateButtons() {
        mShowResultButton = findViewById(R.id.btnShowResult);
        mRollDicesButton = findViewById(R.id.btnRollDices);
        mSaveToButton = findViewById(R.id.btnSaveResult);
    }

    // Game play related
    private GamePlay mGamePlay;
    private Score[] mScoreBank = new Score[]{
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
    private boolean mShowUsedValues = true;

    private boolean mGameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw_dices);

        initiateImageViews();
        initiateAdressableTeaxtView();
        initiateButtons();
        clickOnShowOrHideUsedValues();

        for (ImageView imageViewDice : mImageViewDices) {
            imageViewDice.setOnClickListener(this);
        }
        mTextViewShowUsedValues.setOnClickListener(this);

        mShowResultButton.setOnClickListener(this);
        mRollDicesButton.setOnClickListener(this);
        mSaveToButton.setOnClickListener(this);

        mGamePlay = new GamePlay();

        updateUsedValuesColors();
    }

    // *****************************
    // **     Clickable stuff     **
    // *****************************
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDice1:
                clickOnDice(0);
                break;
            case R.id.ivDice2:
                clickOnDice(1);
                break;
            case R.id.ivDice3:
                clickOnDice(2);
                break;
            case R.id.ivDice4:
                clickOnDice(3);
                break;
            case R.id.ivDice5:
                clickOnDice(4);
                break;
            case R.id.ivDice6:
                clickOnDice(5);
                break;
            case R.id.btnShowResult:
                clickOnShowResult();
                break;
            case R.id.btnRollDices:
                clickOnRollDices();
                break;
            case R.id.btnSaveResult:
                clickOnSaveTo();
                break;
            case R.id.tvShowUsedValues:
                clickOnShowOrHideUsedValues();
                break;
        }
    }

    private void clickOnDice(int id) {
//        Log.d(TAG, "clickOnDice: clicked on dice" + (id +1));
        boolean isAllowed =mGamePlay.isAllowedToChangeStateOnDice();
//        Log.d(TAG, "clickOnDice: Allowed to change state = " + isAllowed);

        if (isAllowed) {
            Toast.makeText(this, "Allowed", Toast.LENGTH_SHORT).show();
            mGamePlay.changeStateDice(id);
        } else {
            Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
        }
        updateDicesOnTheDisplay();

    }



    // Activity related to show result
    private void clickOnShowResult() {
        Toast.makeText(this, "hi result", Toast.LENGTH_SHORT).show();

        String[] scoreBank = new String[]{"r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10"};
        Intent showResult = new Intent(ThrowDices.this, ShowResult.class);
        for (int i = 0; i < scoreBank.length; i++) {
            showResult.putExtra(scoreBank[i], mScoreBank[i].getScore());
        }
        startActivityForResult(showResult, REQUEST_CODE_RESULT);
    }


    // Activity related to roll dices
    private void clickOnRollDices() {
        mGamePlay.playGame();
        updateThrowText();
        updateDicesOnTheDisplay();

        if (mGamePlay.hasRoundEnded()) {
            switchButtons(true);
        }
    }

    private void updateThrowText() {
        TextView textViewThrowText = findViewById(R.id.tvThrowText);

        if (mGamePlay.getCountTurns() == 0) {
            textViewThrowText.setText(R.string.throwZero);
        } else if (mGamePlay.getCountTurns() == 1) {
            textViewThrowText.setText(R.string.throwOne);
        } else if (mGamePlay.getCountTurns() == 2) {
            textViewThrowText.setText(R.string.throwTwo);
        } else {
            textViewThrowText.setText(R.string.throwThree);
        }

    }

    private void updateDicesOnTheDisplay() {
        int[] dices = mGamePlay.getValueOfDices();
        boolean[] isLocked = mGamePlay.getLockDice();
//        for (int i = 0; i < dices.length; i++) {
//            Log.i(TAG, "updateDicesOnTheDisplay: dice" + i + " = " + dices[i] + ", is locked " + isLocked[i]);
//        }

        for (int i = 0; i < dices.length; i++) {
            if (isLocked[i]) {
                if (dices[i] == 1) {
                    mImageViewDices[i].setImageResource(R.drawable.red1);
                } else if (dices[i] == 2) {
                    mImageViewDices[i].setImageResource(R.drawable.red2);
                } else if (dices[i] == 3) {
                    mImageViewDices[i].setImageResource(R.drawable.red3);
                } else if (dices[i] == 4) {
                    mImageViewDices[i].setImageResource(R.drawable.red4);
                } else if (dices[i] == 5) {
                    mImageViewDices[i].setImageResource(R.drawable.red5);
                } else if (dices[i] == 6) {
                    mImageViewDices[i].setImageResource(R.drawable.red6);
                } /*else {
                    Toast.makeText(this, "no red dice", Toast.LENGTH_SHORT).show();
                }*/
            } else {
                if (dices[i] == 1) {
                    mImageViewDices[i].setImageResource(R.drawable.white1);
                } else if (dices[i] == 2) {
                    mImageViewDices[i].setImageResource(R.drawable.white2);
                } else if (dices[i] == 3) {
                    mImageViewDices[i].setImageResource(R.drawable.white3);
                } else if (dices[i] == 4) {
                    mImageViewDices[i].setImageResource(R.drawable.white4);
                } else if (dices[i] == 5) {
                    mImageViewDices[i].setImageResource(R.drawable.white5);
                } else if (dices[i] == 6) {
                    mImageViewDices[i].setImageResource(R.drawable.white6);
                } /*else {
                    Toast.makeText(this, "no red dice", Toast.LENGTH_SHORT).show();
                }*/
            }
        }
    }

    private void switchButtons(boolean saveMode) {
        if (saveMode) {
            mRollDicesButton.setVisibility(View.INVISIBLE);
            mSaveToButton.setVisibility(View.VISIBLE);
        } else {
            mRollDicesButton.setVisibility(View.VISIBLE);
            mSaveToButton.setVisibility(View.INVISIBLE);
        }
    }


    // Activity related to hide and show used values
    private void clickOnShowOrHideUsedValues() {
        ConstraintLayout constraintLayoutShowUsedValues = findViewById(R.id.clShowUsedValues);
        if (mShowUsedValues) {
            mShowUsedValues = false;
            constraintLayoutShowUsedValues.setVisibility(View.INVISIBLE);
        } else {
            mShowUsedValues = true;
            constraintLayoutShowUsedValues.setVisibility(View.VISIBLE);
        }
    }

    private void updateUsedValuesColors() {
        for (int i = 0; i < mScoreBank.length; i++) {
            if (mScoreBank[i].isAllowedToChange()) {
                mTextViewUsedValues[i].setBackgroundColor(GREEN);
            } else {
                mTextViewUsedValues[i].setBackgroundColor(RED);
            }
        }
    }


    // Activity related to save current result
    private void clickOnSaveTo() {
        int[] dices = mGamePlay.getValueOfDices();
        String[] diceName = new String[]{ "dice1", "dice2", "dice3", "dice4", "dice5", "dice6"};
        String[] usedUpValuesInScoreBank = new String[]{"usedUpValue3", "usedUpValue4", "usedUpValue5", "usedUpValue6", "usedUpValue7", "usedUpValue8", "usedUpValue9", "usedUpValue10", "usedUpValue11", "usedUpValue12"};

        // TODO: mScoreBank[0].isAllowedToChange();

        Intent saveThisIn = new Intent(ThrowDices.this, SaveTo.class);
        for (int i = 0; i < diceName.length; i++) {
            saveThisIn.putExtra(diceName[i], dices[i]);
        }
        for (int i = 0; i < usedUpValuesInScoreBank.length; i++) {
            saveThisIn.putExtra(usedUpValuesInScoreBank[i], mScoreBank[i].isAllowedToChange());
        }
        startActivityForResult(saveThisIn, REQUEST_CODE_SAVE_IN);
    }


    // *******************************
    // **     Receiving Intents     **
    // *******************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            if (REQUEST_CODE_RESULT == requestCode) {
                Log.d(TAG, "onActivityResult: REQUEST_CODE_RESULT = 1");
                try {
                    String output = data.getStringExtra("coming_back");
                    Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) {
                    Log.e(TAG, "onActivityResult: ", e);
                }
            } else if (REQUEST_CODE_SAVE_IN == requestCode) {
                Log.d(TAG, "onActivityResult: REQUEST_CODE_SAVE_IN = 2");
                try {
                    String[] returnValue = new String[]{"coming_back", "total_score", "save_in"};
                    String one = data.getStringExtra(returnValue[0]);
                    Toast.makeText(this, one, Toast.LENGTH_SHORT).show();

                    int totalScore = data.getIntExtra(returnValue[1], -1);
                    int saveInId = data.getIntExtra(returnValue[2], -1);

                    mScoreBank[saveInId].updateScore(saveInId, totalScore);

                } catch (NullPointerException e) {
                    Log.e(TAG, "onActivityResult: ", e);
                }


                if (checkIfGameOver()) {
                    switchButtons(true);
                    Log.d(TAG, "onActivityResult: GAME OVER!");
                } else {
                    switchButtons(false);
                }


                mGamePlay.resetRound();
                updateDicesOnTheDisplay();
                updateThrowText();
                updateUsedValuesColors();
            }
        }


        // TODO: Resultcode för att reseta spelet
        // TODO: Skapa highscore på förstasidan?
    }

    private boolean checkIfGameOver() {
        for (int i = 0; i < mScoreBank.length; i++) {
            if (mScoreBank[i].isAllowedToChange()) {
                return false;
            }
        }
        return true;
    }
}
