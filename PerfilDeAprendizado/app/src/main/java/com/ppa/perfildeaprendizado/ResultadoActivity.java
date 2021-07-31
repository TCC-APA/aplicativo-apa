package com.ppa.perfildeaprendizado;

import android.os.Bundle;

import com.github.mikephil.charting.charts.RadarChart;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.data.model.PerfilRespostas;

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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_resultado, R.id.navigation_editar_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
