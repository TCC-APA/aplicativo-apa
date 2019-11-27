package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.io.OutputStream;
import java.net.MalformedURLException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InserirAlunoTask extends AsyncTask<Aluno, Void, Boolean> {

    private Aluno aluno;

    public InserirAlunoTask(Aluno aluno){
        this.aluno = aluno;
    }

    @Override
    protected Boolean doInBackground(Aluno... alunos) {
        alunos = new Aluno[1];
        alunos[0] = aluno;
        try{
            URL url = new URL("http://192.168.0.11:8080/rest/v1/login/inserir");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            Gson gson = new Gson();
            String input = gson.toJson(alunos[0]);

            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
            }
            connection.disconnect();
            return true;

        } catch (MalformedURLException e) {
            Log.e("ERRO", e.getMessage());
            return false;
        } catch (IOException e){
            Log.e("ERRO", e.getMessage());
            return false;
        }
    }
}
