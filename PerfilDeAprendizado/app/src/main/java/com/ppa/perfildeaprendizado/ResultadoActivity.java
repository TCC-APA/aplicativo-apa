package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.ppa.perfildeaprendizado.controller.MenuController;
import com.ppa.perfildeaprendizado.data.DetalhesEstilosVO;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.data.model.RangePontuacaoClassificacao;
import com.ppa.perfildeaprendizado.task.BuscarPerfilAlunoTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView textoEstilo;
    private TextView textoCaracteristicas;
    private Button botaoVerMais;
    private TextView tituloGrafico;
    private ScrollView scrollEstilosAprendizagem;
    private RelativeLayout graficoRelative;
    private RelativeLayout verMaisRelative;
    private static final float MIN = 0f;
    private float max;
    private RadarChart radarChart;

    private Aluno aluno;
    private Questionario questionario;
    private PerfilAluno perfilAluno;
    private List<RadarEntry> radarEntries = new ArrayList<>();
    private DetalhesEstilosVO detalhesEstilosVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        questionario = (Questionario) getIntent().getSerializableExtra(Questionario.class.getSimpleName());
        setContentView(R.layout.activity_resultado);
        max = 0f;

        textoEstilo = findViewById(R.id.text_estilo);
        textoCaracteristicas = findViewById(R.id.text_caracteristicas);
        radarChart = findViewById(R.id.radarChart);
        botaoVerMais = findViewById(R.id.button_ver_mais);
        tituloGrafico = findViewById(R.id.titulo_grafico_container);
        scrollEstilosAprendizagem = findViewById(R.id.estilos_aprendizagem_scroll);
        graficoRelative = findViewById(R.id.grafico);
        verMaisRelative = findViewById(R.id.button_view);
        textoEstilo.setText("");
        textoCaracteristicas.setText("");

        try {
            perfilAluno = new BuscarPerfilAlunoTask(aluno.getMatricula(), questionario.getId()).execute().get();
            if(perfilAluno != null){
                preencherTextosPerfilPredominante();
                if(questionario.getTipoGrafico() != null){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(getString(R.string.titulo_grafico));
                    stringBuilder.append(" ");
                    stringBuilder.append(aluno.getNome().split(" ")[0]);
                    tituloGrafico.setText(stringBuilder.toString());
                    fazerGrafico();
                } else {
                    graficoRelative.setVisibility(View.GONE);
                    RelativeLayout.LayoutParams layoutParamsScroll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    LinearLayout.LayoutParams layoutParamsVerMais = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsScroll.setMargins(40, 40, 41, 0);
                    layoutParamsVerMais.setMargins(40, 0, 41, 40);
                    scrollEstilosAprendizagem.setLayoutParams(layoutParamsScroll);
                    verMaisRelative.setLayoutParams(layoutParamsVerMais);
                    
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        botaoVerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoActivity.this, DetalhesEstilosActivity.class);
                intent.putExtra(DetalhesEstilosVO.class.getSimpleName(), detalhesEstilosVO);
                intent.putExtra(Aluno.class.getSimpleName(), aluno);
                intent.putExtra(PerfilAluno.class.getSimpleName(), perfilAluno);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultadoActivity.this, MenuQuestionariosActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
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
                return super.onOptionsItemSelected(item);
        }
    }

    public void fazerGrafico(){
        switch (questionario.getTipoGrafico()){
            case 0: //Teia
                radarChart.setBackgroundColor(Color.WHITE);
                radarChart.getDescription().setEnabled(false);
                radarChart.setWebLineWidth(1f);
                radarChart.getLegend().setEnabled(false);
                radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
                radarChart.setWebAlpha(100);


                XAxis xAxis = radarChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));

                YAxis yAxis = radarChart.getYAxis();
                yAxis.setAxisMinimum(MIN);
                yAxis.setAxisMaximum(max);
                yAxis.setLabelCount(1, true);

                radarChart.setData(getData());
        }
    }

    private RadarData getData() {
        if(radarEntries != null && !radarEntries.isEmpty()) {
            RadarDataSet radarDataSet = new RadarDataSet(radarEntries, aluno.getNome());
            radarDataSet.setLineWidth(2f);
            radarDataSet.setValueTextColor(Color.BLACK);
            radarDataSet.setValueTextSize(14f);
            radarDataSet.setFillAlpha(50);
            radarDataSet.setColor(Color.rgb(103, 110, 129));
            radarDataSet.setFillColor(Color.rgb(103, 110, 129));
            radarDataSet.setDrawFilled(true);
            radarDataSet.setFillAlpha(180);
            radarDataSet.setLineWidth(2f);
            radarDataSet.setDrawHighlightCircleEnabled(true);
            radarDataSet.setDrawHighlightIndicators(false);

            ArrayList<IRadarDataSet> sets = new ArrayList<>();
            sets.add(radarDataSet);

            RadarData data = new RadarData(sets);
            data.setDrawValues(true);
            data.setValueTextColor(Color.BLACK);

            return data;
        }
        return null;
    }

    private String[] getLabels(){
        if (perfilAluno.getPontuacaoPorEstilo() != null && !perfilAluno.getPontuacaoPorEstilo().isEmpty()) {
            String[] labels = new String[perfilAluno.getEstilos().size()];
            List<Estilo> estilos = perfilAluno.getEstilos();

            Long pontuacaoAux;

            for (int i = 0; i <= perfilAluno.getPontuacaoPorEstilo().size() - 1; i++) {
                labels[i] = estilos.get(i).getNome();
                pontuacaoAux = perfilAluno.getPontuacaoPorEstilo().get(estilos.get(i).getId().toString());
                if(pontuacaoAux != null) {
                    radarEntries.add(new RadarEntry(pontuacaoAux));
                }
            }
            return labels;
        }
        return null;
    }

    public void preencherTextosPerfilPredominante(){
        Map<Integer, List<Estilo>> estilosOrdenadosByPredominancia = new HashMap<>();
        Map<Long, RangePontuacaoClassificacao> idEstilosRange = new HashMap<>();
        Integer rangesSize = 0;
        if (perfilAluno.getPontuacaoPorEstilo() != null && !perfilAluno.getPontuacaoPorEstilo().isEmpty()) {
            List<RangePontuacaoClassificacao> ranges = questionario.getRanges();

            if(ranges != null && !ranges.isEmpty()){
                for(String estiloIndex: questionario.getEstilosIndexados().keySet()){
                    Estilo estiloAtual = questionario.getEstilosIndexados().get(estiloIndex);
                    String estiloId = estiloAtual.getId().toString();
                    Long pontuacaoEstilo = perfilAluno.getPontuacaoPorEstilo() != null && perfilAluno.getPontuacaoPorEstilo().containsKey(estiloId) ?
                            perfilAluno.getPontuacaoPorEstilo().get(estiloId) : 0L;

                    List<RangePontuacaoClassificacao> rangesDoEstilo = new ArrayList<>();
                    for(RangePontuacaoClassificacao range: ranges){
                        if(range.getEstiloKey().equals(estiloIndex)){
                            rangesDoEstilo.add(range);
                            if(range.getMaxValue() > max)
                                max = (float) range.getMaxValue();
                        }
                    }
                    rangesSize = rangesDoEstilo.size();
                    for(int i = 0; i<rangesDoEstilo.size(); i++){
                        RangePontuacaoClassificacao rangeEstilo = rangesDoEstilo.get(i);
                        if(pontuacaoEstilo >= rangeEstilo.getMinValue() && pontuacaoEstilo <= rangeEstilo.getMaxValue()){
                            List<Estilo> listaEstiloForRange = new ArrayList<>();
                            if(estilosOrdenadosByPredominancia.containsKey(i) && estilosOrdenadosByPredominancia.get(i) != null)
                                listaEstiloForRange.addAll(estilosOrdenadosByPredominancia.get(i));

                            listaEstiloForRange.add(estiloAtual);
                            idEstilosRange.put(estiloAtual.getId(), rangeEstilo);
                            estilosOrdenadosByPredominancia.put(i, listaEstiloForRange);
                            break;
                        }
                    }
                }
            }
        }
        List<Estilo> estilosPredominantes = new ArrayList<>();
        List<Estilo> estilosNaoPredominantes = new ArrayList<>();

        divideEstilosByPredominancia(estilosOrdenadosByPredominancia, estilosPredominantes, estilosNaoPredominantes, rangesSize);

        DetalhesEstilosVO vo = new DetalhesEstilosVO();
        vo.setEstilosNaoPredominantes(estilosNaoPredominantes);
        vo.setEstilosPredominantes(estilosPredominantes);
        vo.setIdEstiloRange(idEstilosRange);

        StringBuilder strPredominantes = new StringBuilder();
        StringBuilder strCaracteristicas = new StringBuilder();
        for(Estilo e: estilosPredominantes){
            strPredominantes.append("\"" + e.getNome() + "\", " );
            strCaracteristicas.append(e.getNome() + ": " + e.getCaracteristicas() + "\n\n");
        }

        String textoPredominantes  = strPredominantes.substring(0, strPredominantes.length() - 2);
        String caracteristicas = strCaracteristicas.substring(0, strCaracteristicas.length() - 2);
        textoEstilo.setText(textoPredominantes);
        textoCaracteristicas.setText(caracteristicas);

        this.detalhesEstilosVO = vo;
    }

    private void divideEstilosByPredominancia(Map<Integer, List<Estilo>> estilosOrdenadosByPredominancia, List<Estilo> estilosPredominantes, List<Estilo> estilosNaoPredominantes, int estilosSize) {
        Boolean predominanteEncontrado = false;
        for(int i = estilosSize - 1; i >= 0; i--){
            if(!predominanteEncontrado){
                if(estilosOrdenadosByPredominancia.containsKey(i)){
                    estilosPredominantes.addAll(estilosOrdenadosByPredominancia.get(i));
                    predominanteEncontrado = true;
                }
            } else {
                if(estilosOrdenadosByPredominancia.containsKey(i)){
                    estilosNaoPredominantes.addAll(estilosOrdenadosByPredominancia.get(i));
                }
            }
        }
    }

}
