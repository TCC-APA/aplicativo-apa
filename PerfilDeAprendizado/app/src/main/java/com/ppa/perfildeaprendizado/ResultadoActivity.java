package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;
import com.ppa.perfildeaprendizado.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private RadarChart radarChart;

    private Aluno aluno = new Aluno();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        radarChart = findViewById(R.id.radarChart);

        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());

        if(aluno != null) {
            if (aluno.temPerfis()) {
                mostrarResultadosLogin();
            } else {
                mostrarResultadosQuestionario();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResultadoActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void fazerGrafico(){
        RadarDataSet radarDataSet = new RadarDataSet(getData(), "");
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
    }

    public void mostrarResultadosLogin(){
        fazerGrafico();
    }

    private List<RadarEntry> getData(){
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(aluno.getPerfilAtivo()));
        entries.add(new RadarEntry(aluno.getPerfilReflexivo()));
        entries.add(new RadarEntry(aluno.getPerfilTeorico()));
        entries.add(new RadarEntry(aluno.getPerfilPragmatico()));
        return entries;
    }

    private String[] getLabels(){
        return new String[] { getString(R.string.ativo), getString(R.string.reflexivo), getString(R.string.teorico), getString(R.string.pragmático) };

    }

    public void mostrarResultadosQuestionario(){
        Integer[] respostas = QuestionarioActivity.respostas;

        if(respostas.length == 80) {
            int ativo = 0;
            int reflexivo = 0;
            int teorico = 0;
            int pragmatico = 0;

            ativo += respostas[2];
            ativo += respostas[4];
            ativo += respostas[6];
            ativo += respostas[8];
            ativo += respostas[12];
            ativo += respostas[19];
            ativo += respostas[25];
            ativo += respostas[26];
            ativo += respostas[34];
            ativo += respostas[36];
            ativo += respostas[40];
            ativo += respostas[42];
            ativo += respostas[45];
            ativo += respostas[47];
            ativo += respostas[50];
            ativo += respostas[60];
            ativo += respostas[66];
            ativo += respostas[73];
            ativo += respostas[74];
            ativo += respostas[76];

            reflexivo += respostas[9];
            reflexivo += respostas[15];
            reflexivo += respostas[17];
            reflexivo += respostas[18];
            reflexivo += respostas[27];
            reflexivo += respostas[30];
            reflexivo += respostas[31];
            reflexivo += respostas[33];
            reflexivo += respostas[35];
            reflexivo += respostas[38];
            reflexivo += respostas[41];
            reflexivo += respostas[43];
            reflexivo += respostas[48];
            reflexivo += respostas[54];
            reflexivo += respostas[57];
            reflexivo += respostas[62];
            reflexivo += respostas[64];
            reflexivo += respostas[68];
            reflexivo += respostas[69];
            reflexivo += respostas[78];

            teorico += respostas[1];
            teorico += respostas[3];
            teorico += respostas[5];
            teorico += respostas[10];
            teorico += respostas[14];
            teorico += respostas[16];
            teorico += respostas[20];
            teorico += respostas[22];
            teorico += respostas[24];
            teorico += respostas[28];
            teorico += respostas[32];
            teorico += respostas[44];
            teorico += respostas[49];
            teorico += respostas[53];
            teorico += respostas[59];
            teorico += respostas[63];
            teorico += respostas[65];
            teorico += respostas[70];
            teorico += respostas[77];
            teorico += respostas[79];

            pragmatico += respostas[0];
            pragmatico += respostas[7];
            pragmatico += respostas[11];
            pragmatico += respostas[13];
            pragmatico += respostas[21];
            pragmatico += respostas[23];
            pragmatico += respostas[29];
            pragmatico += respostas[37];
            pragmatico += respostas[39];
            pragmatico += respostas[46];
            pragmatico += respostas[51];
            pragmatico += respostas[52];
            pragmatico += respostas[55];
            pragmatico += respostas[56];
            pragmatico += respostas[58];
            pragmatico += respostas[61];
            pragmatico += respostas[67];
            pragmatico += respostas[71];
            pragmatico += respostas[72];
            pragmatico += respostas[75];

            aluno.setPerfilAtivo(ativo);
            aluno.setPerfilReflexivo(reflexivo);
            aluno.setPerfilTeorico(teorico);
            aluno.setPerfilPragmatico(pragmatico);

            fazerGrafico();

            try {
                String b = new InserirAlunoTask(aluno).execute().get();
                if (b.equals("true")) {
                    Toast.makeText(this, "Perfil cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar perfil", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
