package com.ppa.perfildeaprendizado.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppa.perfildeaprendizado.MenuQuestionariosActivity;
import com.ppa.perfildeaprendizado.QuestionarioActivity;
import com.ppa.perfildeaprendizado.QuestionarioSobreActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.ResultadoActivity;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.RetornaQuestionarioTask;
import com.ppa.perfildeaprendizado.ui.adapters.MenuQuestionarioAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class MenuQuestionarioFragment extends Fragment {

    @BindView(R.id.lista_questionarios)
    RecyclerView listaQuestionarios;

    private List<Questionario> questionarios;
    private MenuQuestionarioAdapter adapter;
    private Aluno aluno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu_questionario, container, false);

        if(getActivity() != null){
            ButterKnife.bind(root);
            try {
                aluno = (Aluno) this.getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());
                questionarios = new RetornaQuestionarioTask(aluno.getMatricula()).execute().get();
                if(questionarios != null && !questionarios.isEmpty()) {
                    carregarView();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    public void goToQuestionario(Questionario questionario){
        Intent intent = new Intent(this.getActivity(), QuestionarioActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    public void goToResultado(Questionario questionario){
        Intent intent = new Intent(this.getActivity(), ResultadoActivity.class);
        intent.putExtra(Aluno.class.getSimpleName(), aluno);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    public void goToSobre(Questionario questionario){
        Intent intent = new Intent(this.getActivity(), QuestionarioSobreActivity.class);
        intent.putExtra(Questionario.class.getSimpleName(), questionario);
        startActivity(intent);
    }

    private void carregarView(){
        adapter = new MenuQuestionarioAdapter(questionarios, aluno.getMatricula(), this);
        listaQuestionarios = new RecyclerView(getContext());
        listaQuestionarios.setHasFixedSize(false);
        listaQuestionarios.setItemAnimator(new DefaultItemAnimator());
        listaQuestionarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listaQuestionarios.setAdapter(adapter);
        listaQuestionarios.setVisibility(View.VISIBLE);

    }
}
