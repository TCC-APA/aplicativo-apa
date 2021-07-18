package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.data.model.PerfilRespostas;
import com.ppa.perfildeaprendizado.requests.GsonRequest;
import com.ppa.perfildeaprendizado.task.BuscarPerfilAlunoTask;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ppa.perfildeaprendizado.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ResultadoActivity extends AppCompatActivity {

    private static final float MAX = 50, MIN = 0f;
    private static final int NumEstilos = 4;
    private RadarChart radarChart;

    private Aluno aluno = new Aluno();
    private PerfilAluno perfilAluno;
    private PerfilRespostas perfilRespostas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
//        radarChart = findViewById(R.id.radarChart);
//
//        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
//        perfilRespostas = (PerfilRespostas) getIntent().getSerializableExtra(PerfilRespostas.class.getSimpleName());
//        try {
//            perfilAluno = new BuscarPerfilAlunoTask(aluno.getMatricula(), 48L).execute().get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if(perfilAluno != null) {
//            mostrarResultados();
//        }
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_resultado, R.id.navigation_editar_perfil)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }

//    public void fazerGrafico(){
//
//        radarChart.setBackgroundColor(Color.WHITE);
//        radarChart.getDescription().setEnabled(false);
//        radarChart.setWebLineWidth(1f);
//        radarChart.setWebColor(Color.BLUE);
//        radarChart.setWebAlpha(100);
//
//
//        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
//        XAxis xAxis = radarChart.getXAxis();
//        xAxis.setTextSize(9f);
//        xAxis.setYOffset(0);
//        xAxis.setXOffset(0);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));
//
//        YAxis yAxis = radarChart.getYAxis();
//        yAxis.setLabelCount(NumEstilos, false);
//        yAxis.setTextSize(9f);
//        yAxis.setAxisMaximum(MIN);
//        yAxis.setAxisMaximum(MAX);
//
//        radarChart.setData(getData());
//        radarChart.invalidate();
//
//
//       /* RadarDataSet radarDataSet = new RadarDataSet(getData(), "");
//        RadarData radarData = new RadarData(radarDataSet);
//        radarData.setDrawValues(false);
//
//        XAxis xAxis = radarChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));
//        radarChart.getYAxis().setEnabled(false);
//        radarChart.getYAxis().setAxisMaximum(100f);
//
//        radarChart.setData(radarData);
//        radarChart.getLegend().setEnabled(false);
//        radarChart.getDescription().setEnabled(false);
//
//        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        radarDataSet.setValueTextColor(Color.BLACK);
//        radarDataSet.setValueTextSize(18f);
//        radarDataSet.setFormLineWidth(3f);
//        */
//    }
//
//    public void mostrarResultados(){
//        fazerGrafico();
//    }
//
//    private RadarData getData(){
//        List<RadarEntry> entries = new ArrayList<>();
//        for(Long pontuacaoEstilo : perfilAluno.getPontuacaoPorEstilo().values()){
//            entries.add(new RadarEntry(pontuacaoEstilo));
//        }
////        entries.add(new RadarEntry(aluno.getPerfilAtivo()));
////        entries.add(new RadarEntry(aluno.getPerfilReflexivo()));
////        entries.add(new RadarEntry(aluno.getPerfilTeorico()));
////        entries.add(new RadarEntry(aluno.getPerfilPragmatico()));
//
//        RadarDataSet set = new RadarDataSet(entries, aluno.getNome());
//        set.setColor(Color.rgb(103, 110, 129));
//        set.setFillColor(Color.rgb(103, 110, 129));
//        set.setDrawFilled(true);
//        set.setFillAlpha(180);
//        set.setLineWidth(2f);
//        set.setDrawHighlightCircleEnabled(true);
//        set.setDrawHighlightIndicators(false);
//
//        ArrayList<IRadarDataSet> sets = new ArrayList<>();
//        sets.add(set);
//
//        RadarData data = new RadarData(sets);
//        data.setValueTextSize(8f);
//        data.setDrawValues(false);
//        data.setValueTextColor(Color.WHITE);
//
//        return data;
//    }
//
//    private String[] getLabels(){
//        String[] labels = new String[perfilAluno.getEstilos().size()];
//        Estilo[] estilos = (Estilo[]) perfilAluno.getPontuacaoPorEstilo().keySet().toArray();
//
//        for(int i = 0; i <= perfilAluno.getPontuacaoPorEstilo().size(); i++){
//            labels[i] = estilos[i].getNome();
//        }
//        return labels;
////        return new String[] { getString(R.string.ativo), getString(R.string.reflexivo), getString(R.string.teorico), getString(R.string.pragmático) };
//
//    }
}
