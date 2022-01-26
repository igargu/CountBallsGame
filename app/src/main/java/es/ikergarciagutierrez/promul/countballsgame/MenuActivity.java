package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btEasyMode, btMediumMode, btHardMode;

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

        defineBTEasyModeListener();
        defineBTMediumModeListener();
        defineBTHardModeListener();
    }

    private void defineBTEasyModeListener() {
        // Suena pista de audio
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void defineBTMediumModeListener() {
        // Suena pista de audio

    }

    private void defineBTHardModeListener() {
        // Suena pista de audio

    }
}