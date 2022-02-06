package es.ikergarciagutierrez.promul.countballsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult, tvCorrectAnswer, tvWrongAnswer, tvAnswerMessage, tvRedBallsAnswer,
            tvGreenBallsAnswer, tvBlueBallsAnswer;
    private EditText etRedBallsResult, etGreenBallsResult, etBlueBallsResult;
    private Button btCheckAnswer, btContinue;
    private String redBallsResult, greenBallsResult, blueBallsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        tvResult = findViewById(R.id.tvResult);
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
        btContinue = findViewById(R.id.btContinue);

        defineCheckButtonListener();
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

    private void showAnswer() {
        redBallsResult = getIntent().getStringExtra("redBallsResult");
        greenBallsResult = getIntent().getStringExtra("greenBallsResult");
        blueBallsResult = getIntent().getStringExtra("blueBallsResult");

        tvAnswerMessage.setVisibility(View.VISIBLE);
        tvRedBallsAnswer.setVisibility(View.VISIBLE);
        tvGreenBallsAnswer.setVisibility(View.VISIBLE);
        tvBlueBallsAnswer.setVisibility(View.VISIBLE);
        btContinue.setVisibility(View.VISIBLE);

        String redBallsAnswer = etRedBallsResult.getText().toString();
        String greenBallsAnswer = etGreenBallsResult.getText().toString();
        String blueBallsAnswer = etBlueBallsResult.getText().toString();

        if (redBallsAnswer.contains(redBallsResult) && greenBallsAnswer.contains(greenBallsResult)
                && blueBallsAnswer.contains(blueBallsResult)) {
            tvCorrectAnswer.setVisibility(View.VISIBLE);
        } else {
            tvWrongAnswer.setVisibility(View.VISIBLE);
        }

        String redBallsMessage = tvRedBallsAnswer.getText().toString();
        String greenBallsMessage = tvGreenBallsAnswer.getText().toString();
        String blueBallsMessage = tvBlueBallsAnswer.getText().toString();

        tvRedBallsAnswer.setText(redBallsMessage.replace("x", redBallsAnswer));
        tvGreenBallsAnswer.setText(greenBallsMessage.replace("x", greenBallsAnswer));
        tvBlueBallsAnswer.setText(blueBallsMessage.replace("x", blueBallsAnswer));
    }
}