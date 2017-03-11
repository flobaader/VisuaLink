package com.eehack.visualink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ViewFlipper;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

       final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper2);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(500); //time in milliseconds

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(500); //time in milliseconds

        flipper.setInAnimation(fadeIn);
        flipper.setOutAnimation(fadeOut);

        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           flipper.showNext(); }
        });

    }
}
