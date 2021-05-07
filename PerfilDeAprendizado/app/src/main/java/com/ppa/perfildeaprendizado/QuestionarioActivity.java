package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ppa.perfildeaprendizado.data.model.Aluno;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionarioActivity extends AppCompatActivity {

    private TextView questao;
    private TextView posicao;
    private RadioGroup opcoesRadio;
    private RadioButton radioNunca;
    private RadioButton radioAlgumasVezes;
    private RadioButton radioMuitasVezes;
    private RadioButton radioQuaseSempre;
    private RadioButton radioSempre;
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
        this.radioNunca = findViewById(R.id.radioNunca);
        this.radioAlgumasVezes = findViewById(R.id.radioAlgVezes);
        this.radioMuitasVezes = findViewById(R.id.radioMuitasVezes);
        this.radioQuaseSempre = findViewById(R.id.radioQseSempre);
        this.radioSempre = findViewById(R.id.radioSempre);

        radioNunca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 1;
                avancaQuestao();
            }
        });

        radioAlgumasVezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 2;
                avancaQuestao();

            }
        });

        radioMuitasVezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 3;
                avancaQuestao();

            }
        });

        radioQuaseSempre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 4;
                avancaQuestao();

            }
        });

        radioSempre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostas[numQuestao] = 5;
                avancaQuestao();

            }
        });
    }

    public void mantemMarcado(){
        System.out.println("mantemMarcado: "+respostas[numQuestao]);
        this.opcoesRadio.clearCheck();
        if(respostas[numQuestao] != null){
            if(respostas[numQuestao] == 1){
                radioNunca.setChecked(true);

            }
            else if(respostas[numQuestao] == 2){
                radioAlgumasVezes.setChecked(true);

            }
            else if(respostas[numQuestao] == 3){
                radioMuitasVezes.setChecked(true);

            }
            else if(respostas[numQuestao] == 4) {
                radioQuaseSempre.setChecked(true);

            }
            else if(respostas[numQuestao] == 5) {
                radioSempre.setChecked(true);

            }
        }
    }

    protected void desativarBotaoEnviar(){
        this.botaoEnviarResp.setEnabled(false);
        this.botaoEnviarResp.setBackground(getResources().getDrawable(R.drawable.roundedbuttondisabled));
    }

    protected void ativarBotaoEnviar(){
        this.botaoEnviarResp.setEnabled(true);
        this.botaoEnviarResp.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
        this.botaoEnviarResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminaQuestionario();
            }
        });
    }

}
