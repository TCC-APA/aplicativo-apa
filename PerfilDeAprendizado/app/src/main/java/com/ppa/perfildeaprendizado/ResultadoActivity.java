package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;
import com.ppa.perfildeaprendizado.ui.login.LoginActivity;

import java.util.concurrent.ExecutionException;

public class ResultadoActivity extends AppCompatActivity {

    private ProgressBar ativoBar;
    private ProgressBar reflexivoBar;
    private ProgressBar teoricoBar;
    private ProgressBar pragmaticoBar;

    private Aluno aluno = new Aluno();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ativoBar = findViewById(R.id.ativoBar);
        reflexivoBar = findViewById(R.id.reflexivoBar);
        teoricoBar = findViewById(R.id.teoricoBar);
        pragmaticoBar = findViewById(R.id.pragmaticoBar);

        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        if(aluno != null) {
            if (aluno.temPerfis()) {
                mostrarResultadosLogin();
            } else {
                mostrarResultadosQuestionario();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResultadoActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void mostrarResultadosLogin(){
        ativoBar.setMax(79);
        reflexivoBar.setMax(79);
        teoricoBar.setMax(79);
        pragmaticoBar.setMax(79);

        ativoBar.setProgress(aluno.getPerfilAtivo());
        reflexivoBar.setProgress(aluno.getPerfilReflexivo());
        teoricoBar.setProgress(aluno.getPerfilTeorico());
        pragmaticoBar.setProgress(aluno.getPerfilPragmatico());
    }

    public void mostrarResultadosQuestionario(){
        Integer[] respostas = QuestionarioActivity.respostas;
        int ativo = 0;
        int reflexivo = 0;
        int teorico = 0;
        int pragmatico = 0;
        for(int i = 0; i < 20; i++){
            ativo += respostas[i];
        }
        for(int i = 20; i < 40; i++){
            reflexivo += respostas[i];
        }
        for(int i = 40; i < 60; i++){
            teorico += respostas[i];
        }
        for(int i = 60; i < 80; i++){
            pragmatico += respostas[i];
        }

        ativoBar.setMax(79);
        reflexivoBar.setMax(79);
        teoricoBar.setMax(79);
        pragmaticoBar.setMax(79);

        ativoBar.setProgress(ativo);
        reflexivoBar.setProgress(reflexivo);
        teoricoBar.setProgress(teorico);
        pragmaticoBar.setProgress(pragmatico);

        aluno.setPerfilAtivo(ativo);
        aluno.setPerfilReflexivo(reflexivo);
        aluno.setPerfilTeorico(teorico);
        aluno.setPerfilPragmatico(pragmatico);

        try {
            String b = new InserirAlunoTask(aluno).execute().get();
            if(b.equals("true")){
                Toast.makeText(this, "Perfil cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            }else{
               Toast.makeText(this, "Erro ao cadastrar perfil", Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
