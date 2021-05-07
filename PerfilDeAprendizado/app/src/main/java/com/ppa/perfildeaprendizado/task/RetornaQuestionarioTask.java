package com.ppa.perfildeaprendizado.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.ppa.perfildeaprendizado.data.model.Questionario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

//                JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
//                JSONObject jsonObject = jsonArray.getJSONObject(0);
//                JSONArray jsonArray1 = jsonObject.getJSONArray("resultado");
                JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
                questionarios.add(new Gson().fromJson(jsonObject.toString(), Questionario.class));
//                JSONArray jsonArray = jsonObject.getJSONArray("resultado");
//
//                for (int i = 0; i < jsonArray.length(); i++){
//                    questionarios.add(new Gson().fromJson(jsonArray.getString(i), Questionario.class));
//                }

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                }

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return questionarios;
    }
}
