package com.square_play.squareplay.DrawingSquares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.square_play.squareplay.R;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        new SquaresDraw(this,getIntent().getIntExtra("Level",0));
    }

}
