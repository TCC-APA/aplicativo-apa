package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.ui.editar_perfil.EditarPerfilFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ResultadoActivity extends AppCompatActivity {

    public NavController navController;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        setContentView(R.layout.activity_resultado);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_resultado, R.id.navigation_editar_perfil)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultadoActivity.this, MenuQuestionariosActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
    }

}
