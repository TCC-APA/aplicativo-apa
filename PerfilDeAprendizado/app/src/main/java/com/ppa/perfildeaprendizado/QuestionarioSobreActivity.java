package com.ppa.perfildeaprendizado;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionarioSobreActivity extends AppCompatActivity {

    @BindView(R.id.nome_questionario)
    protected TextView nome;
    @BindView(R.id.sobre_questionario)
    protected TextView sobre;

    private Questionario questionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_questionario);
        ButterKnife.bind(this);

        this.questionario = (Questionario) getIntent().getSerializableExtra(Questionario.class.getSimpleName());
        nome.setText("Sobre o questionário: " + questionario.getNome());
        sobre.setText(questionario.getSobre());

    }
}
