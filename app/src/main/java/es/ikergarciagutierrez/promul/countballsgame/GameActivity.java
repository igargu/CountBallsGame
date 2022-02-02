package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    private ObjectAnimator animation;
    private TextView tvReadyMessage;
    private int numBalls;
    private es.ikergarciagutierrez.promul.countballsgame.Ball ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
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
        // Empezamos el juego
        ball = findViewById(R.id.ball);
        animation = ObjectAnimator.ofFloat(tvReadyMessage, "translationY");
        animation.setDuration(5000);
        animation.start();
        // Al terminar el mensaje
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finishGame(String.valueOf(numBalls));
            }
        });

    }

    private void mediumMode() {
        numBalls = ThreadLocalRandom.current().nextInt(7, 11);
        // Empezamos el juego
        for (int i = 0; i < numBalls; i++) {

        }
        finishGame(String.valueOf(numBalls));
    }

    private void hardMode() {
        numBalls = ThreadLocalRandom.current().nextInt(10, 13);
        // Empezamos el juego
        for (int i = 0; i < numBalls; i++) {

        }
        finishGame(String.valueOf(numBalls));
    }

    private void finishGame(String answer) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("gameResult", answer);
        startActivity(intent);
        finish();
    }

}
