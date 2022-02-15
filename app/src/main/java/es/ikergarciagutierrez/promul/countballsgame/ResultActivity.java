package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase que controla el final y el resultado de la partida
 */
public class ResultActivity extends AppCompatActivity {

    // Variables de la clase
    private TextView tvResult, tvRedBalls, tvGreenBalls, tvBlueBalls, tvCorrectAnswer,
            tvWrongAnswer, tvAnswerMessage, tvRedBallsAnswer, tvGreenBallsAnswer, tvBlueBallsAnswer;
    private EditText etRedBallsResult, etGreenBallsResult, etBlueBallsResult;
    private Button btCheckAnswer, btTakePhoto, btContinue;
    private String redBallsResult, greenBallsResult, blueBallsResult;
    private ImageView ivRecordPhoto;
    private MediaPlayer mediaPlayer;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {

        // Reproducimos en bucle la canción de espera de respuestas del jugador
        mediaPlayer = MediaPlayer.create(this, R.raw.waiting_sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // Inicializamos los componentes del activity
        tvResult = findViewById(R.id.tvResult);
        tvRedBalls = findViewById(R.id.tvRedBalls);
        tvGreenBalls = findViewById(R.id.tvGreenBalls);
        tvBlueBalls = findViewById(R.id.tvBlueBalls);
        etRedBallsResult = findViewById(R.id.etRedBallsResult);
        etGreenBallsResult = findViewById(R.id.etGreenBallsResult);
        etBlueBallsResult = findViewById(R.id.etBlueBallsResult);
        btCheckAnswer = findViewById(R.id.btCheckAnswer);

        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvWrongAnswer = findViewById(R.id.tvWrongAnswer);
        tvAnswerMessage = findViewById(R.id.tvAnswerMessage);
        tvRedBallsAnswer = findViewById(R.id.tvRedBallsAnswer);
        tvGreenBallsAnswer = findViewById(R.id.tvGreenBallsAnswer);
        tvBlueBallsAnswer = findViewById(R.id.tvBlueBallsAnswer);
        btTakePhoto = findViewById(R.id.btTakePhoto);
        btContinue = findViewById(R.id.btContinue);
        ivRecordPhoto = findViewById(R.id.ivRecordPhoto);

        // Métodos que definen los listener de los botones
        defineCheckButtonListener();
        defineTakePhotoButtonListener();
        defineContinueButtonListener();
    }

    /**
     * Método que define el listener del botón btCheckAnswer
     */
    private void defineCheckButtonListener() {
        btCheckAnswer.setOnClickListener(view -> {

            // No mostramos los resultados hasta que estén rellenos los TextField
            if (TextUtils.isEmpty(etRedBallsResult.getText().toString()) ||
                    TextUtils.isEmpty(etGreenBallsResult.getText().toString()) ||
                    TextUtils.isEmpty(etBlueBallsResult.getText().toString())) {
                Toast.makeText(this, R.string.errorToast, Toast.LENGTH_SHORT).show();
            } else {
                tvResult.setVisibility(View.GONE);
                tvRedBalls.setVisibility(View.GONE);
                tvGreenBalls.setVisibility(View.GONE);
                tvBlueBalls.setVisibility(View.GONE);
                etRedBallsResult.setVisibility(View.GONE);
                etGreenBallsResult.setVisibility(View.GONE);
                etBlueBallsResult.setVisibility(View.GONE);
                btCheckAnswer.setVisibility(View.GONE);

                // Cargamos la solución de la partida
                showAnswer();
            }
        });
    }

    /**
     * Método que define el listener del botón btContinue
     */
    private void defineContinueButtonListener() {
        btContinue.setOnClickListener(v -> {

            // Mostramos un Toast y volvemos a la activity del menú
            Toast.makeText(this, R.string.finishToast, Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Método que define el listener del botón btTakePhoto
     */
    private void defineTakePhotoButtonListener() {
        btTakePhoto.setOnClickListener(view -> {

            // Mostramos un AlertDialog para saber si el usuario quiere hacer una foto
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Record photo")
                    .setMessage("Do you want to take a photo to remember your record?")
                    .setNegativeButton("Cancel", (dialogInterface, which) -> {

                        // Cerramos el AlertDialog
                        builder.create().cancel();
                    })
                    .setPositiveButton("Confirm", (dialogInterface, which) -> {

                        // Abrimos la cámara para hacer la foto
                        takePhoto();
                    });
            builder.create().show();
        });
    }

    /**
     * Método que muestra la solución de la partida
     */
    private void showAnswer() {

        // Pausamos la música de espera
        mediaPlayer.pause();

        // Obtenemos la solución de la partida
        redBallsResult = getIntent().getStringExtra("redBallsResult");
        greenBallsResult = getIntent().getStringExtra("greenBallsResult");
        blueBallsResult = getIntent().getStringExtra("blueBallsResult");

        // Mostramos los componentes en la pantalla
        tvAnswerMessage.setVisibility(View.VISIBLE);
        tvRedBallsAnswer.setVisibility(View.VISIBLE);
        tvGreenBallsAnswer.setVisibility(View.VISIBLE);
        tvBlueBallsAnswer.setVisibility(View.VISIBLE);
        btTakePhoto.setVisibility(View.VISIBLE);
        btContinue.setVisibility(View.VISIBLE);

        // Obtenemos las respuestas del jugador
        String redBallsAnswer = etRedBallsResult.getText().toString();
        String greenBallsAnswer = etGreenBallsResult.getText().toString();
        String blueBallsAnswer = etBlueBallsResult.getText().toString();

        // Comprobamos si las respuestas coinciden con la solución
        if (redBallsAnswer.contains(redBallsResult) && greenBallsAnswer.contains(greenBallsResult)
                && blueBallsAnswer.contains(blueBallsResult)) {

            // Si el jugador ha acertado reproducimos la música de victoria
            tvCorrectAnswer.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.correct_answer_sound);
            mediaPlayer.start();

        } else {

            // Si el jugador ha fallado reproducimos la música de derrota
            tvWrongAnswer.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong_answer_sound);
            mediaPlayer.start();
        }

        // Mostramos en pantalla las respuestas de la partida
        String redBallsMessage = tvRedBallsAnswer.getText().toString();
        String greenBallsMessage = tvGreenBallsAnswer.getText().toString();
        String blueBallsMessage = tvBlueBallsAnswer.getText().toString();

        tvRedBallsAnswer.setText(redBallsMessage.replace("x", redBallsResult));
        tvGreenBallsAnswer.setText(greenBallsMessage.replace("x", greenBallsResult));
        tvBlueBallsAnswer.setText(blueBallsMessage.replace("x", blueBallsResult));
    }

    /**
     * Método que abre la cámara del dispositivo para tomar la foto
     */
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Método que muestra en pantalla la foto tomada por el jugador
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivRecordPhoto.setImageBitmap(imageBitmap);
        }
        ivRecordPhoto.setVisibility(View.VISIBLE);
        tvAnswerMessage.setVisibility(View.GONE);
        tvRedBallsAnswer.setVisibility(View.GONE);
        tvGreenBallsAnswer.setVisibility(View.GONE);
        tvBlueBallsAnswer.setVisibility(View.GONE);
        btTakePhoto.setText("Change photo");
        if (tvCorrectAnswer.getVisibility() == View.VISIBLE) {
            tvCorrectAnswer.setText("I got it!");
        } else {
            tvWrongAnswer.setText("I tried...");
        }
    }

}