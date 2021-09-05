package com.ppa.perfildeaprendizado.ui.editar_perfil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ppa.perfildeaprendizado.DetalhesEstilosActivity;
import com.ppa.perfildeaprendizado.MenuQuestionariosActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.ResultadoActivity;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.task.EditarPerfilAlunoTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class EditarPerfilActivity extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText dataNascimento;
    private Button trocaSenha;
    private EditText senha;
    private EditText confirmarSenha;
    private EditText senhaAntiga;
    private Spinner genero;
    private Button enviar;
    private DatePickerDialog datePickerDialog;
    private Calendar dataNascAux = Calendar.getInstance();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editar_perfil);

            final Aluno aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());

            nome = findViewById(R.id.nome);
            matricula = findViewById(R.id.matricula);
            dataNascimento = findViewById(R.id.dataNascimento);
            senhaAntiga = findViewById(R.id.senhaAntiga);
            senha = findViewById(R.id.senhaNova);
            confirmarSenha = findViewById(R.id.confirmarSenhaNova);
            genero = findViewById(R.id.genero);
            enviar = findViewById(R.id.enviar);
            trocaSenha = findViewById(R.id.trocaSenha);

            dataNascimento.setFocusable(false);
            dataNascimento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar calendar = Calendar.getInstance();
                    datePickerDialog = new DatePickerDialog(EditarPerfilActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dataNascAux.set(year, month, dayOfMonth);
                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            dataNascimento.setText(formato.format(dataNascAux.getTime()));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            });

            if (aluno != null) {
                nome.setText(aluno.getNome());
                matricula.setText(aluno.getMatricula());
                if (aluno.getDataNascimento() != null) {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    SimpleDateFormat formatoBanco = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    try {
                        dataNascimento.setText(formato.format(formatoBanco.parse(aluno.getDataNascimento())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                matricula.setEnabled(false);
                String[] generos = getResources().getStringArray(R.array.generos);
                for (int i = 0; i < generos.length; i++) {
                    if (aluno.getGenero().equals(generos[i])) {
                        genero.setSelection(i);
                    }
                }

                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editarAluno(aluno);
                    }
                });

                trocaSenha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trocarSenha(aluno);
                    }
                });
            }
    }
/*
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(EditarPerfilFragment.this, MenuQuestionariosActivity.class);
        startActivity(setIntent);
    }
*/
    private void editarAluno ( final Aluno aluno){

            int erros = 0;
            if (nome.getText().toString().equals("")) {
                nome.setError(erroCampoObrigatorio());
                erros++;
            }
            if (matricula.getText().toString().equals("")) {
                matricula.setError(erroCampoObrigatorio());
                erros++;
            }
            if (dataNascimento.getText().toString().equals("")) {
                dataNascimento.setError(erroCampoObrigatorio());
                erros++;
            }
            if (genero.getSelectedItem().equals(getResources().getStringArray(R.array.generos)[0])) {
                ((TextView) genero.getSelectedView()).setError("Escolha outra opção!");
                erros++;
            }

            if (erros == 0) {
                SimpleDateFormat formatoAux = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                try {
                    aluno.setNome(nome.getText().toString());
                    aluno.setDataNascimento(formato.format(formatoAux.parse(dataNascimento.getText().toString())));
                    aluno.setGenero(genero.getSelectedItem().toString());
                    String retorno = new EditarPerfilAlunoTask(aluno).execute().get();
                    if (retorno.equals("erroResponseCode")) {
                        Toast.makeText(this, "Ocorreu um problema no salvamento dos seus dados. Verifique sua conexão.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Mudanças salvas com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
    }

    private void trocarSenha(Aluno aluno){
        int erros = 0;
        if (senhaAntiga.getText().toString().equals("")){
            senhaAntiga.setError(erroCampoObrigatorio());
            erros++;
        } else if (!senhaAntiga.getText().toString().equals(aluno.getSenha())){
            senhaAntiga.setError("Senha errada!");
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
            try {
                aluno.setSenha(senha.getText().toString());
                String retorno = new EditarPerfilAlunoTask(aluno).execute().get();
                if (retorno.equals("erroResponseCode")){
                    Toast.makeText(this, "Ocorreu um problema no salvamento dos seus dados. Verifique sua conexão.", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String erroCampoObrigatorio(){
        return getResources().getString(R.string.campo_obrigatorio);
    }

}
