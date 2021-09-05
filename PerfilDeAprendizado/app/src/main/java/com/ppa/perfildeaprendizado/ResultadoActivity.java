package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ppa.perfildeaprendizado.controller.MenuController;
import com.ppa.perfildeaprendizado.data.model.Aluno;

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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_resultado, R.id.navigation_editar_perfil)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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
                MenuController.sobreAction(this);
                return true;
            case R.id.navigation_sair:
                MenuController.sairAction(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
