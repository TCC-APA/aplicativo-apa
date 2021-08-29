package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private Aluno aluno;

    public LoginTask(String matricula, String senha){
        this.matricula = matricula;
        this.senha = senha;
    }

    @Override
    protected Aluno doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if(this.matricula != null && this.senha != null){
            try{
                URL url = new URL("http://ec2-13-58-169-218.us-east-2.compute.amazonaws.com:8080/apa/aluno/login");
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
                while (scanner.hasNextLine()) {
                    resposta.append(scanner.nextLine());
                }

                JsonObject jsonObject = new JsonParser().parse(resposta.toString()).getAsJsonObject();

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder = gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                aluno = gson.fromJson(jsonObject, Aluno.class);

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                    return null;
                }

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return aluno;
    }
}
