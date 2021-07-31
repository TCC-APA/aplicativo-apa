package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ppa.perfildeaprendizado.data.model.Questionario;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RetornaQuestionarioTask extends AsyncTask<Void, Void, List<Questionario>> {

    private String matricula;

    public RetornaQuestionarioTask(String matricula){
        this.matricula = matricula;
    }

    @Override
    protected List<Questionario> doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        List<Questionario> questionarios = new ArrayList<>();

        if(this.matricula != null){
            try {
                URL url = new URL("http://ec2-13-58-169-218.us-east-2.compute.amazonaws.com:8080/apa/questionario/grupos?matricula=" + matricula);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);

                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNextLine()) {
                    resposta.append(scanner.nextLine());
                }

                JsonArray jsonArray = (JsonArray) new JsonParser().parse(resposta.toString()).getAsJsonObject().get("questionarios");

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder = gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();

//                for (int i = 0; i < jsonArray.size(); i++){
                    questionarios.add(new Gson().fromJson(jsonArray.get(0), Questionario.class));
//                }

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                    return null;
                }

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return questionarios;
    }
}
