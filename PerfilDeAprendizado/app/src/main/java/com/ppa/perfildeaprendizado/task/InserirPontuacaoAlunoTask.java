package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ppa.perfildeaprendizado.data.model.PerfilRespostas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class InserirPontuacaoAlunoTask extends AsyncTask<Void, Void, String> {

    private PerfilRespostas perfilRespostas;

    public InserirPontuacaoAlunoTask(PerfilRespostas perfilRespostas){
        this.perfilRespostas = perfilRespostas;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        String redactedResponse = "";
        if(perfilRespostas != null) {
            try {
                URL url = new URL("http://ec2-13-58-169-218.us-east-2.compute.amazonaws.com:8080/apa/perfil/pontuacao");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(5000);

                Gson gson = new Gson();
                String input = gson.toJson(perfilRespostas);

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
                    redactedResponse = "Ocorreu um erro ao inserir sua pontuação.";
                }
                connection.disconnect();
                redactedResponse = "Pontuação cadastrada com sucesso!";

            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
                redactedResponse = "Ocorreu um erro ao inserir sua pontuação.";
            }
        }
        return redactedResponse;
    }
}
