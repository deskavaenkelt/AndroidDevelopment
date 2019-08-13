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

import android.util.Log;

/**
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-08-10
 * https://github.com/deskavaenkelt/
 */
class CalculateScore {
    private static final String TAG = "CalculateScore";

    private int[] mReceivedDices = new int[6];
    private int[] mDiceWithValue = new int[]{0, 0, 0, 0, 0, 0};
    private boolean[] mBooleans = new boolean[]{false, false, false, false, false, false};
    private boolean[] mCountOnlyChosenDices = new boolean[]{false, false, false, false, false, false};

    private int mSum;
    private int mValidateAgainst;

    private void calculateLowSum() {
        Log.d(TAG, "calculateLowSum: ");
        int sum = 0;
        for (int i = 0; i < mReceivedDices.length; i++) {
            Log.d(TAG, "calculateLowSum: mReceivedDices[" + i + "] = " + mReceivedDices[i]);
            if (mReceivedDices[i] < 4) {
                sum += mReceivedDices[i];
                Log.d(TAG, "calculateLowSum: added " + mReceivedDices[i] + ", new mSum = " + sum);
            }
        }
        mSum = sum;
    }

    private void calculateTotalSum() {
        Log.d(TAG, "calculateTotalSum: ");
        int sum = 0;

        for (int i = 0; i < mReceivedDices.length; i++) {
            Log.d(TAG, "calculateTotalSum: mReceivedDices[" + i + "] = " + mReceivedDices[i]);
            if (mCountOnlyChosenDices[i]) {
                sum += mReceivedDices[i];
                Log.d(TAG, "calculateTotalSum: added " + mReceivedDices[i] + ", new mSum = " + sum);
            }
        }

        Log.d(TAG, "calculateTotalSum: Sum to validate = " + sum);
        boolean isSumValid = validateSum(sum);
        Log.d(TAG, "calculateTotalSum: isSumValid = " + isSumValid);

        if (isSumValid) {
            Log.d(TAG, "calculateTotalSum: mSum = sum");
            mSum = sum;
        } else {
            Log.d(TAG, "calculateTotalSum: invalid sum");
        }
    }

    private boolean validateSum(int sum) {
        Log.d(TAG, "validateSum: sum = " + sum);
        switch (mValidateAgainst) {
            case 0:
                return true;
            case 4:
                return isValid(sum);
            case 5:
                return isValid(sum);
            case 6:
                return isValid(sum);
            case 7:
                return isValid(sum);
            case 8:
                return isValid(sum);
            case 9:
                return isValid(sum);
            case 10:
                return isValid(sum);
            case 11:
                return isValid(sum);
            case 12:
                return isValid(sum);
        }

        return false;
    }

    private boolean isValid(int sum) {
        Log.d(TAG, "isValid: sum = " + sum);
        for (int i = 1; i < 6; i++) {
            if (sum == (i * mValidateAgainst)) {
                Log.d(TAG, "isValid: sum = " + sum + ", mValidateAgainst = " + (i * mValidateAgainst));
                Log.d(TAG, "isValid: return true");
                return true;
            } else if (sum == 0) {
                Log.d(TAG, "isValid is 0: sum = " + sum);
                return true;
            } else {
                Log.d(TAG, "isValid is FALSE: sum = " + sum + ", mValidateAgainst = " + (i * mValidateAgainst));
            }
        }
        Log.d(TAG, "isValid: return false");
        return false;
    }

    private void whatDicesDoIHave() {
        Log.d(TAG, "whatDicesDoIHave: ");
        for (int receivedice : mReceivedDices) {
            switch (receivedice) {
                case 1:
                    mDiceWithValue[0]++;
                    mBooleans[0] = true;
                    break;
                case 2:
                    mDiceWithValue[1]++;
                    mBooleans[1] = true;
                    break;
                case 3:
                    mDiceWithValue[2]++;
                    mBooleans[2] = true;
                    break;
                case 4:
                    mDiceWithValue[3]++;
                    mBooleans[3] = true;
                    break;
                case 5:
                    mDiceWithValue[4]++;
                    mBooleans[4] = true;
                    break;
                case 6:
                    mDiceWithValue[5]++;
                    mBooleans[5] = true;
                    break;
            }
        }

    }

    int calculateSum(int[] dices, int calculateScoreForSum, boolean[] countIfTrue) {
        // Put dices in global array
        mReceivedDices = dices;
        // Validate against numbers not indexes => +3 on mValidateAgainst
        mValidateAgainst = (calculateScoreForSum + 3);
        // Put useful dices in global array
        mCountOnlyChosenDices = countIfTrue;
        // Init mSum to -1 every time function is called
        mSum = -1;

        // Debug
        Log.d(TAG, "calculateSum: set mSum = " + mSum);
        Log.d(TAG, "calculateSum: mValidateAgainst = " + mValidateAgainst);
        for (int i = 0; i < dices.length; i++) {
            //mReceivedDices[i] = dices[i];
            Log.d(TAG, "calculateSum: dice" + i + " = " + mReceivedDices[i] +
                    ", mCountOnlyChosenDices = " + mCountOnlyChosenDices[i]);
        }

        if (mValidateAgainst == 3) {
            Log.d(TAG, "calculateSum: low");
            calculateLowSum();
        } else {
            Log.d(TAG, "calculateSum: all other");
            calculateTotalSum();
        }

        if (mSum == -1) {
            Log.d(TAG, "calculateSum: return mSum = " + mSum);
            return -1;
        } else {
            Log.d(TAG, "calculateSum: return mSum = " + mSum);
            return mSum;
        }



        // Debug


//        whatDicesDoIHave();
        /*for (int i = 0; i < mBooleans.length; i++) {
            if (mBooleans[i]) {
                Log.d(TAG, "whatDicesDoIHave: mDiceWithValue" + (i+1) + " = " + mDiceWithValue[i]);
            }
        }*/
    }
}
