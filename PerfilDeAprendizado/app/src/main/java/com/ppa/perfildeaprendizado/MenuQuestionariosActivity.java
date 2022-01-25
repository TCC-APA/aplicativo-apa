package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ppa.perfildeaprendizado.controller.MenuController;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuController.setupMenu(menu, getMenuInflater());
        MenuItem itemInicio = menu.findItem(R.id.navigation_inicio);
        itemInicio.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_sobre:
                MenuController.sobreAction(this, aluno);
                return true;
            case R.id.navigation_editar_perfil:
                MenuController.editarPerfilAction(this, aluno);
                return true;
            default:
                MenuController.sairAction(this);
                return true;
        }
    }

    public void goToQuestionario(Questionario questionario){
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
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
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
