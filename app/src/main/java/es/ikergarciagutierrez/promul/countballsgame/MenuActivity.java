package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btEasyMode, btMediumMode, btHardMode;
    private MediaPlayer mediaPlayerMenu, mediaPlayerDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
    }

    private void initialize() {

        mediaPlayerMenu = MediaPlayer.create(this, R.raw.menu_sound);
        mediaPlayerMenu.start();
        mediaPlayerMenu.setLooping(true);

        btEasyMode = findViewById(R.id.btEasyMode);
        btMediumMode = findViewById(R.id.btMediumMode);
        btHardMode = findViewById(R.id.btHardMode);

        mediaPlayerDifficulty = MediaPlayer.create(this, R.raw.difficulty_selected_sound);

        defineBTEasyModeListener();
        defineBTMediumModeListener();
        defineBTHardModeListener();
    }

    private void defineBTEasyModeListener() {
        btEasyMode.setOnClickListener(v -> {
            mediaPlayerMenu.pause();
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "easy");
                startActivity(intent);
                finish();
            });

        });
    }

    private void defineBTMediumModeListener() {
        btMediumMode.setOnClickListener(v -> {
            mediaPlayerMenu.pause();
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "medium");
                startActivity(intent);
                finish();
            });

        });
    }

    private void defineBTHardModeListener() {
        btHardMode.setOnClickListener(v -> {
            mediaPlayerMenu.pause();
            // Suena pista de audio y cambiamos de activity cuando acaba
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("gameMode", "hard");
                startActivity(intent);
                finish();
            });
        });
    }
}