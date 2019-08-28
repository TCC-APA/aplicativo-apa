package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerguntasPessoaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_pessoais);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(PerguntasPessoaisActivity.this, QuestionarioActivity.class);
        startActivity(intent);

    }
}
