package com.example.flyingbirdproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private TextView information;
    private TextView totalScore;
    private TextView currentScore;
    private TextView playAgain;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        information = findViewById(R.id.textViewInfo);
        totalScore = findViewById(R.id.totalScore);
        currentScore = findViewById(R.id.currentScore);
        playAgain = findViewById(R.id.tryAgain);

        score = getIntent().getIntExtra("score",0);
        currentScore.setText("Your score: " + score);

        sharedPreferences = this.getSharedPreferences("Score",MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("totalScore",0);

        if (score >= 200){
            information.setText("Поздравляю ты выиграл!");
            totalScore.setText("Total score:"+ score );
            sharedPreferences.edit().putInt("totalScore",score).apply();
        }else if (score >= highestScore){
            information.setText("Теперь Джун будет год страдать из за тебя(((");
            totalScore.setText("Total score:"+ score );
            sharedPreferences.edit().putInt("totalScore",score).apply();
        }else{
            information.setText("Теперь Джун будет год страдать из за тебя(((");
            totalScore.setText("Total score:"+ highestScore );
        }
    }

    public void onTryAgain(View view){
        startActivity(new Intent(ResultActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Джун тебе не простит!");
        builder.setMessage("Ты точно хочешь выйти?");
        builder.setCancelable(false);
        builder.setNegativeButton("Выйти", (dialog, which) -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        });
        builder.setPositiveButton("Остаться", (dialog, which) -> {
                dialog.cancel();
        });
        builder.show();
    }
}