package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuQuestionariosActivity extends AppCompatActivity {

    private CardView questionariosView;
    //K: Turma, V: Nome dos questionarios
    private Map<String, List<String>> questionariosTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_questionarios);

        /*List<String> questionarios = new ArrayList<String>();
        questionarios.add("Perfil de Aprendizado");
        questionarios.add("Questionario 2");
        questionarios.add("Questionario 3");
        questionarios.add("Quesitonario 4");
        questionarios.add("Questionario 5");

        this.questionariosTurma.put("T123", questionarios);

        this.questionariosView = (CardView) findViewById(R.id.cardViewQuest);
        this.questionariosView.addView*/
    }

    public int getNumeroQuestionarios(){
        return this.questionariosTurma.get("T123").size();


    }

    public void goToQuetionario(View view){

        Intent intent = new Intent(MenuQuestionariosActivity.this, QuestionarioActivity.class);
        startActivity(intent);

    }
}
