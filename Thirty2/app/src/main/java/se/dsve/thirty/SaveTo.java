package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

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
import static android.graphics.Color.YELLOW;


public class SaveTo extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SaveTo";

    // Update Dices with received Intent
    private int[] dicesRecived = new int[6];
    private boolean[] isAllowedToChangeRecieved = new boolean[10];
    private void receivedIntent() {
        String[] diceName = new String[]{"dice1", "dice2", "dice3", "dice4", "dice5", "dice6"};
        String[] usedUpValuesInScoreBank = new String[]{"usedUpValue3", "usedUpValue4", "usedUpValue5", "usedUpValue6", "usedUpValue7", "usedUpValue8", "usedUpValue9", "usedUpValue10", "usedUpValue11", "usedUpValue12"};

        Intent receivedIntent = getIntent();
        for (int i = 0; i < diceName.length; i++) {
            dicesRecived[i] = receivedIntent.getIntExtra(diceName[i], -1);
        }
        for (int i = 0; i < usedUpValuesInScoreBank.length; i++) {
            isAllowedToChangeRecieved[i] = receivedIntent.getBooleanExtra(usedUpValuesInScoreBank[i], true);
        }
    }

    // Return Sum and where to save
    private int mReturnThisScore;
    private int mSaveScoreIn;
    private void returnThis() {
        Log.d(TAG, "returnThis: mReturnThisScore = " + mReturnThisScore +
                ", and mSaveScoreIn = " + mSaveScoreIn);

        String[] returnValue = new String[]{"coming_back", "total_score", "save_in"};
        Intent sendBackThis = new Intent();
        sendBackThis.putExtra(returnValue[0], "Successfully saved");
        sendBackThis.putExtra(returnValue[1], mReturnThisScore);
        sendBackThis.putExtra(returnValue[2], mSaveScoreIn);
        setResult(RESULT_OK, sendBackThis);
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
            Log.i(TAG, "updateDicesOnTheDisplay: dice" + i + " = " + dicesRecived[i] + " mUseDice = " + mUseDice[i]);
        }*/

        // Check every dice's boolean
        for (int j = 0; j < mUseDice.length; j++) {
            if (mUseDice[j]) {
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

    private boolean[] mUseDice = new boolean[6];
    private void setUseDicesToTrue() {
        for (int i = 0; i < mUseDice.length; i++) {
            mUseDice[i] = true;
        }
    }


    // TextView that display if its possible to save in certain container
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
    private void updateUsedValuesColors() {
        for (int i = 0; i < isAllowedToChangeRecieved.length; i++) {
            if (isAllowedToChangeRecieved[i]) {
                mTextViewtvUsedValueInSaveTo[i].setBackgroundColor(GREEN);
            } else {
                mTextViewtvUsedValueInSaveTo[i].setBackgroundColor(RED);
            }
        }
    }
    private int saveInContainer = -1;

    // TextView for displaying score
    private TextView mTextViewShowCalculatedScore;

    // Buttons
    private Button mButtonSave;
    private Button mButtonCalculateScore;

    CalculateScore calculateScore;

    // onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to);
        Log.d(TAG, "onCreate: ");

        // Receive intents first
        receivedIntent();

        // Return intent
        mReturnThisScore = 6;
        mSaveScoreIn = 3;
        returnThis();

        // Dices
        initiateImageViews();
        // Update stuff related to intents
        setUseDicesToTrue();
        updateDicesOnTheDisplay();
        // Make dices clickable
        for (int i = 0; i < mImageViewDiceResult.length; i++) {
            mImageViewDiceResult[i].setOnClickListener(this);
        }


        // Initiate calculate score TextView
        mTextViewShowCalculatedScore = findViewById(R.id.tvShowCalculatedScore);

        // Make clickable buttons
        mButtonSave = findViewById(R.id.btnSave);
        mButtonSave.setOnClickListener(this);
        mButtonCalculateScore = findViewById(R.id.btnCalculateScore);
        mButtonCalculateScore.setOnClickListener(this);

        // Stuff related to show used containers
        mapTextViewtvUsedValueInSaveTo();
        updateUsedValuesColors();
        for (int i = 0; i < mTextViewtvUsedValueInSaveTo.length; i++) {
            mTextViewtvUsedValueInSaveTo[i].setOnClickListener(this);
        }


        // Stuff related to calculate score
        calculateScore = new CalculateScore();



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
            case R.id.btnSave:
                clickOnSaveButton();
                break;
            case R.id.ivResultDice6:
                clickOnDice(5);
                break;
            case R.id.btnCalculateScore:
                clickOnCalculateScoreButton();
                break;
            case R.id.tvUsedValue3InSaveTo:
                clickOnWhereToSaveTextViews(0);
                break;
            case R.id.tvUsedValue4InSaveTo:
                clickOnWhereToSaveTextViews(1);
                break;
            case R.id.tvUsedValue5InSaveTo:
                clickOnWhereToSaveTextViews(2);
                break;
            case R.id.tvUsedValue6InSaveTo:
                clickOnWhereToSaveTextViews(3);
                break;
            case R.id.tvUsedValue7InSaveTo:
                clickOnWhereToSaveTextViews(4);
                break;
            case R.id.tvUsedValue8InSaveTo:
                clickOnWhereToSaveTextViews(5);
                break;
            case R.id.tvUsedValue9InSaveTo:
                clickOnWhereToSaveTextViews(6);
                break;
            case R.id.tvUsedValue10InSaveTo:
                clickOnWhereToSaveTextViews(7);
                break;
            case R.id.tvUsedValue11InSaveTo:
                clickOnWhereToSaveTextViews(8);
                break;
            case R.id.tvUsedValue12InSaveTo:
                clickOnWhereToSaveTextViews(9);
                break;
        }
    }

    private void clickOnWhereToSaveTextViews(int id) {

        if (isAllowedToChangeRecieved[id]) {
            saveInContainer = id;
            updateUsedValuesColors();
            mTextViewtvUsedValueInSaveTo[id].setBackgroundColor(YELLOW);
        } else {
            Toast.makeText(this, "Already used!", Toast.LENGTH_SHORT).show();
        }



    }

    private void clickOnDice(int mId) {
        Log.d(TAG, "clickOnDice:" + mId);
        if (mUseDice[mId]) {
            mUseDice[mId] = false;
            Toast.makeText(this, "Don't use dice" + (mId +1), Toast.LENGTH_SHORT).show();
        } else {
            mUseDice[mId] = true;
            Toast.makeText(this, "Use dice" + (mId +1), Toast.LENGTH_SHORT).show();
        }
        updateDicesOnTheDisplay();
    }

    private void clickOnCalculateScoreButton() {
        Log.d(TAG, "clickOnCalculateScoreButton: ");

        if (saveInContainer == -1) {
            Toast.makeText(this, "Choose where to save first", Toast.LENGTH_SHORT).show();
        } else {
            int sum = calculateScore.calculateSum(dicesRecived, (saveInContainer), mUseDice);
            mReturnThisScore = sum;
            mSaveScoreIn = saveInContainer;

            String mOutputToTextView;
            if (sum == -1) {
                mOutputToTextView = "Not a valid score, remove dices that don't sum upp";
            } else {
                mOutputToTextView = "Score is: " + sum;
            }

            mTextViewShowCalculatedScore.setText(mOutputToTextView);
            Log.d(TAG, "clickOnCalculateScoreButton: Sum is = " + sum);
        }
    }

    private void clickOnSaveButton() {
        Log.d(TAG, "clickOnSaveButton: ");
        returnThis();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        returnThis();
    }

}
