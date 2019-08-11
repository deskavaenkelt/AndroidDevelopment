package se.dsve.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTvHowToPlay;
    ImageView mImageViewDiceLauncher;
    private boolean mShowHelp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvHowToPlay = findViewById(R.id.tvHowToPlay);
        mImageViewDiceLauncher = findViewById(R.id.ivDiceLauncher);

        mTvHowToPlay.setOnClickListener(this);
        mImageViewDiceLauncher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvHowToPlay:
                if (mShowHelp) {
                    mShowHelp = false;
                    mTvHowToPlay.setText(R.string.how_to_play_hide);
                } else {
                    mShowHelp = true;
                    mTvHowToPlay.setText(R.string.how_to_play_show);
                }
            break;
            case R.id.ivDiceLauncher:
                Intent intent = new Intent(MainActivity.this, ThrowDices.class);
                startActivity(intent);
                break;


        }
    }
}
