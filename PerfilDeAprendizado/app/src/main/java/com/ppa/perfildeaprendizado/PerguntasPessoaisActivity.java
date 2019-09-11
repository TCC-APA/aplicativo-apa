package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PerguntasPessoaisActivity extends AppCompatActivity {

//    TODO: botar validacao aqui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner genero = findViewById(R.id.genero);
        setContentView(R.layout.activity_perguntas_pessoais);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(PerguntasPessoaisActivity.this, QuestionarioActivity.class);
        startActivity(intent);
    }
}
