package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SaveTo extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SaveTo";

    // Update Dices with received Intent
    private int[] dicesRecived = new int[6];
    private void receivedIntent() {
        String[] diceName = new String[]{"dice1", "dice2", "dice3", "dice4", "dice5", "dice6"};

        Intent receivedIntent = getIntent();
        for (int i = 0; i < diceName.length; i++) {
            dicesRecived[i] = receivedIntent.getIntExtra(diceName[i], -1);
        }
    }

    // Related to ImageView Dices
    private ImageView[] mImageViewDiceResult = new ImageView[6];
    private void initiateImageViews() {
        mImageViewDiceResult[0] = findViewById(R.id.ivResultDice1);
        mImageViewDiceResult[1] = findViewById(R.id.ivResultDice2);
        mImageViewDiceResult[2] = findViewById(R.id.ivResultDice3);
        mImageViewDiceResult[3] = findViewById(R.id.ivResultDice4);
        mImageViewDiceResult[4] = findViewById(R.id.ivResultDice5);
        mImageViewDiceResult[5] = findViewById(R.id.ivResultDice6);
    }
    private void updateDicesOnTheDisplay() {
        /*for (int i = 0; i < dicesRecived.length; i++) {
            Log.i(TAG, "updateDicesOnTheDisplay: dice" + i + " = " + dicesRecived[i] + " useDice = " + useDice[i]);
        }*/

        // Check every dice's boolean
        for (int j = 0; j < useDice.length; j++) {
            if (useDice[j]) {
                ifWhiteDice(j);
            } else {
                ifRedDice(j);
            }
        }
    }
    private void ifWhiteDice(int i) {
        if (dicesRecived[i] == 1) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey1);
        } else if (dicesRecived[i] == 2) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey2);
        } else if (dicesRecived[i] == 3) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey3);
        } else if (dicesRecived[i] == 4) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey4);
        } else if (dicesRecived[i] == 5) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey5);
        } else if (dicesRecived[i] == 6) {
            mImageViewDiceResult[i].setImageResource(R.drawable.grey6);
        } else {
            Toast.makeText(this, "no grey dice", Toast.LENGTH_SHORT).show();
        }
    }
    private void ifRedDice(int i) {
        if (dicesRecived[i] == 1) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red1);
        } else if (dicesRecived[i] == 2) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red2);
        } else if (dicesRecived[i] == 3) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red3);
        } else if (dicesRecived[i] == 4) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red4);
        } else if (dicesRecived[i] == 5) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red5);
        } else if (dicesRecived[i] == 6) {
            mImageViewDiceResult[i].setImageResource(R.drawable.red6);
        } else {
            Toast.makeText(this, "no red dice", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean[] useDice = new boolean[6];
    private void setUseDicesToTrue() {
        for (int i = 0; i < useDice.length; i++) {
            useDice[i] = true;
        }
    }

    // Return Sum and where to save
    private int mReturnThisScore;
    private int mSaveScoreIn;
    private void returnThis() {
        String[] returnValue = new String[]{"coming_back", "total_score", "save_in"};
        Intent sendBackThis = new Intent();
        sendBackThis.putExtra(returnValue[0], "Successfully saved");
        sendBackThis.putExtra(returnValue[1], mReturnThisScore);
        sendBackThis.putExtra(returnValue[2], mSaveScoreIn);
        setResult(RESULT_OK, sendBackThis);
    }

    // Related to Radio Groups, Radio Buttons
    private RadioGroup[] mRadioGroupsColon = new RadioGroup[6];
    private void mapRadioGroups() {
        mRadioGroupsColon[0] = findViewById(R.id.rbgDice1);
        mRadioGroupsColon[1] = findViewById(R.id.rbgDice2);
        mRadioGroupsColon[2] = findViewById(R.id.rbgDice3);
        mRadioGroupsColon[3] = findViewById(R.id.rbgDice4);
        mRadioGroupsColon[4] = findViewById(R.id.rbgDice5);
        mRadioGroupsColon[5] = findViewById(R.id.rbgDice6);
    }
    private RadioButton[] mRadioButtonsRow1 = new RadioButton[6];
    private void mapRadioButtonsRow1() {
        mRadioButtonsRow1[0] = findViewById(R.id.rbc1r1);
        mRadioButtonsRow1[1] = findViewById(R.id.rbc2r1);
        mRadioButtonsRow1[2] = findViewById(R.id.rbc3r1);
        mRadioButtonsRow1[3] = findViewById(R.id.rbc4r1);
        mRadioButtonsRow1[4] = findViewById(R.id.rbc5r1);
        mRadioButtonsRow1[5] = findViewById(R.id.rbc6r1);
    }
    private RadioButton[] mRadioButtonsRow2 = new RadioButton[6];
    private void mapRadioButtonsRow2() {
        mRadioButtonsRow2[0] = findViewById(R.id.rbc1r2);
        mRadioButtonsRow2[1] = findViewById(R.id.rbc2r2);
        mRadioButtonsRow2[2] = findViewById(R.id.rbc3r2);
        mRadioButtonsRow2[3] = findViewById(R.id.rbc4r2);
        mRadioButtonsRow2[4] = findViewById(R.id.rbc5r2);
        mRadioButtonsRow2[5] = findViewById(R.id.rbc6r2);
    }
    private RadioButton[] mRadioButtonsRow3 = new RadioButton[6];
    private void mapRadioButtonsRow3() {
        mRadioButtonsRow3[0] = findViewById(R.id.rbc1r3);
        mRadioButtonsRow3[1] = findViewById(R.id.rbc2r3);
        mRadioButtonsRow3[2] = findViewById(R.id.rbc3r3);
        mRadioButtonsRow3[3] = findViewById(R.id.rbc4r3);
        mRadioButtonsRow3[4] = findViewById(R.id.rbc5r3);
        mRadioButtonsRow3[5] = findViewById(R.id.rbc6r3);
    }
    private RadioButton[] mRadioButtonsRow4 = new RadioButton[6];
    private void mapRadioButtonsRow4() {
        mRadioButtonsRow4[0] = findViewById(R.id.rbc1r4);
        mRadioButtonsRow4[1] = findViewById(R.id.rbc2r4);
        mRadioButtonsRow4[2] = findViewById(R.id.rbc3r4);
        mRadioButtonsRow4[3] = findViewById(R.id.rbc4r4);
        mRadioButtonsRow4[4] = findViewById(R.id.rbc5r4);
        mRadioButtonsRow4[5] = findViewById(R.id.rbc6r4);
    }
    private RadioButton[] mRadioButtonsRow5 = new RadioButton[6];
    private void mapRadioButtonsRow5() {
        mRadioButtonsRow5[0] = findViewById(R.id.rbc1r5);
        mRadioButtonsRow5[1] = findViewById(R.id.rbc2r5);
        mRadioButtonsRow5[2] = findViewById(R.id.rbc3r5);
        mRadioButtonsRow5[3] = findViewById(R.id.rbc4r5);
        mRadioButtonsRow5[4] = findViewById(R.id.rbc5r5);
        mRadioButtonsRow5[5] = findViewById(R.id.rbc6r5);
    }
    private RadioButton[] mRadioButtonsRow6 = new RadioButton[6];
    private void mapRadioButtonsRow6() {
        mRadioButtonsRow6[0] = findViewById(R.id.rbc1r6);
        mRadioButtonsRow6[1] = findViewById(R.id.rbc2r6);
        mRadioButtonsRow6[2] = findViewById(R.id.rbc3r6);
        mRadioButtonsRow6[3] = findViewById(R.id.rbc4r6);
        mRadioButtonsRow6[4] = findViewById(R.id.rbc5r6);
        mRadioButtonsRow6[5] = findViewById(R.id.rbc6r6);
    }

    private void initializeRadioRelated() {
        mapRadioGroups();
        mapRadioButtonsRow1();
        mapRadioButtonsRow2();
        mapRadioButtonsRow3();
        mapRadioButtonsRow4();
        mapRadioButtonsRow5();
        mapRadioButtonsRow6();

        mapTextViewTotalScore();
    }

    // TextView sum of every row + total score
    private TextView[] mTextViewSum = new TextView[6];
    private TextView mTextViewTotalScore;
    private void mapTextViewTotalScore() {
        mTextViewSum[0] = findViewById(R.id.tvSumRow1);
        mTextViewSum[1] = findViewById(R.id.tvSumRow2);
        mTextViewSum[2] = findViewById(R.id.tvSumRow3);
        mTextViewSum[3] = findViewById(R.id.tvSumRow4);
        mTextViewSum[4] = findViewById(R.id.tvSumRow5);
        mTextViewSum[5] = findViewById(R.id.tvSumRow6);

        mTextViewTotalScore = findViewById(R.id.twScore);
    }

    // TextView that display if its possible to sav in certain container
    private TextView[] mTextViewtvUsedValueInSaveTo = new TextView[10];
    private void mapTextViewtvUsedValueInSaveTo() {
        mTextViewtvUsedValueInSaveTo[0] = findViewById(R.id.tvUsedValue3InSaveTo);
        mTextViewtvUsedValueInSaveTo[1] = findViewById(R.id.tvUsedValue4InSaveTo);
        mTextViewtvUsedValueInSaveTo[2] = findViewById(R.id.tvUsedValue5InSaveTo);
        mTextViewtvUsedValueInSaveTo[3] = findViewById(R.id.tvUsedValue6InSaveTo);
        mTextViewtvUsedValueInSaveTo[4] = findViewById(R.id.tvUsedValue7InSaveTo);
        mTextViewtvUsedValueInSaveTo[5] = findViewById(R.id.tvUsedValue8InSaveTo);
        mTextViewtvUsedValueInSaveTo[6] = findViewById(R.id.tvUsedValue9InSaveTo);
        mTextViewtvUsedValueInSaveTo[7] = findViewById(R.id.tvUsedValue10InSaveTo);
        mTextViewtvUsedValueInSaveTo[8] = findViewById(R.id.tvUsedValue11InSaveTo);
        mTextViewtvUsedValueInSaveTo[9] = findViewById(R.id.tvUsedValue12InSaveTo);
    }

    // onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to);
        Log.d(TAG, "onCreate: ");

        // Receive intents first
        receivedIntent();
        initiateImageViews();

        // Update stuff related to intents
        setUseDicesToTrue();
        updateDicesOnTheDisplay();

        // Make dices clickable
        for (int i = 0; i < mImageViewDiceResult.length; i++) {
            mImageViewDiceResult[i].setOnClickListener(this);
        }

        // Stuff related to Radio Buttons
        initializeRadioRelated();

        // Stuff related to show used containers
        mapTextViewtvUsedValueInSaveTo();
        // TODO: send the array with intent

        // Return intent
        mReturnThisScore = 6;
        mSaveScoreIn = 3;
        returnThis();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivResultDice1:
                clickOnDice(0);
                break;
            case R.id.ivResultDice2:
                clickOnDice(1);
                break;
            case R.id.ivResultDice3:
                clickOnDice(2);
                break;
            case R.id.ivResultDice4:
                clickOnDice(3);
                break;
            case R.id.ivResultDice5:
                clickOnDice(4);
                break;
            case R.id.ivResultDice6:
                clickOnDice(5);
                break;
        }
    }

    private void clickOnDice(int mId) {
        Log.d(TAG, "clickOnDice:" + mId);
        if (useDice[mId]) {
            useDice[mId] = false;
            Toast.makeText(this, "Don't use dice" + (mId +1), Toast.LENGTH_SHORT).show();
        } else {
            useDice[mId] = true;
            Toast.makeText(this, "Use dice" + (mId +1), Toast.LENGTH_SHORT).show();
        }
        updateDicesOnTheDisplay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        returnThis();
    }
}
