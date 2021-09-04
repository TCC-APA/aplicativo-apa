package com.ppa.perfildeaprendizado.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ppa.perfildeaprendizado.MenuActivity;
import com.ppa.perfildeaprendizado.MenuQuestionariosActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.TermoActivity;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.LoginTask;

import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button firstAccessButton = (Button) findViewById(R.id.primAcesso);
        final EditText usernameEditText = (EditText) findViewById(R.id.username);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);
        final Button loginButton = (Button) findViewById(R.id.login);

        firstAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarQuestionario();
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginButton.setEnabled(true);
            }
        };

        usernameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = null;
                try {
                    aluno = new LoginTask(usernameEditText.getText().toString(), passwordEditText.getText().toString()).execute().get();
                    if(aluno != null) {
                        if(aluno.getErros() != null){
                            showLoginFailed("Matrícula ou senha erradas!");
                        }else {
                            updateUiWithUser(aluno);
                        }
                    }else {
                        showLoginFailed("Login Falhou!");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateUiWithUser(Aluno aluno) {
        String welcome = getString(R.string.welcome) + aluno.getNome() + "!";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        startActivity(intent);
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_LONG).show();
    }

    private void iniciarQuestionario(){
        Intent intent = new Intent(this, TermoActivity.class);
        startActivity(intent);
    }
}
