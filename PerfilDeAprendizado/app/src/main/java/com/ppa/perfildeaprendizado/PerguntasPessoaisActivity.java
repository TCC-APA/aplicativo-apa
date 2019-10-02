package com.ppa.perfildeaprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PerguntasPessoaisActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText turma;
    private EditText idade;
    private EditText senha;
    private Spinner genero;
    private LinearLayout form;
    private Button enviar;

//    TODO: botar validacao aqui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_pessoais);
        form = findViewById(R.id.form);
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        turma = findViewById(R.id.turma);
        idade = findViewById(R.id.idade);
        senha = findViewById(R.id.senha);
        genero = findViewById(R.id.genero);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int erros = 0;
                if(nome.getText().toString().equals("")){
                    nome.setError(erroCampoObrigatorio());
                    erros += 1;
                }
                if(email.getText().toString().equals("")){
                    email.setError(erroCampoObrigatorio());
                    erros += 1;
                }else if(!email.getText().toString().contains("@") || !email.getText().toString().contains(".")){
                    email.setError("Precisa ser um e-mail!");
                    erros += 1;
                }
                if(turma.getText().toString().equals("")){
                    turma.setError(erroCampoObrigatorio());
                    erros += 1;
                }
                if(idade.getText().toString().equals("")){
                    idade.setError(erroCampoObrigatorio());
                    erros += 1;
                }
                if(genero.getSelectedItem().equals(getResources().getStringArray(R.array.generos)[0])){
                    ((TextView)genero.getSelectedView()).setError("Escolha outra opção!");
                    erros += 1;
                }
                if(senha.getText().toString().equals("")){
                    senha.setError(erroCampoObrigatorio());
                    erros += 1;
                }else if(senha.getText().toString().length() < 5){
                    senha.setError("A Senha Precisa ter no Mínimo 5 Dígitos!");
                    erros += 1;
                }
                if(erros == 0){
                    sendMessage();
                }
            }
        });
    }

    public void sendMessage(){
        Intent intent = new Intent(PerguntasPessoaisActivity.this, MenuQuestionariosActivity.class);
        startActivity(intent);
    }

    public String erroCampoObrigatorio(){
        return getResources().getString(R.string.campo_obrigatorio);
    }
}
