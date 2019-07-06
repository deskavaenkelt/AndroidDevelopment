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
                int num = 0;
                changeStateDice(num);
                dice1.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });

        final Button dice2 = findViewById(R.id.dice2);
        dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 1;
                changeStateDice(num);
                dice2.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });

        final Button dice3 = findViewById(R.id.dice3);
        dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 2;
                changeStateDice(num);
                dice3.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });

        final Button dice4 = findViewById(R.id.dice4);
        dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 3;
                changeStateDice(num);
                dice4.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });

        final Button dice5 = findViewById(R.id.dice5);
        dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 4;
                changeStateDice(num);
                dice5.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });

        final Button dice6 = findViewById(R.id.dice6);
        dice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num =5;
                changeStateDice(num);
                dice6.setBackgroundResource(displayDice(valueOfDice[num], lockDice[num]));
            }
        });


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

                dice1.setBackgroundResource(displayDice(valueOfDice[0], lockDice[0]));
                dice2.setBackgroundResource(displayDice(valueOfDice[1], lockDice[1]));
                dice3.setBackgroundResource(displayDice(valueOfDice[2], lockDice[2]));
                dice4.setBackgroundResource(displayDice(valueOfDice[3], lockDice[3]));
                dice5.setBackgroundResource(displayDice(valueOfDice[4], lockDice[4]));
                dice6.setBackgroundResource(displayDice(valueOfDice[5], lockDice[5]));

            }
        });

    }

    private void changeStateDice(int num) {
        if (lockDice[num]) {
            lockDice[num] = false;
        } else {
            lockDice[num] = true;
        }
    }



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

    public void saveResultsIn() {
        Log.d(TAG, "saveResultsIn: something");
    }
}
