package se.dsve.buttonclickapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private TextView textView;
    private int numTimesClicked = 0;
    private static final String TAG = "MainActivity";
    private final String TEXT_CONTENTS = "TextContents";
    private final String COUNTER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.myEditText);
        userInput.setText("");
        Button button = findViewById(R.id.inputButton);
        textView = findViewById(R.id.myTextView);
        textView.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());              // Make textView scrollable
        View.OnClickListener ourClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ourClickListener");
                String result = userInput.getText().toString();
                result += "\n";
                textView.append(result);
                userInput.setText("");
            }
        };
        button.setOnClickListener(ourClickListener);

        Button inputButton = findViewById(R.id.myButton);
        View.OnClickListener inputClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: inputClickListener");
                numTimesClicked += 1;
                String result = "The button COUNTER got tapped " + numTimesClicked + " time";
                if (numTimesClicked != 1) {
                    result += "'s";
                }
                result += "\n";
                textView.append(result);

            }
        };
        button.setOnClickListener(ourClickListener);
        inputButton.setOnClickListener(inputClickListener);
        Log.d(TAG, "onCreate: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
        //String savedString = savedInstanceState.getString(TEXT_CONTENTS);
        //textView.setText(savedString);
        textView.setText(savedInstanceState.getString(TEXT_CONTENTS));
//        numTimesClicked = Integer.parseInt(COUNTER);
        Log.d(TAG, "onRestoreInstanceState: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");
        outState.putString(COUNTER, String.valueOf(numTimesClicked));
        outState.putString(TEXT_CONTENTS, textView.getText().toString());   // Save content before the save command
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: in");
        super.onDestroy();
        Log.d(TAG, "onDestroy: out");
    }


}

// start code
/*public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button button;
    private TextView textView;
    private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.myEditText);
        button = (Button) findViewById(R.id.myButton);
        textView = (TextView) findViewById(R.id.myTextView);
        textView.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());              // Make textView scrollable
        View.OnClickListener ourClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numTimesClicked += 1;
                String result = "The button got tapped " + numTimesClicked + " time";
                if (numTimesClicked != 1) {
                    result += "'s";
                }
                result += "\n";
                textView.append(result);

            }
        };
        button.setOnClickListener(ourClickListener);

    }
}*/
