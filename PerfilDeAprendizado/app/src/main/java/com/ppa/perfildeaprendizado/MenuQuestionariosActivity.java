package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.RetornaQuestionarioTask;
import com.ppa.perfildeaprendizado.ui.adapters.MenuQuestionarioAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuQuestionariosActivity extends AppCompatActivity {

    @BindView(R.id.lista_questionarios)
    RecyclerView listaQuestionarios;

    private List<Questionario> questionarios;
    private MenuQuestionarioAdapter adapter;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_questionarios);

        ButterKnife.bind(this);
        try {
            aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
            questionarios = new RetornaQuestionarioTask(aluno.getMatricula()).execute().get();
            if(questionarios != null && !questionarios.isEmpty()) {
                carregarView();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goToQuetionario(Questionario questionario){
        Intent intent = new Intent(MenuQuestionariosActivity.this, QuestionarioActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    public void goToResultado(Questionario questionario){
        Intent intent = new Intent(MenuQuestionariosActivity.this, ResultadoActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    public void goToSobre(Questionario questionario){
        Intent intent = new Intent(MenuQuestionariosActivity.this, QuestionarioSobreActivity.class);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    private void carregarView(){
        adapter = new MenuQuestionarioAdapter(questionarios, aluno.getMatricula(), this);
        listaQuestionarios.setHasFixedSize(false);
        listaQuestionarios.setItemAnimator(new DefaultItemAnimator());
        listaQuestionarios.setLayoutManager(new LinearLayoutManager(this));
        listaQuestionarios.setAdapter(adapter);
        listaQuestionarios.setVisibility(View.VISIBLE);

    }
}
