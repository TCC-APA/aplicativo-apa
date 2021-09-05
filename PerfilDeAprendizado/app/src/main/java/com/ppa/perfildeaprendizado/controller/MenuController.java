package com.ppa.perfildeaprendizado.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.ui.editar_perfil.EditarPerfilActivity;
import com.ppa.perfildeaprendizado.ui.login.LoginActivity;

public final class MenuController {
    public static void sairAction(Context context){
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public static void editarPerfilAction(Context context, Aluno aluno){
        Intent i = new Intent(context, EditarPerfilActivity.class);
        i.putExtra(Aluno.class.getSimpleName(), aluno);
        context.startActivity(i);
    }

    public static void setupMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.bottom_nav_menu, menu);

        MenuItem itemSair = menu.findItem(R.id.navigation_sair);
        SpannableString spanString = new SpannableString(itemSair.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, spanString.length(), 0); //fix the color to white
        itemSair.setTitle(spanString);
    }
}
