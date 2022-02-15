package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase que controla el juego
 */
public class GameActivity extends AppCompatActivity {

    // Variables de la clase
    private int[] balls = {R.id.ball1, R.id.ball2, R.id.ball3, R.id.ball4, R.id.ball5, R.id.ball6,
            R.id.ball7, R.id.ball8, R.id.ball9, R.id.ball10, R.id.ball11, R.id.ball12};
    private ObjectAnimator animation;
    private TextView tvReadyMessage;
    private int numBalls;
    private String[] colorBalls = {"#FF3333", "#07DC07", "#1C86FD"};
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {

        // Inicializamos la música del juego
        mediaPlayer = MediaPlayer.create(this, R.raw.game_level_sound);

        // Mostramos un mensaje en la pantalla antes de comenzar la partida
        readyMessage();
    }

    /**
     * Método que muestra un mensaje en la pantalla antes de comenzar la partida
     */
    private void readyMessage() {

        // Calculamos el alto de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int finalPosition = displayMetrics.heightPixels;

        // Inicializamos el TextView y lo animamos para que recorra la pantalla de arriba a abajo
        tvReadyMessage = findViewById(R.id.tvReadyMessage);
        animation = ObjectAnimator.ofFloat(tvReadyMessage, "translationY", finalPosition);
        animation.setDuration(3000);
        animation.start();

        // Reproducimos la música del juego
        mediaPlayer.start();

        // Al terminar la animación del mensaje se carga la partida según el modo de juego seleccionado
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                gameModeSelection();
            }
        });

    }

    /**
     * Método que reproduce la música del juego
     */
    private void gameLevelSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.game_level_sound);
        mediaPlayer.start();
    }

    /**
     * Método que carga la partida según el modo de juego seleccionado en el menú
     */
    private void gameModeSelection() {

        // Generamos aleatoriamente la cantidad de bolas que van aparecer en pantalla según la dificultad
        switch (getIntent().getStringExtra("gameMode")) {
            case "easy":
                numBalls = ThreadLocalRandom.current().nextInt(5, 8);
                break;

            case "medium":
                numBalls = ThreadLocalRandom.current().nextInt(7, 11);
                break;

            case "hard":
                numBalls = ThreadLocalRandom.current().nextInt(10, 13);
                break;
        }

        // Cargamos el juego con el número de bolas que se han generado
        game(numBalls);

    }

    /**
     * Método que carga la partida según el número de bolas que van aparecer en pantalla
     *
     * @param numBalls Número de bolas que van aparecer en pantalla
     */
    private void game(int numBalls) {

        int ejeX = 0, ejeY = 0, redBalls = 0, greenBalls = 0, blueBalls = 0;

        // Inicializamos cada bola en cada ciclo del bucle
        for (int i = 0; i < numBalls; i++) {

            // Generamos la velocidad de cada bola aleatoriamente según la dificultad
            switch (getIntent().getStringExtra("gameMode")) {
                case "easy":
                    ejeX = ThreadLocalRandom.current().nextInt(0, 51);
                    ejeY = ThreadLocalRandom.current().nextInt(0, 51);
                    break;

                case "medium":
                    ejeX = ThreadLocalRandom.current().nextInt(20, 71);
                    ejeY = ThreadLocalRandom.current().nextInt(20, 71);
                    break;

                case "hard":
                    ejeX = ThreadLocalRandom.current().nextInt(40, 91);
                    ejeY = ThreadLocalRandom.current().nextInt(30, 81);
                    break;
            }

            // Generamos aleatoriamente el color de la bola
            int color = ThreadLocalRandom.current().nextInt(0, 3);
            String ballColor = colorBalls[color];

            // Inicializamos la bola, estableciendo su color y velocidad
            Ball ball = findViewById(balls[i]);
            ball.setVisibility(View.VISIBLE);
            ball.setColor(ballColor);
            ball.setVelocidad(ejeX, ejeY);

            // Guardamos en variables el color de la bola
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

        // Establecemos un delay que será la duración de la partida, 10 seg en este caso, y cargamos
        // la siguiente activity pasánsole a la función de cantidad de bolas de cada color
        int finalRedBalls = redBalls, finalGreenBalls = greenBalls, finalBlueBalls = blueBalls;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finishGame(String.valueOf(finalRedBalls), String.valueOf(finalGreenBalls), String.valueOf(finalBlueBalls));
            }
        }, 10000);

    }

    /**
     * Método que termina la partida y carga la siguiente activity, enviando la solución correcta
     *
     * @param redBalls   Número de bolas rojas que han aparecido
     * @param greenBalls Número de bolas verdes que han aparecido
     * @param blueBalls  Número de bolas azules que han aparecido
     */
    private void finishGame(String redBalls, String greenBalls, String blueBalls) {

        // Se detiene la música del juego
        mediaPlayer.stop();

        // Se carga la siguiente activity
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("redBallsResult", redBalls);
        intent.putExtra("greenBallsResult", greenBalls);
        intent.putExtra("blueBallsResult", blueBalls);
        startActivity(intent);
        finish();
    }

}