package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class LoginTask extends AsyncTask<Void, Void, Aluno> {

    private String matricula;
    private String senha;

    public LoginTask(String matricula, String senha){
        this.matricula = matricula;
        this.senha = senha;
    }

    @Override
    protected Aluno doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if(this.matricula != null && this.senha != null){
            try{
                URL url = new URL("http://192.168.0.11:8080/apa/aluno/login");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(5000);

                String input = "{\"matricula\":\"" + matricula + "\",\"senha\":\"" + senha + "\"}";

                OutputStream os = connection.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                os.close();

                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                }

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), Aluno.class);
    }
}
