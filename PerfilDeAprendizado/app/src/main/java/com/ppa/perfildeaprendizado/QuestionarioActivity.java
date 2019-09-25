package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class QuestionarioActivity extends AppCompatActivity {

    public static boolean refazer = false;
    private TextView questao;
    private TextView posicao;
    private static SeekBar progressBar;
    public static int numQuestao = 0;
    private RadioButton radioDiscTot;
    private RadioButton radioDisc;
    private RadioButton radioConc;
    private RadioButton radioConcTot;
    private String[] questoes;
    protected static Integer[] respostas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        this.questoes = getResources().getStringArray(R.array.perguntas);
        respostas = new Integer[this.questoes.length];
        numQuestao = 0;
        this.radioDiscTot = findViewById(R.id.radioDiscTot);
        this.radioDisc = findViewById(R.id.radioDisc);
        this.radioConc = findViewById(R.id.radioConc);
        this.radioConcTot = findViewById(R.id.radioConcTot);

        radioDiscTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioDiscTot.setChecked(false);
                System.out.println("numQuestao:"+numQuestao);
                respostas[numQuestao] = 1;
                while(respostas.length > numQuestao && respostas[numQuestao] != null){
                    numQuestao++;
                }
                System.out.println(respostas);
                avancaPergunta();
            }
        });

        radioDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioDisc.setChecked(false);
                System.out.println("numQuestao:"+numQuestao);
                respostas[numQuestao] = 2;
                while(respostas.length > numQuestao && respostas[numQuestao] != null){
                    numQuestao++;
                }
                System.out.println(respostas);
                avancaPergunta();
            }
        });

        radioConc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioConc.setChecked(false);
                System.out.println("numQuestao:"+numQuestao);
                respostas[numQuestao] = 3;
                while(respostas.length > numQuestao && respostas[numQuestao] != null){
                    numQuestao++;
                }
                System.out.println(respostas);
                avancaPergunta();

            }
        });

        radioConcTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioConcTot.setChecked(false);
                System.out.println("numQuestao:"+numQuestao);
                respostas[numQuestao] = 4;
                while(respostas.length > numQuestao && respostas[numQuestao] != null){
                    numQuestao++;
                }
                //System.out.println(respostas);
                avancaPergunta();
            }
        });

        progressBar = (SeekBar) findViewById(R.id.progressBarQuest);
        posicao = (TextView) findViewById(R.id.posicaoQuest);
        progressBar.setMax(questoes.length - 1);
        progressBar.setProgress(numQuestao);
        posicao.setText((progressBar.getProgress() + 1) + "/" + questoes.length);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                questao = (TextView) findViewById(R.id.pergunta);
                numQuestao = progress;
                questao.setText(questoes[numQuestao]);
                posicao.setText((progressBar.getProgress() + 1) + "/" + questoes.length);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        avancaPergunta();
    }

    public String[] getQuestoes(){
        return this.questoes;
    }

    public void avancaPergunta(){

        if(this.refazer == true){
            this.numQuestao = 0;
            this.refazer = false;
            System.out.println("NUMERO QUESTAO="+this.numQuestao);
        }
        Integer count = 0;
        for(int i = 0; i < questoes.length; i++){
            if(respostas[i] != null){
                count++;
            }
        }
        System.out.println("progress: "+count);
        progressBar.setProgress(count);

        if(this.numQuestao < this.questoes.length){
            questao = (TextView) findViewById(R.id.pergunta);
            questao.setText(questoes[numQuestao]);

        } else {
            if(count != questoes.length){
                int contador = 0;
                for(int i = 0; i < questoes.length; i++){
                    if(respostas[i] != null){
                        contador++;
                    } else {
                        break;
                    }
                }
                numQuestao = contador;
            } else {
                this.numQuestao = 0;
                terminaQuestionario();
            }
        }

    }

    public void terminaQuestionario(){
        Log.d("Size respostas final:", String.valueOf(respostas.length));
        for(Integer resp: respostas){
            Log.d("respostas final: ", String.valueOf(resp));
        }
        Intent intent = new Intent(QuestionarioActivity.this, FimQuestionarioActivity.class);
        startActivity(intent);
    }


}
