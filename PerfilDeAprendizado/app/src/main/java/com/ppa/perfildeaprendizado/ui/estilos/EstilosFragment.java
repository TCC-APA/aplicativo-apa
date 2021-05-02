package com.ppa.perfildeaprendizado.ui.estilos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.ppa.perfildeaprendizado.QuestionarioActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EstilosFragment extends Fragment {

    private EstilosViewModel estilosViewModel;

    private TextView textoEstilo;
    private TextView textoCaracteristicas;
    private TextView textoAprendizadoEstilo;
    private Button botaoVerMais;
    private static final float MAX = 50, MIN = 0f;
    private static final int NumEstilos = 4;
    private RadarChart radarChart;

    private Aluno aluno = new Aluno();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        estilosViewModel = ViewModelProviders.of(this).get(EstilosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_estilos, container, false);

        textoEstilo = root.findViewById(R.id.text_estilo);
        textoCaracteristicas = root.findViewById(R.id.text_caracteristicas);
        textoAprendizadoEstilo = root.findViewById(R.id.text_aprendizado_estilo);
        radarChart = root.findViewById(R.id.radarChart);

        aluno = (Aluno) getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        if(aluno != null) {
            if (aluno.temPerfis()) {
                mostrarResultadosLogin();
            } else {
                mostrarResultadosQuestionario();
            }
        }



//        estilosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    public void fazerGrafico(){

        radarChart.setBackgroundColor(Color.WHITE);
        radarChart.getDescription().setEnabled(false);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.BLUE);
        radarChart.setWebAlpha(100);


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
        yAxis.setAxisMaximum(MAX);

        radarChart.setData(getData());
        radarChart.invalidate();


       /* RadarDataSet radarDataSet = new RadarDataSet(getData(), "");
        RadarData radarData = new RadarData(radarDataSet);
        radarData.setDrawValues(false);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));
        radarChart.getYAxis().setEnabled(false);
        radarChart.getYAxis().setAxisMaximum(100f);

        radarChart.setData(radarData);
        radarChart.getLegend().setEnabled(false);
        radarChart.getDescription().setEnabled(false);

        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(18f);
        radarDataSet.setFormLineWidth(3f);
        */
    }

    public void mostrarResultadosLogin(){
        fazerGrafico();
    }

    private RadarData getData(){
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(aluno.getPerfilAtivo()));
        entries.add(new RadarEntry(aluno.getPerfilReflexivo()));
        entries.add(new RadarEntry(aluno.getPerfilTeorico()));
        entries.add(new RadarEntry(aluno.getPerfilPragmatico()));

        RadarDataSet set = new RadarDataSet(entries, aluno.getNome());
        set.setColor(Color.rgb(103, 110, 129));
        set.setFillColor(Color.rgb(103, 110, 129));
        set.setDrawFilled(true);
        set.setFillAlpha(180);
        set.setLineWidth(2f);
        set.setDrawHighlightCircleEnabled(true);
        set.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        return data;
    }

    private String[] getLabels(){
        return new String[] { getString(R.string.ativo), getString(R.string.reflexivo), getString(R.string.teorico), getString(R.string.pragmático) };

    }

    public void mostrarResultadosQuestionario(){
        Integer[] respostas = QuestionarioActivity.respostas;

        if(respostas.length == 40) {
            int ativo = 0;
            int reflexivo = 0;
            int teorico = 0;
            int pragmatico = 0;

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

            aluno.setPerfilAtivo(ativo);
            aluno.setPerfilReflexivo(reflexivo);
            aluno.setPerfilTeorico(teorico);
            aluno.setPerfilPragmatico(pragmatico);

            fazerGrafico();

            try {
                String b = new InserirAlunoTask(aluno).execute().get();
                if (b.equals("true")) {
                    Toast.makeText(this.getActivity(), "Perfil cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this.getActivity(), "Erro ao cadastrar perfil", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
