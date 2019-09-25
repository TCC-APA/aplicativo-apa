package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ProgressBar;

public class ResultadoActivity extends AppCompatActivity {

    private ProgressBar ativoBar;
    private ProgressBar reflexivoBar;
    private ProgressBar teoricoBar;
    private ProgressBar pragmaticoBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ativoBar = findViewById(R.id.ativoBar);
        reflexivoBar = findViewById(R.id.reflexivoBar);
        teoricoBar = findViewById(R.id.teoricoBar);
        pragmaticoBar = findViewById(R.id.pragmaticoBar);

        Integer[] respostas = QuestionarioActivity.respostas;
        int ativo = 0;
        int reflexivo = 0;
        int teorico = 0;
        int pragmatico = 0;
        for(int i = 0; i < 20; i++){
            ativo += respostas[i].intValue();
        }
        for(int i = 20; i < 40; i++){
            reflexivo += respostas[i].intValue();
        }
        for(int i = 40; i < 60; i++){
            teorico += respostas[i].intValue();
        }
        for(int i = 60; i < 80; i++){
            pragmatico += respostas[i].intValue();
        }

        ativoBar.setMax(79);
        reflexivoBar.setMax(79);
        teoricoBar.setMax(79);
        pragmaticoBar.setMax(79);

        ativoBar.setProgress(ativo);
        reflexivoBar.setProgress(reflexivo);
        teoricoBar.setProgress(teorico);
        pragmaticoBar.setProgress(pragmatico);
    }
}
