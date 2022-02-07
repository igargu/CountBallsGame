package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    private ConstraintLayout layGameActivity;
    private AttributeSet attributeSet;
    private ObjectAnimator animation;
    private TextView tvReadyMessage;
    private int numBalls;
    private String[] colorBalls = {"#FF3333", "#07DC07", "#1C86FD"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // attributeSet = new AttributeSet();

        initialize();
    }

    private void initialize() {
        layGameActivity = (ConstraintLayout) findViewById(R.id.layGameActivity);
        readyMessage();
    }

    private void readyMessage() {
        // Altura de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int finalPosition = displayMetrics.heightPixels;
        // Mensaje al empezar el juego
        tvReadyMessage = findViewById(R.id.tvReadyMessage);
        animation = ObjectAnimator.ofFloat(tvReadyMessage, "translationY", finalPosition);
        animation.setDuration(5000);
        animation.start();
        // Al terminar el mensaje
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // Seleccionamos dificultad
                switch (getIntent().getStringExtra("gameMode")) {
                    case "easy":
                        easyMode();
                        break;
                    case "medium":
                        mediumMode();
                        break;
                    case "hard":
                        hardMode();
                        break;
                }
            }
        });

    }

    private void easyMode() {
        numBalls = ThreadLocalRandom.current().nextInt(5, 8);
        int redBalls = 0, greenBalls = 0, blueBalls = 0;

        // Empezamos el juego
        gameLevelSound();

        int[] balls = {R.id.ball1, R.id.ball2, R.id.ball3, R.id.ball4, R.id.ball5, R.id.ball6,
                R.id.ball7, R.id.ball8, R.id.ball9, R.id.ball10, R.id.ball11, R.id.ball12};

        for (int i = 0; i < numBalls; i++) {

            int color = ThreadLocalRandom.current().nextInt(0, 3);
            String ballColor = colorBalls[color];

            ImageView ball = findViewById(balls[i]);
            ball.setColorFilter(Color.parseColor(ballColor));
            ball.setVisibility(View.VISIBLE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int finalPositionx = displayMetrics.widthPixels;
            int finalPositiony = displayMetrics.heightPixels;
            animation = ObjectAnimator.ofFloat(ball, "translationY", finalPositiony);
            //animation = ObjectAnimator.ofFloat(ball, "translationY", finalPositiony);
            animation.setDuration(5000);
            animation.start();

            switch (color) {
                case 0:
                    redBalls = redBalls + 1;
                    break;
                case 1:
                    greenBalls = greenBalls + 1;
                    break;
                case 2:
                    blueBalls = blueBalls + 1;
                    break;
            }

        }

        // finishGame(String.valueOf(redBalls), String.valueOf(greenBalls), String.valueOf(blueBalls));

    }

    private void mediumMode() {
        numBalls = ThreadLocalRandom.current().nextInt(7, 11);
        int redBalls = 0, greenBalls = 0, blueBalls = 0;

        // Empezamos el juego
        gameLevelSound();

        for (int i = 0; i < numBalls; i++) {

            int color = ThreadLocalRandom.current().nextInt(0, 3);
            String ballColor = colorBalls[color];

            /*Ball ball = new Ball(this, attributeSet);
            ball.setBackgroundColor(Color.parseColor(ballColor));
            layGameActivity.addView(ball);*/

            switch (color) {
                case 0:
                    redBalls = redBalls + 1;
                    break;
                case 1:
                    greenBalls = greenBalls + 1;
                    break;
                case 2:
                    blueBalls = blueBalls + 1;
                    break;
            }

        }
        finishGame(String.valueOf(redBalls), String.valueOf(greenBalls), String.valueOf(blueBalls));
    }

    private void hardMode() {
        numBalls = ThreadLocalRandom.current().nextInt(10, 13);
        int redBalls = 0, greenBalls = 0, blueBalls = 0;

        // Empezamos el juego
        gameLevelSound();

        for (int i = 0; i < numBalls; i++) {

            int color = ThreadLocalRandom.current().nextInt(0, 3);
            String ballColor = colorBalls[color];

            /*Ball ball = new Ball(this, attributeSet);
            ball.setBackgroundColor(Color.parseColor(ballColor));
            layGameActivity.addView(ball);*/

            switch (color) {
                case 0:
                    redBalls = redBalls + 1;
                    break;
                case 1:
                    greenBalls = greenBalls + 1;
                    break;
                case 2:
                    blueBalls = blueBalls + 1;
                    break;
            }

        }
        finishGame(String.valueOf(redBalls), String.valueOf(greenBalls), String.valueOf(blueBalls));
    }

    private void gameLevelSound() {
        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.difficulty_selected_sound);
        //mediaPlayer.start();
    }

    private void finishGame(String redBalls, String greenBalls, String blueBalls) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("redBallsResult", redBalls);
        intent.putExtra("greenBallsResult", greenBalls);
        intent.putExtra("blueBallsResult", blueBalls);
        startActivity(intent);
        finish();
    }

}
