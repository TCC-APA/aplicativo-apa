package com.ppa.perfildeaprendizado.ui.estilos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.ppa.perfildeaprendizado.DetalhesEstilosActivity;
import com.ppa.perfildeaprendizado.R;
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

public class EstilosFragment extends Fragment {

    private EstilosViewModel estilosViewModel;

    private TextView textoEstilo;
    private TextView textoCaracteristicas;
    //private TextView textoAprendizadoEstilo;
    private Button botaoVerMais;
    private static final float MIN = 0f;
    private float max;
    private RadarChart radarChart;

    private Aluno aluno;
    private Questionario questionario;
    private PerfilAluno perfilAluno;
    private List<RadarEntry> radarEntries = new ArrayList<>();
    private DetalhesEstilosVO detalhesEstilosVO;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        estilosViewModel = ViewModelProviders.of(this).get(EstilosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_estilos, container, false);
        max = 0f;

        textoEstilo = root.findViewById(R.id.text_estilo);
        textoCaracteristicas = root.findViewById(R.id.text_caracteristicas);
        //textoAprendizadoEstilo = root.findViewById(R.id.text_aprendizado_estilo);
        radarChart = root.findViewById(R.id.radarChart);
        botaoVerMais = root.findViewById(R.id.button_ver_mais);
        textoEstilo.setText("");
        textoCaracteristicas.setText("");
        //textoAprendizadoEstilo.setText("");

        aluno = (Aluno) getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        questionario = (Questionario) getActivity().getIntent().getSerializableExtra(Questionario.class.getSimpleName());

        try {
            perfilAluno = new BuscarPerfilAlunoTask(aluno.getMatricula(), 48L).execute().get();
            if(perfilAluno != null){
                preencherTextosPerfilPredominante();
                fazerGrafico();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        botaoVerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetalhesEstilosActivity.class);
                intent.putExtra(DetalhesEstilosVO.class.getSimpleName(), detalhesEstilosVO);
                intent.putExtra(Aluno.class.getSimpleName(), aluno);
                intent.putExtra(PerfilAluno.class.getSimpleName(), perfilAluno);
                startActivity(intent);
            }
        });

        return root;
    }

    public void fazerGrafico(){
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
        List<Estilo> estilos = perfilAluno.getEstilos();

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

        divideEstilosByPredominancia(estilosOrdenadosByPredominancia, estilosPredominantes, estilosNaoPredominantes, estilos.size());

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

    private void getEstilosPredominantes(){
        if (perfilAluno.getPontuacaoPorEstilo() != null && !perfilAluno.getPontuacaoPorEstilo().isEmpty()) {
            List<Estilo> estilos = perfilAluno.getEstilos();

            Long pontuacaoAux;
            Long maiorPontuacao = 0L;
            Estilo estiloPredominante = null;

            for (int i = 0; i <= perfilAluno.getPontuacaoPorEstilo().size()-1; i++) {
                pontuacaoAux = perfilAluno.getPontuacaoPorEstilo().get(estilos.get(i).getId().toString());
                if(pontuacaoAux > maiorPontuacao) {
                    maiorPontuacao = pontuacaoAux;
                    estiloPredominante = estilos.get(i);
                }
            }
            if(estiloPredominante != null) {
                textoEstilo.setText(estiloPredominante.getNome());
                textoCaracteristicas.setText(estiloPredominante.getCaracteristicas());
//                textoAprendizadoEstilo.setText(estiloPredominante.getSugestoes());
            }
        }
    }
}
