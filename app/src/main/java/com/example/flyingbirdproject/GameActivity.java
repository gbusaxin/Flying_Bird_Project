package com.example.flyingbirdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird, enemy1, enemy2, enemy3, coin1, coin2, right1, right2, right3;
    private TextView textViewScore, textViewStartInfo;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl = false;
    private Runnable runnable;
    private Handler handler;

    int birdX, birdY;
    int enemy1X, enemy2X, enemy3X, coin1X, coin2X;
    int enemy1Y, enemy2Y, enemy3Y, coin1Y, coin2Y;
    int screenWidth, screenHeight;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.imageViewBird);
        enemy1 = findViewById(R.id.imageViewEnemy1);
        enemy2 = findViewById(R.id.imageViewEnemy2);
        enemy3 = findViewById(R.id.imageViewEnemy3);
        coin1 = findViewById(R.id.imageViewCoin);
        coin2 = findViewById(R.id.imageViewCoin2);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        textViewScore = findViewById(R.id.textViewScore);
        textViewStartInfo = findViewById(R.id.textViewStartInfo);
        constraintLayout = findViewById(R.id.constraintLayout);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                textViewStartInfo.setVisibility(View.INVISIBLE);

                if (!beginControl) {
                    beginControl = true;

                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight = (int) constraintLayout.getHeight();

                    birdX = (int) bird.getX();
                    birdY = (int) bird.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {

                            moveToBird();
                            enemyControl();

                            handler.postDelayed(runnable, 20);
                        }
                    };
                    handler.post(runnable);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        touchControl = true;
                    }

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        touchControl = false;
                    }
                }
                return true;
            }
        });
    }

    private void enemyControl() {
        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        enemy1X = enemy1X - (screenWidth / 150);

        if (enemy1X < 0){
            enemy1X = screenWidth + 200;
            enemy1X = (int) Math.floor(Math.random() * screenHeight);

            if (enemy1Y <= 0){
                enemy1Y = 0;
            }

            if (enemy1Y >= (screenHeight - enemy1.getHeight())){
                enemy1Y = (screenHeight - enemy1.getHeight());
            }
        }
    }

    private void moveToBird() {
        if (touchControl){
            birdY = birdY - (screenHeight / 50);
        } else{
            birdY = birdY + (screenHeight / 50);
        }

        if (birdY <= 0){
            birdY = 0;
        }

        if (birdY >= (screenHeight - bird.getHeight())){
            birdY = (screenHeight - bird.getHeight());
        }
        bird.setY(birdY);
    }
}