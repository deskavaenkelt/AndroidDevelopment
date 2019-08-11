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
 * @since 2019-08-04
 * https://github.com/deskavaenkelt/
 */
class CalculateScore {
    private static final String TAG = "CalculateScore";


    /*
     * What to do:
     * Receive 6 numbers
     * Return 1 total score
     * Debug logcat numbers added together
     * Calculate maximum score by adding all number together optional
     * */

//    private int score;
//    private int[] intDiceArray;

    public int sumAllNumbers(int[] diceArray) {
        Log.d(TAG, "sumAllNumbers: Entered method");

        int sum = 0;

        for (int i1 : diceArray) {
            Log.d(TAG, "sumAllNumbers: " + i1);
            sum += i1;
        }

        Log.d(TAG, "sumAllNumbers: return the sum: " + sum);
        Log.d(TAG, "sumAllNumbers: exit method");
        return sum;
    }

    /*public boolean verifyValidScore(int saveIn, int score) {
//        if (saveIn < 3 || saveIn > 12) {
//            return false;
//        }
//        if (score < 6 || score > 36) {
//            return false;
//        }

        switch (score) {
            case 3:
                if (score > 5 || score < 19) {
                    return true;
                }
                break;
            case 4:
                if (score == 4 || score == 8  || score == 12 || score == 16 || score == 20 || score == 24)
                return true;
                break;
            case 5:
//                somthing
                break;
            case 6:
//                somthing
                break;
            case 7:
//                somthing
                break;
            case 8:
//                somthing
                break;
            case 9:
//                somthing
                break;
            case 10:
//                somthing
                break;
            case 11:
//                somthing
                break;
            case 12:
//                somthing
                break;
        }

    }*/
}


































