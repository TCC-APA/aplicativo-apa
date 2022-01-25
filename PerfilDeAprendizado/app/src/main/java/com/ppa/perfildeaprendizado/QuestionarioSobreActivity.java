package com.ppa.perfildeaprendizado;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ppa.perfildeaprendizado.controller.MenuController;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionarioSobreActivity extends AppCompatActivity {

    @BindView(R.id.nome_questionario)
    protected TextView nome;
    @BindView(R.id.sobre_questionario)
    protected TextView sobre;

    private Questionario questionario;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_questionario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        this.questionario = (Questionario) getIntent().getSerializableExtra(Questionario.class.getSimpleName());
        this.aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        nome.setText("Sobre o questionário: " + questionario.getNome());
        sobre.setText(questionario.getSobre());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuController.setupMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_editar_perfil:
                MenuController.editarPerfilAction(this, aluno);
                return true;
            case R.id.navigation_inicio:
                MenuController.inicioAction(this, aluno);
                return true;
            case R.id.navigation_sobre:
                MenuController.sobreAction(this, aluno);
                return true;
            case R.id.navigation_sair:
                MenuController.sairAction(this);
                return true;
            default:
                onBackPressed();
                return true;
        }
    }
}
