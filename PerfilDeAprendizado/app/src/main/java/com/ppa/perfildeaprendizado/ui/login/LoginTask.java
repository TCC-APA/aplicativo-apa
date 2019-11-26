package com.ppa.perfildeaprendizado.ui.login;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class LoginTask extends AsyncTask<Void, Void, String> {

    public String email;

    public LoginTask(String email){
        this.email = email;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if(this.email != null){
            try{
                URL url = new URL("http://192.168.0.11:8080/rest/v1/teste/testeurl?cpf=" + this.email);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(50000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //Retorno para quando for mexer com objeto Aluno: return new Gson().fromJson(resposta.toString(), Aluno.class);
        return resposta.substring(resposta.indexOf(":")+2, resposta.length()-2);
    }
}
