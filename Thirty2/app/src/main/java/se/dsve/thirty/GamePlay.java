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
class GamePlay {
    private static final String TAG = "GamePlay";

    private static int[] sValueOfDice = new int[]{6, 6, 6, 6, 6, 6};
    private static boolean[] sLockDice = new boolean[]{false, false, false, false, false, false};

    private int mCountTurns = 0;
    private boolean mAllowedToChangeStateOnDice = false;
    private static boolean mRoundHasEnded = false;

    GamePlay() {
    }

    void playGame() {
        mCountTurns++;

        if (mCountTurns > 0 && mCountTurns < 4) {
            rollDice();

            mAllowedToChangeStateOnDice = mCountTurns > 0 && mCountTurns < 3;
            mRoundHasEnded = mCountTurns >= 0 && mCountTurns >= 3;
        } /*else {
            //saveToHighscore();
            endOfRound();
        }*/



        Log.d(TAG, "playGame: mCountTurns = " + mCountTurns);
        Log.d(TAG, "playGame: mAllowedToChangeStateOnDice = " + mAllowedToChangeStateOnDice);
        Log.d(TAG, "playGame: mRoundHasEnded = " + mRoundHasEnded);
    }
    private void rollDice() {
        for (int i = 0; i < 6; i++) {
            if (!sLockDice[i]) {
                sValueOfDice[i] = (int) ((Math.random() * 6) + 1);
            }
        }
    }

    void resetRound() {
        mCountTurns = 0;
        setLockDiceToFalse();
        for (int i = 0; i < sValueOfDice.length; i++) {
            sValueOfDice[i] = 1;
        }
    }
    private void setLockDiceToFalse() {
        for (int i = 0; i < sLockDice.length; i++) {
            sLockDice[i] = false;
        }
    }






    // *********************
    // **     Getters     **
    // *********************
    int getCountTurns() {
        return mCountTurns;
    }

    int[] getValueOfDices() {
        return sValueOfDice;
    }

    boolean[] getLockDice() {
        return sLockDice;
    }

    boolean hasRoundEnded() {
        Log.i(TAG, "hasRoundEnded: " + mRoundHasEnded);
        return mRoundHasEnded;
    }

    boolean isAllowedToChangeStateOnDice() {
        return mAllowedToChangeStateOnDice;
    }


    // *********************
    // **     Setters     **
    // *********************
    void changeStateDice(int diceId) {
        Log.d(TAG, "changeStateDice: ");
        if (mAllowedToChangeStateOnDice) {
            if (sLockDice[diceId]) {
                sLockDice[diceId] = false;
            } else {
                sLockDice[diceId] = true;
            }
        }
    }
}
