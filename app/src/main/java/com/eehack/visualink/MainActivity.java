package com.eehack.visualink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import static com.eehack.visualink.R.id.flipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    TextView bedroom, kitchen, bathroom, livingRoom, total, totalDesc;
    private boolean showingFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         bedroom = (TextView) findViewById(R.id.bedroom);
         kitchen = (TextView) findViewById(R.id.kitchen);
         bathroom = (TextView) findViewById(R.id.bathroom);
         livingRoom = (TextView) findViewById(R.id.living_room);

        total = (TextView) findViewById(R.id.total);
        totalDesc = (TextView) findViewById(R.id.totalDesc);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        Button chart = (Button) findViewById(R.id.button_chart);

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this, ChartActivity.class);
                startActivity(i);
            }
        });

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
                switchView();
            }
        });

    }

    private void switchView() {
        flipper.showNext();
        showingFirst = !showingFirst;
        if(showingFirst){
            kitchen.setText("Kitchen: -14%");
            livingRoom.setText("Living Room: -19%");
            bedroom.setText("Bedroom: -22%");
            bathroom.setText("Bathroom: -27%");

            total.setVisibility(View.VISIBLE);
            totalDesc.setVisibility(View.VISIBLE);
            total.setText("-30%");
        }else{
            kitchen.setText("Kitchen: +2%");
            livingRoom.setText("Living Room: +4%");
            bedroom.setText("Bedroom: +9%");
            bathroom.setText("Bathroom: -14%");

            totalDesc.setVisibility(View.INVISIBLE);
            total.setVisibility(View.INVISIBLE);
        }
    }
}
