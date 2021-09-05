package com.ppa.perfildeaprendizado;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppa.perfildeaprendizado.controller.MenuController;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.PerfilRespostas;
import com.ppa.perfildeaprendizado.data.model.Questao;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.data.model.ValorAlternativa;
import com.ppa.perfildeaprendizado.task.InserirPontuacaoAlunoTask;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Integer alternativasSize = 0;
    public static int numQuestao = 0;
    private List<Questao> questoes;
    public static Integer[] respostas;
    private Questionario questionario;
    private Aluno aluno;
    private PerfilRespostas perfilRespostas;
    private List<ValorAlternativa> valoresAlternativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        this.questionario = (Questionario) getIntent().getSerializableExtra(Questionario.class.getSimpleName());
        this.aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        if (questionario != null) {
            this.questoes = questionario.getQuestoes();
            if (questoes != null && !questoes.isEmpty()) {
                respostas = new Integer[questoes.size()];

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

                        if (numQuestao == questoes.size() - 1) {
                            numQuestao = 0;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao + 1) + "/" + questoes.size();
                            posicao.setText(pos);
                        } else {
                            numQuestao++;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao + 1) + "/" + questoes.size();
                            posicao.setText(pos);
                        }
                        mantemMarcado();

                    }
                });

                botaoVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (numQuestao > 0) {
                            numQuestao--;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao + 1) + "/" + questoes.size();
                            posicao.setText(pos);
                        } else {
                            numQuestao = questoes.size() - 1;
                            questao.setText(questoes.get(numQuestao).getTexto());
                            String pos = (numQuestao + 1) + "/" + questoes.size();
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
        if (questoes != null) {
            desativarBotaoEnviar();
            questao.setText(questoes.get(numQuestao).getTexto());
            String pos = (numQuestao + 1) + "/" + questoes.size();
            posicao.setText(pos);
            mantemMarcado();
        }
    }

    public List<Questao> getQuestoes() {
        return this.questoes;

    }

    public void retornaQuestao() {

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

    public void terminaQuestionario() {
        Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        inserirPontuacaoPerfil(aluno);
        Intent intent = new Intent(QuestionarioActivity.this, ResultadoActivity.class);
        if (perfilRespostas != null) {
            intent.putExtra(PerfilRespostas.class.getSimpleName(), perfilRespostas);
        }
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
    }

    public void inserirPontuacaoPerfil(Aluno aluno) {

        perfilRespostas = new PerfilRespostas();
        Map<String, Long> estilosPontuacao = new HashMap<>();
        Map<String, Estilo> estilosIndexados = questionario.getEstilosIndexados();
        if (estilosIndexados != null && estilosIndexados.size() > 0) {
            for (String key : estilosIndexados.keySet()) {
                Estilo e = estilosIndexados.get(key);
                estilosPontuacao.put(e.getId().toString(), 0L);
            }

            if (respostas != null && respostas.length > 0) {
                for (int i = 0; i < questoes.size(); i++) {
                    Questao questao = questoes.get(i);
                    Integer respostaValue = respostas[i];
                    Estilo estilo = estilosIndexados.get(questao.getEstiloKey());

                    String estiloId = estilo.getId().toString();
                    estilosPontuacao.put(estiloId, estilosPontuacao.get(estiloId) + respostaValue);
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();

                perfilRespostas.setMatriculaAluno(aluno.getMatricula());
                perfilRespostas.setDataRealizado(formatter.format(date));
                perfilRespostas.setIdQuestionario(48L);
                perfilRespostas.setPontuacaoPorEstilo(estilosPontuacao);

                try {
                    String b = new InserirPontuacaoAlunoTask(perfilRespostas).execute().get();
                    Toast.makeText(this, b, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Ocorreu um erro inesperado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void coletaRespostas() {
        this.radioNunca = findViewById(R.id.radioNunca);
        this.radioAlgumasVezes = findViewById(R.id.radioAlgVezes);
        this.radioMuitasVezes = findViewById(R.id.radioMuitasVezes);
        this.radioQuaseSempre = findViewById(R.id.radioQseSempre);
        this.radioSempre = findViewById(R.id.radioSempre);
        if (this.questionario != null) {
            valoresAlternativas = this.questionario.getValoresAlternativas();
            Collections.sort(valoresAlternativas, new Comparator<ValorAlternativa>() {
                @Override
                public int compare(ValorAlternativa o1, ValorAlternativa o2) {
                    return o1.getValor().compareTo(o2.getValor());
                }
            });
            alternativasSize = valoresAlternativas.size();
            if (alternativasSize >= 1) {
                radioNunca.setVisibility(View.VISIBLE);
                radioNunca.setText(valoresAlternativas.get(0).getTextoAlternativa());
                radioNunca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        respostas[numQuestao] = valoresAlternativas.get(0).getValor();
                        avancaQuestao();
                    }
                });
            } else {
                radioNunca.setVisibility(View.GONE);
            }

            if (alternativasSize >= 2) {
                radioAlgumasVezes.setVisibility(View.VISIBLE);
                radioAlgumasVezes.setText(valoresAlternativas.get(1).getTextoAlternativa());
                radioAlgumasVezes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        respostas[numQuestao] = valoresAlternativas.get(1).getValor();
                        avancaQuestao();

                    }
                });
            } else {
                radioAlgumasVezes.setVisibility(View.GONE);
            }

            if (alternativasSize >= 3) {
                radioMuitasVezes.setVisibility(View.VISIBLE);
                radioMuitasVezes.setText(valoresAlternativas.get(2).getTextoAlternativa());
                radioMuitasVezes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        respostas[numQuestao] = valoresAlternativas.get(2).getValor();
                        avancaQuestao();

                    }
                });
            } else {
                radioMuitasVezes.setVisibility(View.GONE);
            }

            if (alternativasSize >= 4) {
                radioQuaseSempre.setVisibility(View.VISIBLE);
                radioQuaseSempre.setText(valoresAlternativas.get(3).getTextoAlternativa());
                radioQuaseSempre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        respostas[numQuestao] = valoresAlternativas.get(3).getValor();
                        avancaQuestao();

                    }
                });
            } else {
                radioQuaseSempre.setVisibility(View.GONE);
            }

            if (alternativasSize >= 5) {
                radioSempre.setVisibility(View.VISIBLE);
                radioSempre.setText(valoresAlternativas.get(4).getTextoAlternativa());
                radioSempre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        respostas[numQuestao] = valoresAlternativas.get(4).getValor();
                        avancaQuestao();

                    }
                });
            } else {
                radioSempre.setVisibility(View.GONE);
            }
        }

    }

    public void mantemMarcado() {
        System.out.println("mantemMarcado: " + respostas[numQuestao]);
        this.opcoesRadio.clearCheck();
        if (respostas[numQuestao] != null) {
            if(alternativasSize >= 1){
                if (respostas[numQuestao] == valoresAlternativas.get(0).getValor()) {
                    radioNunca.setChecked(true);
                }
            }
            if(alternativasSize >= 2){
                if (respostas[numQuestao] == valoresAlternativas.get(1).getValor()) {
                    radioAlgumasVezes.setChecked(true);
                }
            }
            if(alternativasSize >= 3){
                if (respostas[numQuestao] == valoresAlternativas.get(2).getValor()) {
                    radioMuitasVezes.setChecked(true);
                }
            }
            if(alternativasSize >= 4){
                if (respostas[numQuestao] == valoresAlternativas.get(3).getValor()) {
                    radioQuaseSempre.setChecked(true);
                }
            }
            if(alternativasSize >= 5){
                if (respostas[numQuestao] == valoresAlternativas.get(4).getValor()) {
                    radioSempre.setChecked(true);
                }
            }
        }
    }

    protected void desativarBotaoEnviar() {
        this.botaoEnviarResp.setEnabled(false);
        this.botaoEnviarResp.setBackground(getResources().getDrawable(R.drawable.roundedbuttondisabled));
    }

    protected void ativarBotaoEnviar() {
        this.botaoEnviarResp.setEnabled(true);
        this.botaoEnviarResp.setBackground(getResources().getDrawable(R.drawable.roundedbutton));
        this.botaoEnviarResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminaQuestionario();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuController.setupMenu(menu, getMenuInflater());
        MenuItem itemSobre = menu.findItem(R.id.navigation_sobre);
        MenuItem itemEditarPerfil = menu.findItem(R.id.navigation_editar_perfil);
        itemSobre.setVisible(false);
        itemEditarPerfil.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_inicio:
                MenuController.inicioAction(this, aluno);
                return true;
            case R.id.navigation_sair:
                MenuController.sairAction(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
