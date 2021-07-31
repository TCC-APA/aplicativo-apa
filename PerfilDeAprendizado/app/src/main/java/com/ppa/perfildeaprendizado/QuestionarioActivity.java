package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.PerfilRespostas;
import com.ppa.perfildeaprendizado.data.model.Questao;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.InserirPontuacaoAlunoTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
    private List<Questao> questoes;
    public static Integer[] respostas;
    private Questionario questionario;
    private PerfilRespostas perfilRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        this.questionario = MenuQuestionariosActivity.questionarioEscolhido;

//        this.questionario = (Questionario) getIntent().getSerializableExtra(Questionario.class.getSimpleName());
        if(questionario != null) {
            this.questoes = questionario.getQuestoes();
            if(questoes != null && !questoes.isEmpty()) {
                respostas = new Integer[this.questoes.size()];

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

                        if(numQuestao == questoes.size()-1){
                            numQuestao = 0;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao+1)+"/"+questoes.size();
                            posicao.setText(pos);
                        } else {
                            numQuestao++;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao+1)+"/"+questoes.size();
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
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao+1)+"/"+questoes.size();
                            posicao.setText(pos);
                        } else {
                            numQuestao = questoes.size()-1;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao+1)+"/"+questoes.size();
                            posicao.setText(pos);
                        }
                        mantemMarcado();

                    }
                });
            }
        }
//        this.questoes = getResources().getStringArray(R.array.perguntas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(questoes != null) {
            desativarBotaoEnviar();
            questao.setText(questoes.get(numQuestao).getTexto());
            String pos = (numQuestao + 1) + "/" + questoes.size();
            posicao.setText(pos);
            mantemMarcado();
        }
    }

    public List<Questao> getQuestoes(){
        return this.questoes;

    }

    public void retornaQuestao(){

    }

    public void avancaQuestao() {
        int contador = 0;
        int primeiraNaoRespondidaAnterior = -1;
        if (questoes != null) {
            for (int i = 0; i < questoes.size(); i++) {

                if (respostas[i] != null) { //pode achar questoes anteriores nao respondidas e nao acrescentar o contador
                    contador++;

                } else {
                    if (i > numQuestao) {
                        break;

                    } else {// depois de rodar todas as perguntas, existe uma nao respondida antes da atual
                        contador++; //resolver o problema do "pode achar questoes anteriores nao respondidas e nao acrescentar o contador"
                        if (primeiraNaoRespondidaAnterior == -1) {
                            primeiraNaoRespondidaAnterior = i;

                        }

                    }
                }
            }
            if (contador == questoes.size()) {
                if (primeiraNaoRespondidaAnterior == -1) {
                    ativarBotaoEnviar();

                } else {
                    numQuestao = primeiraNaoRespondidaAnterior;
                    questao.setText(questoes.get(numQuestao).getTexto());
                    String pos = (numQuestao + 1) + "/" + questoes.size();
                    posicao.setText(pos);
                }
            } else {
                numQuestao = contador;
                questao.setText(questoes.get(numQuestao).getTexto());
                String pos = (numQuestao + 1) + "/" + questoes.size();
                posicao.setText(pos);
            }
            String apagar = "";
            for (int i = 0; i < respostas.length; i++) {
                if (respostas[i] != null) {
                    apagar += respostas[i] + ", ";
                } else {
                    apagar += "0, ";
                }
            }
            System.out.println(apagar);
            mantemMarcado();
        }

    }

    public void terminaQuestionario(){
        Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        inserirPontuacaoPerfil(aluno);
        Intent intent = new Intent(QuestionarioActivity.this, ResultadoActivity.class);
        if(perfilRespostas != null){
            intent.putExtra(PerfilRespostas.class.getSimpleName(), perfilRespostas);
        }
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
    }

    public void inserirPontuacaoPerfil(Aluno aluno){

        perfilRespostas = new PerfilRespostas();
        Map<String, Long> estilosPontuacao = new HashMap<>();

        if(respostas.length == 40) {
            Long ativo = 0L;
            Long reflexivo = 0L;
            Long teorico = 0L;
            Long pragmatico = 0L;

            ativo += respostas[2-1];
            ativo += respostas[7-1];
            ativo += respostas[12-1];
            ativo += respostas[15-1];
            ativo += respostas[16-1];
            ativo += respostas[19-1];
            ativo += respostas[22-1];
            ativo += respostas[25-1];
            ativo += respostas[34-1];
            ativo += respostas[39-1];

            estilosPontuacao.put("51",ativo);

            reflexivo += respostas[4-1];
            reflexivo += respostas[13-1];
            reflexivo += respostas[14-1];
            reflexivo += respostas[18-1];
            reflexivo += respostas[20-1];
            reflexivo += respostas[23-1];
            reflexivo += respostas[27-1];
            reflexivo += respostas[29-1];
            reflexivo += respostas[32-1];
            reflexivo += respostas[40-1];

            estilosPontuacao.put("50",reflexivo);

            teorico += respostas[1-1];
            teorico += respostas[5-1];
            teorico += respostas[10-1];
            teorico += respostas[11-1];
            teorico += respostas[21-1];
            teorico += respostas[24-1];
            teorico += respostas[30-1];
            teorico += respostas[31-1];
            teorico += respostas[33-1];
            teorico += respostas[36-1];

            estilosPontuacao.put("49",teorico);

            pragmatico += respostas[3-1];
            pragmatico += respostas[6-1];
            pragmatico += respostas[8-1];
            pragmatico += respostas[9-1];
            pragmatico += respostas[17-1];
            pragmatico += respostas[26-1];
            pragmatico += respostas[28-1];
            pragmatico += respostas[35-1];
            pragmatico += respostas[37-1];
            pragmatico += respostas[38-1];

            estilosPontuacao.put("52",pragmatico);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            perfilRespostas.setMatriculaAluno(aluno.getMatricula());
            perfilRespostas.setDataRealizado(formatter.format(date));
            perfilRespostas.setIdQuestionario(48L);
            perfilRespostas.setPontuacaoPorEstilo(estilosPontuacao);

//            aluno.setPerfilAtivo(ativo);
//            aluno.setPerfilReflexivo(reflexivo);
//            aluno.setPerfilTeorico(teorico);
//            aluno.setPerfilPragmatico(pragmatico);

//            fazerGrafico();

            try {
                String b = new InserirPontuacaoAlunoTask(perfilRespostas).execute().get();
                if (b.equals("true")) {
                    Toast.makeText(this, "Pontuação cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar pontuação", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
