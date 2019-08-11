package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowResult extends AppCompatActivity {

    TextView mSendToTextView;

    private String mShowThis;
    private int mAndThis;

    private void receivedIntent() {
        Intent receivedIntent = getIntent();
        mShowThis = receivedIntent.getStringExtra("show");
        mAndThis = receivedIntent.getIntExtra("and", -1);
    }

    private void returnThis() {
        Intent sendBackThis = new Intent();
        sendBackThis.putExtra("coming_back", "Success");
        setResult(RESULT_OK, sendBackThis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        receivedIntent();
        mSendToTextView = findViewById(R.id.tvSentTextHere);

        String output = mShowThis + mAndThis;
        mSendToTextView.setText(output);

        returnThis();
    }
}
