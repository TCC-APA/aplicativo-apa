package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.BuscarPerfilAlunoTask;
import com.ppa.perfildeaprendizado.task.RetornaQuestionarioTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;

public class MenuQuestionariosActivity extends AppCompatActivity {

    private Button botaoQuestionario;
    //K: Turma, V: Nome dos questionarios
    private List<Questionario> questionarios;
    public static Questionario questionarioEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_questionarios);
        botaoQuestionario = findViewById(R.id.botao_camea);
        try {
            Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
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
        try {
            Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
            PerfilAluno perfilAluno = new BuscarPerfilAlunoTask(aluno.getMatricula(), 48L).execute().get();
            if(perfilAluno != null){
                Intent intent = new Intent(MenuQuestionariosActivity.this, ResultadoActivity.class);
                intent.putExtra(Aluno.class.getSimpleName(), aluno);
                startActivity(intent);
            }else {
                Intent intent = new Intent(MenuQuestionariosActivity.this, QuestionarioActivity.class);
                intent.putExtra(Aluno.class.getSimpleName(), aluno);
                questionarioEscolhido = questionarios.get(0);
//                intent.putExtra(Questionario.class.getSimpleName(), questionarios.get(0));
                startActivity(intent);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
