package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FimQuestionarioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_questionario);
    }

    public void goToQuestionario(View view){
        QuestionarioActivity.refazer = true;
        Intent intent = new Intent(FimQuestionarioActivity.this, QuestionarioActivity.class);
        startActivity(intent);

    }

    public void goToInicio(View view){
        QuestionarioActivity.refazer = true;
        Intent intent = new Intent(FimQuestionarioActivity.this, QuestionarioActivity.class);
        startActivity(intent);

    }
}
