package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity {

    private Button btEasyMode, btMediumMode, btHardMode;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
    }

    private void initialize() {

        btEasyMode = findViewById(R.id.btEasyMode);
        btMediumMode = findViewById(R.id.btMediumMode);
        btHardMode = findViewById(R.id.btHardMode);

        mediaPlayer = MediaPlayer.create(this, R.raw.difficulty_selected_sound);

        defineBTEasyModeListener();
        defineBTMediumModeListener();
        defineBTHardModeListener();
    }

    private void defineBTEasyModeListener() {
        btEasyMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "easy");
                startActivity(intent);
            });

        });
    }

    private void defineBTMediumModeListener() {
        btMediumMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "medium");
                startActivity(intent);
            });

        });
    }

    private void defineBTHardModeListener() {
        btHardMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "hard");
                startActivity(intent);
            });
        });
    }
}