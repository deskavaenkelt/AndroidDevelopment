package se.dsve.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SaveResult extends AppCompatActivity {
    private static final String TAG = "SaveResult";

    private static int VALUE_IN_3 = 0;
    private static int VALUE_IN_4 = 0;
    private static int VALUE_IN_5 = 0;
    private static int VALUE_IN_6 = 0;
    private static int VALUE_IN_7 = 0;
    private static int VALUE_IN_8 = 0;
    private static int VALUE_IN_9 = 0;
    private static int VALUE_IN_10 = 0;
    private static int VALUE_IN_11 = 0;
    private static int VALUE_IN_12 = 0;

    private int mDice[] = new int[6];

    public void storeScoreIn(int valueToStoreIn, int dice1, int dice2, int dice3, int dice4, int dice5, int dice6) {
        mDice[0] = dice1;
        mDice[1] = dice2;
        mDice[2] = dice3;
        mDice[3] = dice4;
        mDice[4] = dice5;
        mDice[5] = dice6;

        switch (valueToStoreIn) {
            case 3:
                store3();
                break;
            case 4:
                store4();
                break;
            case 5:
                store5();
                break;
            case 6:
                store6();
                break;
            case 7:
                store7();
                break;
            case 8:
                store8();
                break;
            case 9:
                store9();
                break;
            case 10:
                store10();
                break;
            case 11:
                store11();
                break;
            case 12:
                store12();
                break;

        }

    }

    private void store3() {
        int sum = 0;
        for (int i = 0; i < mDice.length; i++) {
            if (mDice[i] < 4) {
                sum += mDice[i];
            }
        }
        VALUE_IN_3 = sum;
    }
    private void store4() {
        int sum = 0;
        for (int i = 0; i < mDice.length; i++) {
            if (mDice[i] < 5) {
                sum += mDice[i];
            }
        }
        VALUE_IN_3 = sum;
    }
    private void store5() {

    }
    private void store6() {

    }
    private void store7() {

    }
    private void store8() {

    }
    private void store9() {

    }
    private void store10() {

    }
    private void store11() {

    }
    private void store12() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_result);
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
