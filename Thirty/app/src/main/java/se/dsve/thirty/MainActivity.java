package se.dsve.thirty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private static int valueOfDice[] = new int[]{1, 2, 3, 4, 5, 6};
    private static boolean lockDice[] = new boolean[]{false, false, false, false, false, false};

    private int countTurns = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button dice1 = findViewById(R.id.dice1);
        dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lockDice[0]) {
                    lockDice[0] = false;
                    dice1.setBackgroundResource(displayDice(valueOfDice[0], "white"));
                } else {
                    lockDice[0] = true;
                    dice1.setBackgroundResource(displayDice(valueOfDice[0], "red"));
                }
                Log.d(TAG, "onClick: lockDice = " + lockDice[0]);
            }
        });
        final Button dice2 = findViewById(R.id.dice2);
        final Button dice3 = findViewById(R.id.dice3);
        final Button dice4 = findViewById(R.id.dice4);
        final Button dice5 = findViewById(R.id.dice5);
        final Button dice6 = findViewById(R.id.dice6);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                b.setBackgroundResource(R.drawable.red4);
            }
        };
        //dice1.setOnClickListener(onClickListener);
        dice2.setOnClickListener(onClickListener);
        dice3.setOnClickListener(onClickListener);
        dice4.setOnClickListener(onClickListener);
        dice5.setOnClickListener(onClickListener);
        dice6.setOnClickListener(onClickListener);


        Button rollDiceButton = findViewById(R.id.rollButton);
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countTurns >= 0 || countTurns <3) {
                    rollDice();
                } else {
                    countTurns = 0;
                    saveResultsIn();
                }

                dice1.setBackgroundResource(displayWhiteDice(valueOfDice[0]));
                dice2.setBackgroundResource(displayWhiteDice(valueOfDice[1]));
                dice3.setBackgroundResource(displayWhiteDice(valueOfDice[2]));
                dice4.setBackgroundResource(displayWhiteDice(valueOfDice[3]));
                dice5.setBackgroundResource(displayWhiteDice(valueOfDice[4]));
                dice6.setBackgroundResource(displayWhiteDice(valueOfDice[5]));

            }
        });

    }

    /*private void updateDisplayedDices() {
        if (lockDice[0]) {
            dice1.setBackgroundResource(displayRedDice(valueOfDice[0]));
        } else {
            dice1.setBackgroundResource(displayWhiteDice(valueOfDice[0]));
        }
    }*/

    private void setLockDiceToFalse() {
        for (int i = 0; i < lockDice.length; i++) {
            lockDice[i] = false;
        }
        countTurns++;
    }

    private void rollDice() {
        for (int i = 0; i < 6; i++) {
            if (!lockDice[i]) {
                valueOfDice[i] = (int) ((Math.random() * 6) + 1);
            }
        }
    }

    public int displayWhiteDice(int returnWhiteDice) {
        switch (returnWhiteDice) {
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

    public int displayRedDice(int returnRedDice) {
        switch (returnRedDice) {
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
    }

    public int displayDice(int returnDiceNumber, String colorToReturn) {
        if (colorToReturn.equals("")) {
            return 0;
        }

        if (colorToReturn.equals("grey")) {
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
        } else if (colorToReturn.equals("red")) {
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
        } else if (colorToReturn.equals("white")) {
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
        } else {
            return 0;
        }



    }

    public void saveResultsIn() {
        Log.d(TAG, "saveResultsIn: something");
    }
}
