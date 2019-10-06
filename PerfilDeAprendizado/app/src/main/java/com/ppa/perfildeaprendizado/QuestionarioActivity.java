package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionarioActivity extends AppCompatActivity {

    private TextView questao;
    private TextView posicao;
    private RadioButton radioDiscTot;
    private RadioButton radioDisc;
    private RadioButton radioConc;
    private RadioButton radioConcTot;
    private Button botaoVoltar;
    private Button botaoProx;

    public static int numQuestao = 0;
    private String[] questoes;
    protected static Integer[] respostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        this.questoes = getResources().getStringArray(R.array.perguntas);
        respostas = new Integer[this.questoes.length];
        numQuestao = 0;
        botaoVoltar = (Button) findViewById(R.id.buttonVoltar);
        botaoProx = (Button) findViewById(R.id.buttonProx);
        posicao = (TextView) findViewById(R.id.posicaoQuest);
        questao = (TextView) findViewById(R.id.pergunta);

        coletaRespostas();

        botaoProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desmarcarRadios();
                if(numQuestao < questoes.length-1) {
                    numQuestao++;
                    avancaQuestao();
                }else{
                    int contador = 0;
                    for(int i = 0; i < questoes.length; i++){
                        if(respostas[i] != null){
                            contador++;
                        } else {
                            break;
                        }
                    }
                    if(contador == questoes.length){
                        terminaQuestionario();
                    }else{
                        numQuestao = contador;
                        avancaQuestao();
                    }
                }
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desmarcarRadios();
                if(numQuestao > 0){
                    numQuestao--;
                    avancaQuestao();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        avancaQuestao();
    }

    public String[] getQuestoes(){
        return this.questoes;
    }

    public void avancaQuestao(){
        desmarcarRadios();
        questao.setText(questoes[numQuestao]);
        String pos = (numQuestao+1)+"/"+questoes.length;
        posicao.setText(pos);
        mantemMarcado();
    }

    public void terminaQuestionario(){
        Intent intent = new Intent(QuestionarioActivity.this, FimQuestionarioActivity.class);
        startActivity(intent);
    }

    public void coletaRespostas(){
        this.radioDiscTot = findViewById(R.id.radioDiscTot);
        this.radioDisc = findViewById(R.id.radioDisc);
        this.radioConc = findViewById(R.id.radioConc);
        this.radioConcTot = findViewById(R.id.radioConcTot);

        radioDiscTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 1;
            }
        });

        radioDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 2;
            }
        });

        radioConc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 3;
            }
        });

        radioConcTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 4;
            }
        });
    }

    public void mantemMarcado(){
        if(respostas[numQuestao] != null){
            if(respostas[numQuestao] == 1){
                radioDiscTot.setChecked(true);
            }
            else if(respostas[numQuestao] == 2){
                radioDisc.setChecked(true);
            }
            else if(respostas[numQuestao] == 3){
                radioConc.setChecked(true);
            }
            else if(respostas[numQuestao] == 4) {
                radioConcTot.setChecked(true);
            }
        }else {
            desmarcarRadios();
        }
    }

    public void desmarcarRadios(){
        radioConcTot.setChecked(false);
        radioConc.setChecked(false);
        radioDisc.setChecked(false);
        radioDiscTot.setChecked(false);
    }
}
