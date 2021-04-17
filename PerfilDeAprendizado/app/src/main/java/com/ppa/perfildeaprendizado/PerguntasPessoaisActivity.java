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

import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.InserirAlunoTask;

import java.util.concurrent.ExecutionException;

public class PerguntasPessoaisActivity extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText idade;
    private EditText senha;
    private EditText confirmarSenha;
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
        matricula = findViewById(R.id.matricula);
        idade = findViewById(R.id.idade);
        senha = findViewById(R.id.senha);
        confirmarSenha = findViewById(R.id.confirmarSenha);
        genero = findViewById(R.id.genero);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int erros = 0;
                if (nome.getText().toString().equals("")) {
                    nome.setError(erroCampoObrigatorio());
                    erros++;
                }
                if (matricula.getText().toString().equals("")) {
                    matricula.setError(erroCampoObrigatorio());
                    erros++;
                }

                if (idade.getText().toString().equals("")) {
                    idade.setError(erroCampoObrigatorio());
                    erros++;
                }
                if (genero.getSelectedItem().equals(getResources().getStringArray(R.array.generos)[0])) {
                    ((TextView) genero.getSelectedView()).setError("Escolha outra opção!");
                    erros++;
                }
                if (senha.getText().toString().equals("")) {
                    senha.setError(erroCampoObrigatorio());
                    erros++;
                } else if (senha.getText().toString().length() < 5) {
                    senha.setError("A Senha Precisa ter no Mínimo 5 Dígitos!");
                    erros++;
                }
                if (confirmarSenha.getText().toString().equals("")) {
                    confirmarSenha.setError(erroCampoObrigatorio());
                    erros++;
                } else if (!confirmarSenha.getText().toString().equals(senha.getText().toString())) {
                    confirmarSenha.setError("As Senhas Digitadas são Diferentes!");
                    erros++;
                }
                if (erros == 0) {
                    Aluno aluno = new Aluno(matricula.getText().toString(), nome.getText().toString(), Integer.valueOf(idade.getText().toString()), genero.getSelectedItem().toString(), senha.getText().toString());
                    sendMessage(aluno);
                }
            }
        });

    }

    public void sendMessage(Aluno aluno){
        Intent intent = new Intent(PerguntasPessoaisActivity.this, TermoActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
    }

    public String erroCampoObrigatorio(){
        return getResources().getString(R.string.campo_obrigatorio);
    }
}
