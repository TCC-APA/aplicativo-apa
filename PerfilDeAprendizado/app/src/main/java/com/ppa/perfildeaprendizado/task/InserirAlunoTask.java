package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class InserirAlunoTask extends AsyncTask<Void, Void, String> {

    private Aluno aluno;

    public InserirAlunoTask(Aluno aluno){
        this.aluno = aluno;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        if(aluno != null) {
            try {
                URL url = new URL("http://192.168.0.11:8080/rest/v1/login/inserir");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(5000);

                Gson gson = new Gson();
                String input = gson.toJson(aluno);

                OutputStream os = connection.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                os.close();

                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                }
                connection.disconnect();

            } catch (MalformedURLException e) {
                Log.e("ERRO", e.getMessage());
            } catch (IOException e) {
                Log.e("ERRO", e.getMessage());
            }
        }
        return resposta.toString();
    }
}
