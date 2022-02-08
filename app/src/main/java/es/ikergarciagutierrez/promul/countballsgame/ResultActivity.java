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

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult, tvRedBalls, tvGreenBalls, tvBlueBalls, tvCorrectAnswer,
            tvWrongAnswer, tvAnswerMessage, tvRedBallsAnswer, tvGreenBallsAnswer, tvBlueBallsAnswer;
    private EditText etRedBallsResult, etGreenBallsResult, etBlueBallsResult;
    private Button btCheckAnswer, btTakePhoto, btContinue;
    private String redBallsResult, greenBallsResult, blueBallsResult;
    private ImageView ivRecordPhoto;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
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

        defineCheckButtonListener();
        defineTakePhotoButtonListener();
        defineContinueButtonListener();
    }

    private void defineCheckButtonListener() {
        btCheckAnswer.setOnClickListener(view -> {
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

                showAnswer();
            }
        });
    }

    private void defineContinueButtonListener() {
        btContinue.setOnClickListener(v -> {
            Toast.makeText(this, R.string.finishToast, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void defineTakePhotoButtonListener() {
        btTakePhoto.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Record photo")
                    .setMessage("Do you want to take a photo to remember your record?")
                    .setNegativeButton("Cancel", (dialogInterface, which) -> {
                        builder.create().cancel();
                    })
                    .setPositiveButton("Confirm", (dialogInterface, which) -> {
                        takePhoto();
                    });
            builder.create().show();
        });
    }

    private void showAnswer() {
        redBallsResult = getIntent().getStringExtra("redBallsResult");
        greenBallsResult = getIntent().getStringExtra("greenBallsResult");
        blueBallsResult = getIntent().getStringExtra("blueBallsResult");

        tvAnswerMessage.setVisibility(View.VISIBLE);
        tvRedBallsAnswer.setVisibility(View.VISIBLE);
        tvGreenBallsAnswer.setVisibility(View.VISIBLE);
        tvBlueBallsAnswer.setVisibility(View.VISIBLE);
        btTakePhoto.setVisibility(View.VISIBLE);
        btContinue.setVisibility(View.VISIBLE);

        String redBallsAnswer = etRedBallsResult.getText().toString();
        String greenBallsAnswer = etGreenBallsResult.getText().toString();
        String blueBallsAnswer = etBlueBallsResult.getText().toString();

        if (redBallsAnswer.contains(redBallsResult) && greenBallsAnswer.contains(greenBallsResult)
                && blueBallsAnswer.contains(blueBallsResult)) {
            tvCorrectAnswer.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.correct_answer_sound);
            mediaPlayer.start();
        } else {
            tvWrongAnswer.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong_answer_sound);
            mediaPlayer.start();
        }

        String redBallsMessage = tvRedBallsAnswer.getText().toString();
        String greenBallsMessage = tvGreenBallsAnswer.getText().toString();
        String blueBallsMessage = tvBlueBallsAnswer.getText().toString();

        tvRedBallsAnswer.setText(redBallsMessage.replace("x", redBallsAnswer));
        tvGreenBallsAnswer.setText(greenBallsMessage.replace("x", greenBallsAnswer));
        tvBlueBallsAnswer.setText(blueBallsMessage.replace("x", blueBallsAnswer));
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

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