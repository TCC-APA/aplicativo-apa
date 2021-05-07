package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.RetornaQuestionarioTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MenuQuestionariosActivity extends AppCompatActivity {

    private Button botaoQuestionario;
    //K: Turma, V: Nome dos questionarios
    private List<Questionario> questionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_questionarios);
        botaoQuestionario = findViewById(R.id.botao_camea);
        Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        try {
            questionarios = new RetornaQuestionarioTask(aluno.getMatricula()).execute().get();
            if(questionarios != null && !questionarios.isEmpty()) {
                botaoQuestionario.setText(questionarios.get(0).getNome());
                botaoQuestionario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToQuetionario();
                    }
                });
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goToQuetionario(){
        Intent intent = new Intent(MenuQuestionariosActivity.this, QuestionarioActivity.class);
        intent.putExtra(Questionario.class.getSimpleName(), questionarios.get(0));
        startActivity(intent);

    }
}
