package com.antoniocappiello.androidanimations.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.antoniocappiello.androidanimations.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.single_view_animations_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleViewAnimationsActivity.class));
            }
        });
    }

    public void goToSingleViewAnimations(View view) {
        startActivity(new Intent(MainActivity.this, SingleViewAnimationsActivity.class));
    }

    public void goToLayoutAnimationController(View view) {
    }

    public void goToGridAnimationController(View view) {
    }

}
