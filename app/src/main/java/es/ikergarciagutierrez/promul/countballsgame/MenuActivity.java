package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        btEasyMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            Bundle bundle = new Bundle();
            bundle.putSerializable("gameMode", "easy");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent, bundle);
        });
    }

    private void defineBTMediumModeListener() {
        btMediumMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            Bundle bundle = new Bundle();
            bundle.putSerializable("gameMode", "medium");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }

    private void defineBTHardModeListener() {
        btHardMode.setOnClickListener(v -> {
            // Suena pista de audio y cambiamos de activity cuando acaba
            Bundle bundle = new Bundle();
            bundle.putSerializable("gameMode", "hard");
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }
}