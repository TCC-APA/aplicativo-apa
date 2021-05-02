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

import com.ppa.perfildeaprendizado.data.model.Aluno;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionarioActivity extends AppCompatActivity {

    private TextView questao;
    private TextView posicao;
    private RadioGroup opcoesRadio;
    private RadioButton radioDiscTot;
    private RadioButton radioDisc;
    private RadioButton radioConc;
    private RadioButton radioConcTot;
    private Button botaoVoltar;
    private Button botaoProx;
    private Button botaoEnviarResp;

    public static int numQuestao = 0;
    private String[] questoes;
    public static Integer[] respostas;

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
        opcoesRadio = (RadioGroup) findViewById(R.id.radioQuestionario);
        botaoEnviarResp = (Button) findViewById(R.id.enviar_button);

        desativarBotaoEnviar();

        coletaRespostas();

        botaoProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numQuestao == questoes.length-1){
                    numQuestao = 0;
                    questao.setText(questoes[numQuestao]);
                    String pos = (numQuestao+1)+"/"+questoes.length;
                    posicao.setText(pos);
                } else {
                    numQuestao++;
                    questao.setText(questoes[numQuestao]);
                    String pos = (numQuestao+1)+"/"+questoes.length;
                    posicao.setText(pos);
                }
                mantemMarcado();

            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numQuestao > 0){
                    numQuestao--;
                    questao.setText(questoes[numQuestao]);
                    String pos = (numQuestao+1)+"/"+questoes.length;
                    posicao.setText(pos);


                } else {
                    numQuestao = questoes.length-1;
                    questao.setText(questoes[numQuestao]);
                    String pos = (numQuestao+1)+"/"+questoes.length;
                    posicao.setText(pos);
                }
                mantemMarcado();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        desativarBotaoEnviar();
        questao.setText(questoes[numQuestao]);
        String pos = (numQuestao+1)+"/"+questoes.length;
        posicao.setText(pos);
        mantemMarcado();
    }

    public String[] getQuestoes(){
        return this.questoes;

    }

    public void retornaQuestao(){

    }

    public void avancaQuestao(){
        int contador = 0;
        int primeiraNaoRespondidaAnterior = -1;
        for (int i = 0; i < questoes.length; i++) {

            if (respostas[i] != null) { //pode achar questoes anteriores nao respondidas e nao acrescentar o contador
                contador++;

            } else {
                if(i > numQuestao){
                    break;

                } else {// depois de rodar todas as perguntas, existe uma nao respondida antes da atual
                    contador++; //resolver o problema do "pode achar questoes anteriores nao respondidas e nao acrescentar o contador"
                    if(primeiraNaoRespondidaAnterior == -1){
                        primeiraNaoRespondidaAnterior = i;

                    }

                }
            }
        }
        if(contador == questoes.length){
            if(primeiraNaoRespondidaAnterior == -1){
                ativarBotaoEnviar();

            } else{
                numQuestao = primeiraNaoRespondidaAnterior;
                questao.setText(questoes[numQuestao]);
                String pos = (numQuestao+1)+"/"+questoes.length;
                posicao.setText(pos);
            }
        } else {
            numQuestao = contador;
            questao.setText(questoes[numQuestao]);
            String pos = (numQuestao+1)+"/"+questoes.length;
            posicao.setText(pos);
        }
        String apagar = "";
        for(int i = 0; i < respostas.length; i++){
            if(respostas[i] != null){
                apagar += respostas[i] + ", ";
            } else {
                apagar += "0, ";
            }
        }
        System.out.println(apagar);
        mantemMarcado();

    }

    public void terminaQuestionario(){
        Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        Intent intent = new Intent(QuestionarioActivity.this, ResultadoActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
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
                avancaQuestao();
            }
        });

        radioDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 2;
                avancaQuestao();

            }
        });

        radioConc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 3;
                avancaQuestao();

            }
        });

        radioConcTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 4;
                avancaQuestao();

            }
        });
    }

    public void mantemMarcado(){
        System.out.println("mantemMarcado: "+respostas[numQuestao]);
        this.opcoesRadio.clearCheck();
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
        }
    }

    protected void desativarBotaoEnviar(){
        this.botaoEnviarResp.setEnabled(false);
        this.botaoEnviarResp.setBackgroundColor(getResources().getColor(R.color.disabled_button));
    }

    protected void ativarBotaoEnviar(){
        this.botaoEnviarResp.setEnabled(true);
        this.botaoEnviarResp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        this.botaoEnviarResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminaQuestionario();
            }
        });
    }

}
