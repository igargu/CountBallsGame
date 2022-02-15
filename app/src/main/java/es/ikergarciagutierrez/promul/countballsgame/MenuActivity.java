package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

/**
 * Clase que controla el menú de la aplicación
 */
public class MenuActivity extends AppCompatActivity {

    // Variables de la clase
    private Button btEasyMode, btMediumMode, btHardMode;
    private MediaPlayer mediaPlayerMenu, mediaPlayerDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
    }

    private void initialize() {

        // Reproducimos la canción del menú y la dejamos en bucle
        mediaPlayerMenu = MediaPlayer.create(this, R.raw.menu_sound);
        mediaPlayerMenu.start();
        mediaPlayerMenu.setLooping(true);

        // Inicializamos los botones de dificultad
        btEasyMode = findViewById(R.id.btEasyMode);
        btMediumMode = findViewById(R.id.btMediumMode);
        btHardMode = findViewById(R.id.btHardMode);

        // Incializamos el sonido de selección de menú
        mediaPlayerDifficulty = MediaPlayer.create(this, R.raw.difficulty_selected_sound);

        // Definimos los listener de los botones
        defineBTEasyModeListener();
        defineBTMediumModeListener();
        defineBTHardModeListener();
    }

    /**
     * Método que controla el listener del botón btEasyMode
     */
    private void defineBTEasyModeListener() {
        btEasyMode.setOnClickListener(v -> {

            // Pausamos la música del menú
            mediaPlayerMenu.pause();

            // Reproducimos el sonido de selección del menú y cambiamos de activity
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);

                // Enviamos el modo de juego seleccionado
                intent.putExtra("gameMode", "easy");
                startActivity(intent);
                finish();
            });

        });
    }

    /**
     * Método que controla el listener del botón btMediumMode
     */
    private void defineBTMediumModeListener() {
        btMediumMode.setOnClickListener(v -> {

            // Pausamos la música del menú
            mediaPlayerMenu.pause();

            // Reproducimos el sonido de selección del menú y cambiamos de activity
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);

                // Enviamos el modo de juego seleccionado
                intent.putExtra("gameMode", "medium");
                startActivity(intent);
                finish();
            });

        });
    }

    /**
     * Método que controla el listener del botón btHardMode
     */
    private void defineBTHardModeListener() {
        btHardMode.setOnClickListener(v -> {

            // Pausamos la música el menú
            mediaPlayerMenu.pause();

            // Reproducimos el sonido de selección del menú y cambiamos de activity
            mediaPlayerDifficulty.start();
            mediaPlayerDifficulty.setOnCompletionListener(mediaPlayer -> {
                Intent intent = new Intent(this, GameActivity.class);

                // Enviamos el modo de juego seleccionado
                intent.putExtra("gameMode", "hard");
                startActivity(intent);
                finish();
            });
        });
    }
}