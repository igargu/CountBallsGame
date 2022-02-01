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

    private TextView tvResult;
    private EditText etResult;
    private Button btCheckAnswer;

    private TextView tvCorrectAnswer;
    private TextView tvWrongAnswer;
    private TextView tvAnswerMessage;

    private Button btContinue;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        tvResult = findViewById(R.id.tvResult);
        etResult = findViewById(R.id.etResult);
        btCheckAnswer = findViewById(R.id.btCheckAnswer);

        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        tvWrongAnswer = findViewById(R.id.tvWrongAnswer);
        tvAnswerMessage = findViewById(R.id.tvAnswerMessage);

        btContinue = findViewById(R.id.btContinue);

        defineCheckButtonListener();
        defineContinueButtonListener();
    }

    private void defineCheckButtonListener() {
        btCheckAnswer.setOnClickListener(view -> {
            if (TextUtils.isEmpty(etResult.getText().toString())) {
                Toast.makeText(this, R.string.errorToast, Toast.LENGTH_SHORT).show();
            } else {
                tvResult.setVisibility(View.GONE);
                etResult.setVisibility(View.GONE);
                btCheckAnswer.setVisibility(View.GONE);

                showAnswer();
            }
        });
    }

    private void defineContinueButtonListener() {
        Toast.makeText(this, R.string.finishToast, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showAnswer() {
        tvAnswerMessage.setVisibility(View.VISIBLE);
        btContinue.setVisibility(View.VISIBLE);
        if (etResult.getText().equals(result)) {
            tvCorrectAnswer.setVisibility(View.VISIBLE);
        } else {
            tvWrongAnswer.setVisibility(View.VISIBLE);
        }
    }
}