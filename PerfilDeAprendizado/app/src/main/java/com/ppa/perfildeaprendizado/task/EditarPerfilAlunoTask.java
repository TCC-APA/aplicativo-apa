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

public class EditarPerfilAlunoTask extends AsyncTask<Void, Void, String> {

    private Aluno aluno;

    public EditarPerfilAlunoTask(Aluno aluno){
        this.aluno = aluno;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        if(aluno != null) {
            Aluno alunoAux = new Aluno();
            alunoAux.setNome(aluno.getNome());
            alunoAux.setSenha(aluno.getSenha());
            alunoAux.setGenero(aluno.getGenero());
            alunoAux.setDataNascimento(aluno.getDataNascimento());
            alunoAux.setMatricula(aluno.getMatricula());
            try {
                URL url = new URL("http://ec2-13-58-169-218.us-east-2.compute.amazonaws.com:8080/apa/aluno/" + aluno.getId());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(5000);

                Gson gson = new Gson();
                String input = gson.toJson(alunoAux);

                OutputStream os = connection.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                os.close();

                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }

                if (connection.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                    Log.e("ERRO", "Não foi possível acessar o WebService: " + connection.getResponseCode());
                    return "erroResponseCode";
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
