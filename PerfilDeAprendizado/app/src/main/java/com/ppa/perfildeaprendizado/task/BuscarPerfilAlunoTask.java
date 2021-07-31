package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class BuscarPerfilAlunoTask extends AsyncTask<Void, Void, PerfilAluno> {
    String matricula;
    Long idQuestionario;
    PerfilAluno perfilAluno;
    VolleyError volleyError;

    public BuscarPerfilAlunoTask(String matricula, Long idQuestionario) {
        this.matricula = matricula;
        this.idQuestionario = idQuestionario;
    }

    @Override
    protected PerfilAluno doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.matricula != null && idQuestionario != null) {
            try {
                URL url = new URL("http://ec2-13-58-169-218.us-east-2.compute.amazonaws.com:8080/apa/perfil/pontuacao/ultimaData?matricula=" + matricula + "&idQuestionario=" + idQuestionario);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);

                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNextLine()) {
                    resposta.append(scanner.nextLine());
                }

                JsonObject jsonObject = new JsonParser().parse(resposta.toString()).getAsJsonObject();

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder = gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                if(jsonObject != null) {
                    perfilAluno = gson.fromJson(jsonObject, PerfilAluno.class);
                }else{
                    return null;
                }

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                    return null;
                }

                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return perfilAluno;
    }

    @Override
    protected void onPostExecute(PerfilAluno perfilAluno) {
        super.onPostExecute(perfilAluno);
    }
}
