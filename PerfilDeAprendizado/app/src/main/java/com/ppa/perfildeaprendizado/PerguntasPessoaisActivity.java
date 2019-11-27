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
    private EditText cpf;
    private EditText matricula;
    private EditText email;
    private EditText turma;
    private EditText idade;
    private EditText senha;
    private EditText confirmarSenha;
    private Spinner genero;
    private LinearLayout form;
    private Button enviar;
    private Button teste;

//    TODO: botar validacao aqui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_pessoais);
        form = findViewById(R.id.form);
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        matricula = findViewById(R.id.matricula);
        email = findViewById(R.id.email);
        turma = findViewById(R.id.turma);
        idade = findViewById(R.id.idade);
        senha = findViewById(R.id.senha);
        confirmarSenha = findViewById(R.id.confirmarSenha);
        genero = findViewById(R.id.genero);
        enviar = findViewById(R.id.enviar);
        teste = findViewById(R.id.button_teste);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int erros = 0;
                if(nome.getText().toString().equals("")){
                    nome.setError(erroCampoObrigatorio());
                    erros ++;
                }
                if(cpf.getText().toString().equals("")){
                    cpf.setError(erroCampoObrigatorio());
                    erros ++;
                }
                if(matricula.getText().toString().equals("")){
                    matricula.setError(erroCampoObrigatorio());
                    erros ++;
                }
                if(email.getText().toString().equals("")){
                    email.setError(erroCampoObrigatorio());
                    erros ++;
                }else if(!email.getText().toString().contains("@") || !email.getText().toString().contains(".")){
                    email.setError("Precisa ser um e-mail!");
                    erros ++;
                }
                if(turma.getText().toString().equals("")){
                    turma.setError(erroCampoObrigatorio());
                    erros ++;
                }
                if(idade.getText().toString().equals("")){
                    idade.setError(erroCampoObrigatorio());
                    erros ++;
                }
                if(genero.getSelectedItem().equals(getResources().getStringArray(R.array.generos)[0])){
                    ((TextView)genero.getSelectedView()).setError("Escolha outra opção!");
                    erros ++;
                }
                if(senha.getText().toString().equals("")){
                    senha.setError(erroCampoObrigatorio());
                    erros ++;
                }else if(senha.getText().toString().length() < 5){
                    senha.setError("A Senha Precisa ter no Mínimo 5 Dígitos!");
                    erros++;
                }
                if(confirmarSenha.getText().toString().equals("")){
                    confirmarSenha.setError(erroCampoObrigatorio());
                    erros++;
                }else if(!confirmarSenha.getText().toString().equals(senha.getText().toString())){
                    confirmarSenha.setError("As Senhas Digitadas são Diferentes!");
                    erros++;
                }
                if(erros == 0){
                    Aluno aluno = new Aluno(cpf.getText().toString(), matricula.getText().toString(), nome.getText().toString(), email.getText().toString(), turma.getText().toString(), Integer.valueOf(idade.getText().toString()), genero.getSelectedItem().toString(), senha.getText().toString());
                    try {
                        Boolean b = new InserirAlunoTask(aluno).execute().get();
                        if(b){
                            sendMessage();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
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
