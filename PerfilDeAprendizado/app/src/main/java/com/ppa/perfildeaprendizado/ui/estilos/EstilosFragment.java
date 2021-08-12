package com.ppa.perfildeaprendizado.ui.estilos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.ppa.perfildeaprendizado.DetalhesEstilosActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.task.BuscarPerfilAlunoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EstilosFragment extends Fragment {

    private EstilosViewModel estilosViewModel;

    private TextView textoEstilo;
    private TextView textoCaracteristicas;
    private TextView textoAprendizadoEstilo;
    private Button botaoVerMais;
    private static final float MAX = 100f, MIN = 0f;
    private static final int NumEstilos = 4;
    private RadarChart radarChart;

    private Aluno aluno = new Aluno();
    private PerfilAluno perfilAluno;
    private List<RadarEntry> radarEntries = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        estilosViewModel = ViewModelProviders.of(this).get(EstilosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_estilos, container, false);

        textoEstilo = root.findViewById(R.id.text_estilo);
        textoCaracteristicas = root.findViewById(R.id.text_caracteristicas);
        textoAprendizadoEstilo = root.findViewById(R.id.text_aprendizado_estilo);
        radarChart = root.findViewById(R.id.radarChart);
        botaoVerMais = root.findViewById(R.id.button_ver_mais);
        textoEstilo.setText("");
        textoCaracteristicas.setText("");
        textoAprendizadoEstilo.setText("");

        aluno = (Aluno) getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        try {
            perfilAluno = new BuscarPerfilAlunoTask(aluno.getMatricula(), 48L).execute().get();
            if(perfilAluno != null){
                fazerGrafico(perfilAluno.getEstilos().size());
                preencherTextosPerfilPredominante();
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
                intent.putExtra(PerfilAluno.class.getSimpleName(), perfilAluno);
                startActivity(intent);
            }
        });

        return root;
    }

    public void fazerGrafico(int qtdEstilos){
       /* ArrayList<RadarEntry> radarEntryList = new ArrayList<>();
        radarEntryList.add(new RadarEntry( 30));
        radarEntryList.add(new RadarEntry( 45));
        radarEntryList.add(new RadarEntry( 60));
        radarEntryList.add(new RadarEntry( 73));

        RadarDataSet radarDataSet = new RadarDataSet(radarEntryList, "teste");
       // radarDataSet.setColor(Color.BLUE);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(14f);
        radarDataSet.setFillAlpha(50);
        //radarDataSet.setFillColor(Color.CYAN);

        radarDataSet.setColor(Color.rgb(103, 110, 129));
        radarDataSet.setFillColor(Color.rgb(103, 110, 129));
        radarDataSet.setDrawFilled(true);
        radarDataSet.setFillAlpha(180);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setDrawHighlightCircleEnabled(true);
        radarDataSet.setDrawHighlightIndicators(false);

        RadarData radarDate = new RadarData();
        radarDate.addDataSet(radarDataSet);

        String[] labels = {"label1", "label2", "label3", "label4"}; FUNCIONA*/



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
        yAxis.setAxisMaximum(MAX);
        yAxis.setLabelCount(1, true);

        radarChart.setData(getData());







        /*
        radarChart.setWebColor(Color.BLUE);
        radarChart.setWebAlpha(100);
        radarChart.getLegend().setEnabled(false);
        radarChart.setBackgroundColor(Color.WHITE);
        radarChart.getDescription().setEnabled(false);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.BLUE);
        radarChart.setWebAlpha(100);
        radarChart.getLegend().setEnabled(false);


        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(NumEstilos, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMaximum(MIN);
        yAxis.setAxisMinimum(MAX);

        radarChart.setData(getData());
        //radarChart.invalidate();*/
    }

    private RadarData getData() {

        if(radarEntries != null && !radarEntries.isEmpty()) {
            RadarDataSet radarDataSet = new RadarDataSet(radarEntries, aluno.getNome());

            /*set.setColor(Color.rgb(103, 110, 129));
            set.setFillColor(Color.rgb(103, 110, 129));
            set.setDrawFilled(true);
            set.setFillAlpha(180);
            set.setLineWidth(2f);
            set.setDrawHighlightCircleEnabled(true);
            set.setDrawHighlightIndicators(false);*/
            radarDataSet.setLineWidth(2f);
            radarDataSet.setValueTextColor(Color.BLACK);
            radarDataSet.setValueTextSize(14f);
            radarDataSet.setFillAlpha(50);
            //radarDataSet.setFillColor(Color.CYAN);

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
                textoAprendizadoEstilo.setText(estiloPredominante.getSugestoes());
            }
        }
    }
}
