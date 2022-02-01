package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    ObjectAnimator animation;
    TextView tvReadyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
        readyMessage();
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
    }

    private void easyMode() {
        // Empezamos el juego
        finishGame();
    }

    private void mediumMode() {
        // Empezamos el juego
        finishGame();
    }

    private void hardMode() {
        // Empezamos el juego
        finishGame();
    }

    private void finishGame() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("gameResult", "");
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

}

/*



 */